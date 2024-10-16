package Tests;

import Domain.*;
import Service.ItemStackService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;

public class ItemStackTests {
    Manufacturer aviad = new Manufacturer("Aviad");
    Product product1 = new Product("Bisli", aviad);
    Product product2 = new Product("Bamba", aviad);
    Category category = new Category("Snack");
    private ProductInStore EXAMPLE_VALID_ITEM_1 = new ProductInStore(product1,10,category,10);
    private ProductInStore EXAMPLE_VALID_ITEM_2 = new ProductInStore(product2,100,category,5);
    private ItemStack EXAMPLE_VALID_ITEM_STACK_1 = new ItemStack(EXAMPLE_VALID_ITEM_1, 100);
    private ItemStack EXAMPLE_VALID_ITEM_STACK_2 = new ItemStack(EXAMPLE_VALID_ITEM_2, 20);

    @Before
    public void setUp() {
        this.EXAMPLE_VALID_ITEM_1 = new ProductInStore(product1,1,category,5);
        this.EXAMPLE_VALID_ITEM_2 = new ProductInStore(product2,10,category,10);
        this.EXAMPLE_VALID_ITEM_STACK_1 = new ItemStack(EXAMPLE_VALID_ITEM_1, 2);
        this.EXAMPLE_VALID_ITEM_STACK_2 = new ItemStack(EXAMPLE_VALID_ITEM_2, 20);
    }

    @Test
    public void combineUniqueItems_When_TwoEmptyArrays() {
        ArrayList arr1 = new ArrayList<>();
        ArrayList arr2 = new ArrayList<>();

        ArrayList result = ItemStackService.combineUniqueItems(arr1, arr2);

        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void combineUniqueItems_When_OneEmptyArray() {
        ArrayList<ItemStack> arr1 = new ArrayList<>();
        ArrayList<ItemStack> arr2 = new ArrayList<>();
        arr2.add(EXAMPLE_VALID_ITEM_STACK_1);

        ArrayList result = ItemStackService.combineUniqueItems(arr1, arr2);

        Assert.assertTrue(result.equals(arr2));
    }

    @Test
    public void combineUniqueItems_When_TwoSameItemStackArrays() {
        ArrayList<ItemStack> arr1 = new ArrayList<>();
        arr1.add(EXAMPLE_VALID_ITEM_STACK_1);
        ArrayList<ItemStack> arr2 = new ArrayList<>();
        arr2.add(EXAMPLE_VALID_ITEM_STACK_1);

        ArrayList<ItemStack> expected = new ArrayList<>();
        expected.add(EXAMPLE_VALID_ITEM_STACK_1);
        ArrayList result = ItemStackService.combineUniqueItems(arr1, arr2);

        Assert.assertTrue(result.equals(expected));
    }
    @Test
    public void combineUniqueItems_When_TwoDifferentItemStackArrays() {
        ArrayList<ItemStack> arr1 = new ArrayList<>();
        arr1.add(EXAMPLE_VALID_ITEM_STACK_1);
        ArrayList<ItemStack> arr2 = new ArrayList<>();
        arr2.add(EXAMPLE_VALID_ITEM_STACK_2);

        ArrayList<ItemStack> expected = new ArrayList<>();
        expected.add(EXAMPLE_VALID_ITEM_STACK_1);
        expected.add(EXAMPLE_VALID_ITEM_STACK_2);
        ArrayList results = ItemStackService.combineUniqueItems(arr1, arr2);

        Assert.assertTrue(new HashSet<>(results).equals(new HashSet<>(expected)));
    }


}