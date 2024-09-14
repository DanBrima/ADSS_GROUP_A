package Domain;

import Domain.Items.Item;
import Domain.Items.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Store {
    private ArrayList<Shelf> shelves;

    // Constructor for empty inventory
    public Store() {
        this.shelves = new ArrayList<Shelf>();
    }

    // Constructor from Shelf
    public Store(Shelf shelf) {
        this.shelves = new ArrayList<Shelf>();
        this.addShelf(shelf);
    }

    public void addShelf(Shelf shelf) {
        int shelfIndex = this.shelves.size();
        shelf.setShelfId(shelfIndex);
        this.shelves.add(shelf);
    }

    public ArrayList<Shelf> getShelves() {
        return (ArrayList<Shelf>) shelves.clone();
    }

    public int getSpecificItemAmount(Item wantedItem) {
        int amount = 0;
        for (Shelf shelf : this.getShelves()) {
            for (ItemStack itemStack : shelf.getItemsOnShelf()) {
                if (itemStack.getItemType().getBARCODE() == wantedItem.getBARCODE())
                    amount += itemStack.getItemCount();
            }
        }

        return amount;
    }

    public ArrayList<ItemStack> getAllUniqueItems() {
        ArrayList<ItemStack> uniqueItems = new ArrayList<>();
        HashMap<UUID, ItemStack> itemMap = new HashMap<>();

        for (Shelf shelf : shelves) {
            for (ItemStack itemStack : shelf.getItemsOnShelf()) {
                Item currentItem = itemStack.getItemType();
                UUID currentBarcode = currentItem.getBARCODE();

                // If the item is already in the map, update the quantity
                if (itemMap.containsKey(currentBarcode)) {
                    ItemStack existingItem = itemMap.get(currentBarcode);
                    existingItem.addItems(currentBarcode, itemStack.getItemCount());
                } else {
                    // Otherwise, add the new item with its quantity
                    itemMap.put(currentBarcode, itemStack);
                }
            }
        }

        uniqueItems.addAll(itemMap.values());
        return uniqueItems;
    }
}
