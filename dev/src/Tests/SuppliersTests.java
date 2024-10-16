package Tests;

import Domain.*;
import Presentation.ConsoleIO;
import Presentation.IO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SuppliersTests {
    private Supplier supplier;
    private Contract contract;
    private List<Contact> contacts;
    private List<ProductInContract> products;

    @Before
    public void setUp() {
        // Setup the contact list and product list for testing
        contacts = new ArrayList<>();
        contacts.add(new Contact("Dan the orange", "052-345-6789"));

        Manufacturer aviad = new Manufacturer("Aviad");

        Product product1 = new Product("Bisli", aviad);
        Product product2 = new Product("Bamba", aviad);

        products = new ArrayList<>();
        products.add(new ProductInContract(product1, 100, 10));
        products.add(new ProductInContract(product2, 200, 15));

        contract = new Contract(10, supplier, products);
        supplier = new InPlaceSupplier(false, "activeAccount1", 123456, PaymentOption.CREDIT_CARD, contacts, 3);
    }

    // Test case 1: Add a contract to a supplier and verify it's added
    @Test
    public void testAddContract() {
        Assert.assertEquals(0, supplier.contracts().size());
        supplier.addContract(contract);
        Assert.assertEquals(1, supplier.contracts().size());
    }

    // Test case 2: Verify if the supplier can supply a specific product
    @Test
    public void testGetContractSupplying() {
        supplier.addContract(contract);
        Assert.assertNotNull(supplier.getContractSupplying("Bisli"));
    }

    // Test case 3: Verify the supplier doesn't supply a product not in any contract
    @Test
    public void testGetContractSupplyingProductNotFound() {
        supplier.addContract(contract);
        Assert.assertNull(supplier.getContractSupplying("Chips"));
    }

    // Test case 4: Check if supplier needs pickup
    @Test
    public void testNeedsPickup() {
        Assert.assertFalse(supplier.needsPickup());
    }

    // Test case 5: Check if all products in contracts are returned
    @Test
    public void testGetAllProductsInContracts() {
        supplier.addContract(contract);
        List<ProductInContract> productsInContracts = supplier.getAllProductsInContracts();
        Assert.assertEquals(2, productsInContracts.size());
    }

    // Test case 6: Verify creation of Supplier from IO
    @Test
    public void testGetSupplierFromIO() {
        IO io = new ConsoleIO() {
            @Override
            public String readString(String message) {
                switch (message) {
                    case "Does the supplier need pickup (type yes/no)?":
                        return "yes";
                    case "Enter the supplier's active account:":
                        return "activeAccount";
                    case "Enter the week day (SUNDAY/MONDAY/TUESDAY/WEDNESDAY/THURSDAY/FRIDAY/SATURDAY):":
                        return "SUNDAY";
                    case "Enter the payment option (CASH/CREDIT_CARD):":
                        return "CASH";
                    default:
                        return "";
                }
            }

            @Override
            public int readInt(String message) {
                if (message.equals("Enter the supplier's bank account:")) {
                    return 654321;
                }
                if (message.equals("Enter the number of contacts:")) {
                    return 1;
                }
                if (message.equals("Enter the number of arrival days:")){
                    return 1;
                }
                return 0;
            }
        };

        Supplier supplierFromIO = FixedDaysSupplier.getFixedDaysSupplierFromIO(io);
        Assert.assertNotNull(supplierFromIO);
        Assert.assertTrue(supplierFromIO.needsPickup());
        Assert.assertEquals("activeAccount", supplierFromIO.activeAccount());
        Assert.assertEquals(654321, supplierFromIO.bankAccount());
    }

    // Test case 7: Verify bank account getter
    @Test
    public void testGetBankAccount() {
        Assert.assertEquals(123456, supplier.bankAccount());
    }

    // Test case 8: Verify payment option getter
    @Test
    public void testPaymentOption() {
        Assert.assertEquals(PaymentOption.CREDIT_CARD, supplier.paymentOption());
    }

    // Test case 9: Verify if supplier contacts are unmodifiable
    @Test
    public void testContactsUnmodifiable() {
        List<Contact> unmodifiableContacts = supplier.contacts();
        Assert.assertThrows(UnsupportedOperationException.class, () -> unmodifiableContacts.add(new Contact("Ofek",  "053-456-7890")));
    }

    // Test case 10: Verify if supplier has an empty contract list initially
    @Test
    public void testEmptyContractsInitially() {
        Assert.assertTrue(supplier.contracts().isEmpty());
    }
}

