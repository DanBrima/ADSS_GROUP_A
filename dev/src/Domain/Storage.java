package Domain;

import Domain.Items.Item;
import Domain.Items.ItemStack;
import Domain.Items.StackLocation;

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
        itemStack.setLocation(new StackLocation(this.inventory.size() -1));
    }

    public void addDefectiveItemStack(ItemStack itemStack) {
        this.defectiveItems.add(itemStack);
        itemStack.setLocation(new StackLocation(this.defectiveItems.size() -1));
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

    public ArrayList<ItemStack> getAllUniqueItems() {

        //TODO: add condition if defected or address it somehow else
        ArrayList<ItemStack> uniqueItems = new ArrayList<>();
        HashMap<UUID, ItemStack> itemMap = new HashMap<>();

        for (ItemStack itemStack : this.inventory) {
            Item currentItem = itemStack.getItemType();
            UUID currentBarcode = currentItem.getBARCODE();
            int quantity = itemStack.getItemCount();

            // If the item is already in the map, update the quantity
            if (itemMap.containsKey(currentBarcode)) {
                ItemStack existingItemStack = itemMap.get(currentBarcode);
                existingItemStack.addItems(currentBarcode, itemStack.getItemCount());
            } else {
                // Otherwise, add the new item stack
                ItemStack newItemStack = new ItemStack(currentItem, quantity);
                itemMap.put(currentBarcode, newItemStack);
            }
        }

        uniqueItems.addAll(itemMap.values());
        return uniqueItems;
    }

    public ArrayList<ItemStack> getDefectiveItems() {
        return (ArrayList<ItemStack>) defectiveItems.clone();
    }
}
