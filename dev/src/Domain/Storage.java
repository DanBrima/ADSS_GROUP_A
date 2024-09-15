package Domain;

import Domain.Items.Item;
import Domain.Items.ItemStack;
import Domain.Items.StackLocation;
import Service.ItemStackService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

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
        itemStack.setLocation(new StackLocation("inventory", this.inventory.size() -1));
    }

    public void addDefectiveItemStack(ItemStack itemStack) {
        this.defectiveItems.add(itemStack);
        itemStack.setLocation(new StackLocation("defect",this.defectiveItems.size() -1));
    }

    public ArrayList<ItemStack> getInventory() {
        return (ArrayList<ItemStack>) inventory.clone();
    }

    public ItemStack pullItemsStack(String itemName) {
        for (int i = 0; i < this.inventory.size(); i++) {
            ItemStack itemStack = this.inventory.get(i);
            if (itemStack.getItemType().getName().equals(itemName)) {
                return this.inventory.remove(i);
            }
        }

        return null;
    }

    public ArrayList<ItemStack> getDefectiveItems() {
        return (ArrayList<ItemStack>) defectiveItems.clone();
    }
}
