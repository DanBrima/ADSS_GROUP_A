package Tests;

import Domain.*;
import Service.ItemService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;

public class ProductInStoreTests {
    private Store EXMPLE_VALID_STORE;
    private Storage EXMPLE_VALID_STORAGE;

    Manufacturer aviad = new Manufacturer("Aviad");
    Product product1 = new Product("Bisli", aviad);
    Product product2 = new Product("Bamba", aviad);
    Category category = new Category("Snack");
    private ProductInStore EXAMPLE_VALID_ITEM_1 = new ProductInStore(product1,10,category,10);
    private ProductInStore EXAMPLE_VALID_ITEM_2 = new ProductInStore(product2,100,category,5);
    private ItemStack EXAMPLE_VALID_ITEM_STACK_1 = new ItemStack(EXAMPLE_VALID_ITEM_1,20);
    private ItemStack EXAMPLE_VALID_ITEM_STACK_2 = new ItemStack(EXAMPLE_VALID_ITEM_2, 10);
    private Shelf EXMPLE_SHELF_1 = new Shelf(EXAMPLE_VALID_ITEM_STACK_1);
    private Shelf EXMPLE_SHELF_2 = new Shelf(EXAMPLE_VALID_ITEM_STACK_2);

    @Before
    public void setUp() {
        this.EXMPLE_VALID_STORE = new Store("Maayan's");
        this.EXMPLE_VALID_STORAGE = new Storage();
        this.EXAMPLE_VALID_ITEM_1 = new ProductInStore(product1,20,category,15);
        this.EXAMPLE_VALID_ITEM_2 = new ProductInStore(product2,50, category,10);
        this.EXAMPLE_VALID_ITEM_STACK_1 = new ItemStack(EXAMPLE_VALID_ITEM_1, 10);
        this.EXAMPLE_VALID_ITEM_STACK_2 = new ItemStack(EXAMPLE_VALID_ITEM_2, 50);
        this.EXMPLE_SHELF_1 = new Shelf(EXAMPLE_VALID_ITEM_STACK_1);
        this.EXMPLE_SHELF_2 = new Shelf(EXAMPLE_VALID_ITEM_STACK_2);
    }

    @Test
    public void getAllUniqueItemsFromStorage_When_StorageEmpty() {

        Assert.assertTrue(ItemService.getAllUniqueItemsFromStorage(EXMPLE_VALID_STORAGE).isEmpty());
    }
    @Test
    public void getAllUniqueItemsFromStore_When_TwoDifferent() {
        EXMPLE_VALID_STORE.addShelf(EXMPLE_SHELF_1);
        EXMPLE_VALID_STORE.addShelf(EXMPLE_SHELF_2);

        ArrayList expected = new ArrayList<>();
        expected.add(EXAMPLE_VALID_ITEM_STACK_1);
        expected.add(EXAMPLE_VALID_ITEM_STACK_2);

        ArrayList results = ItemService.getAllUniqueItemsFromStore(EXMPLE_VALID_STORE);

        Assert.assertTrue(new HashSet<>(results).equals(new HashSet<>(expected)));
    }

    @Test
    public void getAllUniqueItemsFromStorage_When_TwoDifferent() {
        EXMPLE_VALID_STORAGE.addItemStack(EXAMPLE_VALID_ITEM_STACK_1);
        EXMPLE_VALID_STORAGE.addItemStack(EXAMPLE_VALID_ITEM_STACK_2);

        ArrayList expected = new ArrayList<>();
        expected.add(EXAMPLE_VALID_ITEM_STACK_1);
        expected.add(EXAMPLE_VALID_ITEM_STACK_2);

        ArrayList results = ItemService.getAllUniqueItemsFromStorage(EXMPLE_VALID_STORAGE);

        Assert.assertTrue(new HashSet<>(results).equals(new HashSet<>(expected)));
    }

    @Test
    public void getAllUniqueItemsFromStorage_When_StoreEmpty() {
        Assert.assertTrue(ItemService.getAllUniqueItemsFromStore(EXMPLE_VALID_STORE).isEmpty());
    }

    @Test
    public void getAllUniqueItems_When_BothEmpty() {
        Assert.assertTrue(ItemService.getAllUniqueItemsFromStore(EXMPLE_VALID_STORE).isEmpty());
    }

    @Test
    public void getAllUniqueItemsWithoutDefective_When_BothEmpty() {
        Assert.assertTrue(ItemService.getAllUniqueItemsFromStore(EXMPLE_VALID_STORE).isEmpty());
    }
}
