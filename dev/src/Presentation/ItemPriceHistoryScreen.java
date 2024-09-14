package Presentation;

import Domain.Items.ItemPrice;
import Domain.Items.ItemPriceHistory;
import Domain.Items.ItemStack;
import Domain.Storage;
import Domain.Store;
import External.Constants;
import Service.ItemsService;

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
        this.allExistingItemsOnStore = new ArrayList<>(
                ItemsService.combineUniqueItems(this.storeRef.getAllUniqueItems(), this.storageRef.getAllUniqueItems()));
    }

    @Override
    public int handleMsg() {
        this.displayItems();
        this.out.println(Constants.ITEM_PRICE_HISTORY_INDEX);
        this.out.print(Constants.ENTER_ITEM);
        String userInput = this.in.nextLine();
        this.out.println();

        this.out.println(Constants.YOU_CHOSE + userInput);

        ItemPriceHistory itemHistory = getItemHistory(userInput);

        if (itemHistory == null)
            this.out.println(Constants.NO_ITEMS_IN_STORAGE);
        else {
            this.out.println(Constants.SUCCESS);
            displayHistory(userInput, itemHistory);
        }

        return Constants.USER_NO_INPUT;
    }

    private void displayItems() {

        String LEFT_ALIGN_FORMAT = "| %-11s |%n";

        this.out.println();
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

    private ItemPriceHistory getItemHistory(String itemName) {
        for (ItemStack itemStack : this.allExistingItemsOnStore) {
            if (itemStack.getItemType().getName().equals(itemName)) {
                return itemStack.getItemType().getItemPriceHistory();
            }
        }

        return null;
    }

    public int displayHistory(String itemName, ItemPriceHistory itemHistory) {
        String LEFT_ALIGN_FORMAT = "| %-12s | %-12s | %-12s | %-28s |%n";

        this.out.println();
        this.out.format("+--------------+------------------------------+------------------------------+%n");
        this.out.format("| Item Name   | Store Price    |Supplier Cost   | Update Date                     |%n");
        this.out.format("+--------------+------------------------------+------------------------------+%n");
        for (int discountIndex = 0; discountIndex < itemHistory.getItemPricesHistory().size(); discountIndex++) {
            ItemPrice itemPrice = itemHistory.getItemPricesHistory().get(discountIndex);
            this.out.format(LEFT_ALIGN_FORMAT,
                    itemName, itemPrice.getStorePrice(), itemPrice.getSupplierCost(), itemPrice.getUpdateDate());
        }

        this.out.format("+--------------+--------------+--------------+------------------------------+%n");
        this.out.println();

        return Constants.USER_NO_INPUT;
    }
}
