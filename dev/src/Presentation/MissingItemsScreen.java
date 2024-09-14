package Presentation;

import Domain.Items.Item;
import Domain.Items.ItemStack;
import Domain.Storage;
import Domain.Store;
import External.Constants;

import java.io.PrintStream;
import java.util.*;

public class MissingItemsScreen extends Screen {
    private Store storeRef;
    private Storage storageRef;

    public MissingItemsScreen(PrintStream out, Scanner in, Store storeRef, Storage storageRef) {
        super(out, in);
        this.storeRef = storeRef;
        this.storageRef = storageRef;
    }

    @Override
    public int handleMsg() {
        ArrayList<ItemStack> allExistingItemsOnStore = new ArrayList<>(
                combineUniqueItems(this.storeRef.getAllUniqueItems(), this.storageRef.getAllUniqueItems()));
        String LEFT_ALIGN_FORMAT = "| %-11s | %-7s | %-9s | %-5s |%n";

        this.out.println();
        this.out.format("+-------------+---------+-----------+-------+%n");
        this.out.format("| Item        | Amount  | Required  | Valid |%n");
        this.out.format("+-------------+---------+-----------+-------+%n");

        for (ItemStack itemStack : allExistingItemsOnStore) {
            int itemRequiredAmount = itemStack.getItemType().getRequiredAmount();
            int itemCurrentAmount = itemStack.getItemSize();

            this.out.format(LEFT_ALIGN_FORMAT, itemStack.getItemType().getName(), itemCurrentAmount,
                    itemRequiredAmount, itemRequiredAmount <= itemCurrentAmount);
            this.out.format("+-------------+---------+-----------+-------+%n");
        }

        this.out.println();

        return Constants.USER_NO_INPUT;

    }


    private ArrayList<ItemStack> combineUniqueItems(ArrayList<ItemStack> list1, ArrayList<ItemStack> list2) {
        HashMap<UUID, ItemStack> itemMap = new HashMap<>();

        // Process the first list
        for (ItemStack itemStack : list1) {
            Item currentItem = itemStack.getItemType();
            UUID currentBarcode = currentItem.getBARCODE();

            if (itemMap.containsKey(currentBarcode)) {
                ItemStack existingItemStack = itemMap.get(currentBarcode);
                for (Item itemInstance : itemStack.getItemsList()) {
                    existingItemStack.addItem(itemInstance);
                }
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
                for (Item itemInstance : itemStack.getItemsList()) {
                    existingItemStack.addItem(itemInstance);
                }
            } else {
                itemMap.put(currentBarcode, itemStack);
            }
        }

        return new ArrayList<>(itemMap.values());
    }

}
