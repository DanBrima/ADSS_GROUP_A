package Tests;

import Domain.*;

import java.util.List;
import java.util.Objects;

public class Test {
    private static Contact dan;
    private static Contact ban;
    private static Manufacturer tnuva;
    private static Manufacturer osem;
    private static Product milk;
    private static Product shoko;
    private static Product cheese;
    private static Product bisli;
    private static Product bamba;
    private static Product waffle;
    private static InPlaceSupplier biko;
    private static FixedDaysSupplier shufersal;
    private static Contract bikoDiary;
    private static Contract bikoOsem;
    private static Contract shufersalDiary;

    private static void assertTrue(boolean condition) throws Exception {
        if (!condition) {
            throw new Exception("Test failed");
        }
    }

    private static void initializeObjects() {
        dan = new Contact("Dan", "0585979676");
        ban = new Contact("Ban", "0586979676");
        tnuva = new Manufacturer("Tnuva");
        milk = new Product("Milk", tnuva);
        shoko = new Product("Shoko", tnuva);
        cheese = new Product("Cheese", tnuva);
        osem = new Manufacturer("Osem");
        bisli = new Product("Bisli", osem);
        bamba = new Product("Bamba", osem);
        waffle = new Product("Waffle", osem);
        biko = new InPlaceSupplier(true, "biko", 123456,
                PaymentOption.CASH, List.of(dan, ban), 5);
        shufersal = new FixedDaysSupplier(false, "shufersal", 123456,
                PaymentOption.CREDIT_CARD, List.of(ban), List.of(WeekDay.MONDAY, WeekDay.TUESDAY));
    }

    public void test_SameProductMultipleSuppliers() throws Exception {
        initializeObjects();
        bikoDiary = new Contract(100, biko,
                List.of(new ProductInContract(milk,  10,25),
                        new ProductInContract(shoko, 10, 20)));
        bikoOsem = new Contract(100, biko,
                List.of(new ProductInContract(bisli, 10, 25),
                        new ProductInContract(bamba, 10, 20),
                        new ProductInContract(waffle, 10, 30)));
        shufersalDiary = new Contract(15, shufersal,
                List.of(new ProductInContract(milk, 10, 10),
                        new ProductInContract(shoko, 10, 10)));
        biko.addContract(bikoDiary);
        shufersal.addContract(shufersalDiary);

        assertTrue(false);
    }

    public void test_SameSupplierMultipleContracts() throws Exception {
        initializeObjects();
        bikoDiary = new Contract(100, biko,
                List.of(new ProductInContract(milk, 10, 25),
                        new ProductInContract(shoko, 10, 20)));
        bikoOsem = new Contract(100, biko,
                List.of(new ProductInContract(bisli, 10, 25),
                        new ProductInContract(bamba, 10, 20),
                        new ProductInContract(waffle, 10, 30)));
        biko.addContract(bikoDiary);
        biko.addContract(bikoOsem);

        assertTrue(biko.contracts().size() == 2);
    }

    public void test_ContractWithDiscount() throws Exception {
        initializeObjects();
        bikoOsem = new Contract(10, biko,
                List.of(new ProductInContract(bisli, 10, 25),
                        new ProductInContract(bamba, 10, 20),
                        new ProductInContract(waffle, 1, 30)));
    }

    public void test_EmptyContract() throws Exception {
        initializeObjects();
        List list = List.of();
        int size = biko.getAllProductsInContracts().size();
        bikoOsem = new Contract(10, biko, List.of());
        biko.addContract(bikoOsem);
        assertTrue(biko.getAllProductsInContracts().size() == size);
    }

    public void test_SameSupplierProduct() throws Exception {
        initializeObjects();
        Contract bikoOsem1 = new Contract(100, biko,
                List.of(new ProductInContract(bisli, 15, 25),
                        new ProductInContract(bamba, 10, 20),
                        new ProductInContract(waffle, 5, 30)));
        Contract bikoOsem2 = new Contract(100, biko,
                List.of(new ProductInContract(bisli, 4, 25),
                        new ProductInContract(bamba, 10, 20),
                        new ProductInContract(waffle, 15, 30)));
        biko.addContract(bikoOsem1);
        biko.addContract(bikoOsem2);
        // Needs to be 6 items (even if the products are the same)
        assertTrue(biko.getAllProductsInContracts().size() == 6);
    }

    public void test_SameNameSuppliers() throws Exception {
        initializeObjects();
        FixedDaysSupplier shufersal2 = new FixedDaysSupplier(false, "shufersal", 123456,
                PaymentOption.CREDIT_CARD, List.of(ban), List.of(WeekDay.MONDAY, WeekDay.TUESDAY));
        assertTrue(Objects.equals(shufersal2.activeAccount(), shufersal.activeAccount()));
    }

    // Always with discount
    public void test_ZeroWholesaleThreshold() throws Exception {
        initializeObjects();
        bikoOsem = new Contract(0, biko,
                List.of(new ProductInContract(bisli, 10, 25),
                        new ProductInContract(bamba, 10, 20),
                        new ProductInContract(waffle, 10, 30)));
        biko.addContract(bikoOsem);
    }

    public void test_NoDeliveryDays() throws Exception {
        initializeObjects();
        shufersal = new FixedDaysSupplier(false, "shufersal", 123456,
                PaymentOption.CREDIT_CARD, List.of(ban), List.of());
        assertTrue(((FixedDaysSupplier) shufersal).getArrivalDays().size() == 0);
    }

    public void test_immediateDeliveryInPlace() throws Exception {
        initializeObjects();
        biko = new InPlaceSupplier(true, "shufersal", 123456,
                PaymentOption.CREDIT_CARD, List.of(ban), 0);
        assertTrue(((InPlaceSupplier) biko).getDeliveryDays() == 0);
    }

}
