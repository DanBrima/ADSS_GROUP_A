package Presentation.Inventory;

import Domain.ItemStack;
import Domain.Storage;
import Domain.Store;
import External.InventoryConstants;
import Presentation.Screen;
import Service.ItemService;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class ItemPriceHistoryScreen extends Screen {
    private Store storeRef;
    private Storage storageRef;
    private ArrayList<ItemStack> allExistingItemsOnStore;

    public ItemPriceHistoryScreen(PrintStream out, Scanner in, Store storeRef, Storage storageRef) {
        super(out, in);
        this.storeRef = storeRef;
        this.storageRef = storageRef;

        this.allExistingItemsOnStore = ItemService.getAllUniqueItemsWithoutDefective(this.storageRef, this.storeRef);
    }

    @Override
    public int handleMsg() {
        this.displayItems();
        this.out.println(InventoryConstants.ITEM_PRICE_HISTORY);
        this.out.print(InventoryConstants.ENTER_ITEM);
        String userInput = this.in.nextLine();
        this.out.println();

        this.out.println(InventoryConstants.YOU_CHOSE + userInput);
        //TODO: Change the method to match the class diagram and probably select from DB
        //ItemPriceHistory itemHistory = getItemHistory(userInput);

//        if (itemHistory == null)
//            this.out.println(InventoryConstants.NO_ITEMS_IN_STORAGE);
//        else {
//            this.out.println(InventoryConstants.SUCCESS);
//            displayHistory(userInput, itemHistory);
//        }

        return InventoryConstants.USER_NO_INPUT;
    }

    private void displayItems() {

        String LEFT_ALIGN_FORMAT = "| %-11s |%n";

        this.out.println();
        this.out.println("Please choose item to display from the following items");
        this.out.format("+-------------+%n");
        this.out.format("| Item        |%n");
        this.out.format("+-------------+%n");
        for (int itemStackIndex = 0; itemStackIndex < allExistingItemsOnStore.size(); itemStackIndex++) {
            ItemStack itemStack = allExistingItemsOnStore.get(itemStackIndex);
            this.out.format(LEFT_ALIGN_FORMAT, itemStack.getItemType().getName());
        }
        this.out.format("+-------------+%n");
        this.out.println();
    }

//    private ItemPriceHistory getItemHistory(String itemName) {
//        for (ItemStack itemStack : this.allExistingItemsOnStore) {
//            if (itemStack.getItemType().getName().equals(itemName)) {
//                return itemStack.getItemType().getItemPriceHistory();
//            }
//        }
//
//        return null;
//    }
//
//    public int displayHistory(String itemName, ItemPriceHistory itemHistory) {
//        String LEFT_ALIGN_FORMAT = "| %-12s | %-14s | %-14s | %-30s |%n";
//
//        this.out.println();
//        this.out.format("+--------------+----------------+----------------+--------------------------------+%n");
//        this.out.format("| Item Name    | Store Price    |Supplier Cost   | Update Date                    |%n");
//        this.out.format("+--------------+----------------+----------------+--------------------------------+%n");
//        for (int discountIndex = 0; discountIndex < itemHistory.getItemPricesHistory().size(); discountIndex++) {
//            ItemPrice itemPrice = itemHistory.getItemPricesHistory().get(discountIndex);
//            this.out.format(LEFT_ALIGN_FORMAT,
//                    itemName, itemPrice.getStorePrice(), itemPrice.getSupplierCost(), itemPrice.getUpdateDate());
//        }
//
//        this.out.format("+--------------+----------------+----------------+--------------------------------+%n");
//        this.out.println();
//
//        return InventoryConstants.USER_NO_INPUT;
//    }
}
