package Domain;

import Domain.Items.ItemStack;
import Domain.Items.StackLocation;

import java.util.ArrayList;

public class Storage {
    private ArrayList<ItemStack> inventory;

    private ArrayList<ItemStack> defectiveItems;

    // Constructor for empty inventory
    public Storage() {
        this.inventory = new ArrayList<ItemStack>();
        this.defectiveItems = new ArrayList<ItemStack>();
    }

    // Constructor from item stack
    public Storage(ItemStack itemStack) {
        this.inventory = new ArrayList<ItemStack>();
        this.addItemStack(itemStack);

        this.defectiveItems = new ArrayList<ItemStack>();
    }

    public void addItemStack(ItemStack itemStack) {
        this.inventory.add(itemStack);
        itemStack.setLocation(new StackLocation(this.inventory.size() -1));
    }

    public void addDefectiveItemStack(ItemStack itemStack) {
        this.defectiveItems.add(itemStack);
        itemStack.setLocation(new StackLocation(this.defectiveItems.size() -1));
    }

    public ArrayList<ItemStack> getInventory() {
        return (ArrayList<ItemStack>) inventory.clone();
    }

    public ArrayList<ItemStack> getDefectiveItems() {
        return (ArrayList<ItemStack>) defectiveItems.clone();
    }
}
