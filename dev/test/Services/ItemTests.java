package Services;

import Domain.Items.Item;
import Domain.Items.ItemStack;
import Domain.Shelf;
import Domain.Storage;
import Domain.Store;
import Service.ItemService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;

public class ItemTests {
    private Store EXMPLE_VALID_STORE = new Store();
    private Storage EXMPLE_VALID_STORAGE = new Storage();
    private Item EXAMPLE_VALID_ITEM_1 = new Item("test1", new BigDecimal(1), "supplier1", new BigDecimal(1), 1, null);
    private Item EXAMPLE_VALID_ITEM_2 = new Item("test2", new BigDecimal(1), "supplier2", new BigDecimal(1), 1, null);
    private ItemStack EXAMPLE_VALID_ITEM_STACK_1 = new ItemStack(EXAMPLE_VALID_ITEM_1);
    private ItemStack EXAMPLE_VALID_ITEM_STACK_2 = new ItemStack(EXAMPLE_VALID_ITEM_2);
    private Shelf EXMPLE_SHELF_1 = new Shelf(EXAMPLE_VALID_ITEM_STACK_1);
    private Shelf EXMPLE_SHELF_2 = new Shelf(EXAMPLE_VALID_ITEM_STACK_2);

    @Before
    public void setUp() {
        this.EXMPLE_VALID_STORE = new Store();
        this.EXMPLE_VALID_STORAGE = new Storage();
        this.EXAMPLE_VALID_ITEM_1 = new Item("test1", new BigDecimal(1), "supplier1", new BigDecimal(1), 1, null);
        this.EXAMPLE_VALID_ITEM_2 = new Item("test2", new BigDecimal(1), "supplier2", new BigDecimal(1), 1, null);
        this.EXAMPLE_VALID_ITEM_STACK_1 = new ItemStack(EXAMPLE_VALID_ITEM_1);
        this.EXAMPLE_VALID_ITEM_STACK_2 = new ItemStack(EXAMPLE_VALID_ITEM_2);
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
