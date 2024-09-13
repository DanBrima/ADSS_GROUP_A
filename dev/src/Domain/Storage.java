package Domain;

import Domain.Items.Item;
import Domain.Items.ItemInstance;
import Domain.Items.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

public class Storage {
    private ArrayList<ItemStack> inventory;

    // Constructor for empty inventory
    public Storage() {
        this.inventory = new ArrayList<ItemStack>();
    }

    // Constructor from item stack
    public Storage(ItemStack itemStack) {
        this.inventory = new ArrayList<ItemStack>();
        this.addItemStack(itemStack);
    }

    public void addItemStack(ItemStack itemStack) {
        this.inventory.add(itemStack);
    }

    public ArrayList<ItemStack> getInventory() {
        return (ArrayList<ItemStack>) inventory.clone();
    }

    public ArrayList<ItemStack> getAllUniqueItems() {
        ArrayList<ItemStack> uniqueItems = new ArrayList<>();
        HashMap<UUID, ItemStack> itemMap = new HashMap<>();

        for (ItemStack itemStack : this.inventory) {
            Item currentItem = itemStack.getItemType();
            UUID currentBarcode = currentItem.getBARCODE();
            int quantity = itemStack.getItemSize();

            // If the item is already in the map, update the quantity
            if (itemMap.containsKey(currentBarcode)) {
                ItemStack existingItemStack = itemMap.get(currentBarcode);
                for (ItemInstance itemInstance : itemStack.getItemsList()) {
                    existingItemStack.addItem(itemInstance);
                }
            } else {
                // Otherwise, add the new item stack
                ItemStack newItemStack = new ItemStack(new ItemInstance(currentItem), quantity);
                itemMap.put(currentBarcode, newItemStack);
            }
        }

        uniqueItems.addAll(itemMap.values());
        return uniqueItems;
    }
}
