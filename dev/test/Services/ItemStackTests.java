package Services;

import Domain.Items.Item;
import Domain.Items.ItemStack;
import Service.ItemStackService;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;

public class ItemStackTests {
     private Item EXAMPLE_VALID_ITEM_1 = new Item("test1", new BigDecimal(1), "supplier1", new BigDecimal(1), 1, null);
     private Item EXAMPLE_VALID_ITEM_2 = new Item("test2", new BigDecimal(1), "supplier2", new BigDecimal(1), 1, null);
     private ItemStack EXAMPLE_VALID_ITEM_STACK_1 = new ItemStack(EXAMPLE_VALID_ITEM_1);
     private ItemStack EXAMPLE_VALID_ITEM_STACK_2 = new ItemStack(EXAMPLE_VALID_ITEM_2);

     @Before
    public void setUp() {
         this.EXAMPLE_VALID_ITEM_1 = new Item("test1", new BigDecimal(1), "supplier1", new BigDecimal(1), 1, null);
         this.EXAMPLE_VALID_ITEM_2 = new Item("test2", new BigDecimal(1), "supplier2", new BigDecimal(1), 1, null);
         this.EXAMPLE_VALID_ITEM_STACK_1 = new ItemStack(EXAMPLE_VALID_ITEM_1);
         this.EXAMPLE_VALID_ITEM_STACK_2 = new ItemStack(EXAMPLE_VALID_ITEM_2);
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
