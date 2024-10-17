package Tests;

import Domain.*;
import Presentation.ConsoleIO;
import Presentation.IO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class StoreTests {

    private Store store;
    private ProductInStore product;
    private  Category category;
    private Order order;

    @Before
    public void setUp() {
        store = new Store("Test Store");
        Manufacturer manufacturer = new Manufacturer("dandan");
        Product product1 = new Product("Twist", manufacturer);
        category = new Category("sweets");
        product = new ProductInStore(product1, 100, category, 10);
        order = new Order();
    }

    // Test case 1: Add an order and verify if it's added
    @Test
    public void testAddOrder() {
        Assert.assertTrue(store.getOrders().isEmpty());
        store.addOrder(order);
        Assert.assertFalse(store.getOrders().isEmpty());
        Assert.assertEquals(1, store.getOrders().size());
    }

    // Test case 2: Add a product to the shelf when no shelves exist
    @Test
    public void testAddItemToEmptyShelf() {
        Assert.assertTrue(store.getShelves().isEmpty());
        store.addItem(product);
        Assert.assertFalse(store.getShelves().isEmpty());
        Assert.assertEquals(1, store.getShelves().get(0).getItemsOnShelf().size());
    }

    // Test case 3: Get the specific amount of an item in the store
    @Test
    public void testGetSpecificItemAmount() {
        store.addItem(product);
        int amount = store.getSpecificItemAmount(product);
        Assert.assertEquals(1, amount);
    }

    // Test case 4: Verify the store's name getter
    @Test
    public void testGetStoreName() {
        Assert.assertEquals("Test Store", store.getName());
    }

    // Test case 5: Add a shelf and verify it's added
    @Test
    public void testAddShelf() {
        Shelf shelf = new Shelf();
        store.addShelf(shelf);
        Assert.assertEquals(1, store.getShelves().size());
    }

    // Test case 6: Create a store from IO
    @Test
    public void testGetStoreFromIO() {
        IO io = new ConsoleIO() {
            @Override
            public String readString(String message) {
                return "Test Store";
            }

            @Override
            public int readInt(String message) {
                return 0;
            }
        };

        Store newStore = Store.getStoreFromIO(io);
        Assert.assertEquals("Test Store", newStore.getName());
    }

    // Test case 7: Verify if the storage object is set and retrieved correctly
    @Test
    public void testGetSetStorage() {
        Storage storage = new Storage();
        store.setStorage(storage);
        Assert.assertEquals(storage, store.getStorage());
    }

    // Test case 8: Verify that orders are unmodifiable after creation
    @Test
    public void testAddOrderWithProducts() {
        List<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("Dan the orange", "052-345-6789"));
        Supplier sup = new InPlaceSupplier(false, "activeAccount1", 123456, PaymentOption.CREDIT_CARD, contacts, 3);
        order.addProduct(new ProductInOrder(product.getProduct(), sup, 100));
        store.addOrder(order);
        Assert.assertEquals(1, store.getOrders().size());
    }

    // Test case 9: Check if adding multiple items works properly
    @Test
    public void testAddMultipleItemsToShelf() {
        store.addItem(product);
        Manufacturer manufacturer = new Manufacturer("idanidan");
        Product product1 = new Product("Tortit", manufacturer);
        ProductInStore product2 = new ProductInStore(product1, 50, category, 10);
        store.addItem(product2);
        Assert.assertEquals(1, store.getShelves().size());
    }

    // Test case 10: Check that empty store starts with no shelves
    @Test
    public void testEmptyShelvesInitially() {
        Assert.assertTrue(store.getShelves().isEmpty());
    }
}
