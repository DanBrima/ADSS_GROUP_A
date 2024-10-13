package Service;

import Domain.ProductInStore;
import Domain.ItemStack;
import Domain.Shelf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class ItemStackService {
    public static ArrayList<ItemStack> combineUniqueItems(ArrayList<ItemStack> list1, ArrayList<ItemStack> list2) {
        HashMap<UUID, ItemStack> itemMap = new HashMap<>();

        // Process the first list
        for (ItemStack itemStack : list1) {
            ProductInStore currentItem = itemStack.getItemType();
            UUID currentBarcode = currentItem.getBarcode();

            if (itemMap.containsKey(currentBarcode)) {
                ItemStack existingItemStack = itemMap.get(currentBarcode);
                existingItemStack.addItems(currentBarcode, itemStack.getItemCount());
            } else {
                itemMap.put(currentBarcode, itemStack);
            }
        }

        // Process the second list
        for (ItemStack itemStack : list2) {
            ProductInStore currentItem = itemStack.getItemType();
            UUID currentBarcode = currentItem.getBarcode();

            if (itemMap.containsKey(currentBarcode)) {
                ItemStack existingItemStack = itemMap.get(currentBarcode);
                existingItemStack.addItems(currentBarcode, itemStack.getItemCount());
            } else {
                itemMap.put(currentBarcode, itemStack);
            }
        }

        return new ArrayList<>(itemMap.values());
    }

    public static ArrayList<ItemStack> getAllUniqueItemsFromStore(ArrayList<Shelf> shelves) {
        ArrayList<ItemStack> uniqueItems = new ArrayList<>();
        HashMap<UUID, ItemStack> itemMap = new HashMap<>();

        for (Shelf shelf : shelves) {
            for (ItemStack itemStack : shelf.getItemsOnShelf()) {
                ProductInStore currentItem = itemStack.getItemType();
                UUID currentBarcode = currentItem.getBarcode();

                // If the ProductInStore is already in the map, update the quantity
                if (itemMap.containsKey(currentBarcode)) {
                    ItemStack existingItem = itemMap.get(currentBarcode);
                    existingItem.addItems(currentBarcode, itemStack.getItemCount());
                } else {
                    // Otherwise, add the new ProductInStore with its quantity
                    itemMap.put(currentBarcode, itemStack);
                }
            }
        }

        uniqueItems.addAll(itemMap.values());
        return uniqueItems;
    }
}
