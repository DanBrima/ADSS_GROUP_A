package Service;

import Domain.Items.Item;
import Domain.Items.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class ItemsService {
    public static ArrayList<ItemStack> combineUniqueItems(ArrayList<ItemStack> list1, ArrayList<ItemStack> list2) {
        HashMap<UUID, ItemStack> itemMap = new HashMap<>();

        // Process the first list
        for (ItemStack itemStack : list1) {
            Item currentItem = itemStack.getItemType();
            UUID currentBarcode = currentItem.getBARCODE();

            if (itemMap.containsKey(currentBarcode)) {
                ItemStack existingItemStack = itemMap.get(currentBarcode);
                existingItemStack.addItems(currentBarcode, itemStack.getItemCount());
            } else {
                itemMap.put(currentBarcode, itemStack);
            }
        }

        // Process the second list
        for (ItemStack itemStack : list2) {
            Item currentItem = itemStack.getItemType();
            UUID currentBarcode = currentItem.getBARCODE();

            if (itemMap.containsKey(currentBarcode)) {
                ItemStack existingItemStack = itemMap.get(currentBarcode);
                existingItemStack.addItems(currentBarcode, itemStack.getItemCount());
            } else {
                itemMap.put(currentBarcode, itemStack);
            }
        }

        return new ArrayList<>(itemMap.values());
    }
}
