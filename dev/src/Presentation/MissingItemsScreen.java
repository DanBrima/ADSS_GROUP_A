package Presentation;

import Domain.Items.Item;
import Domain.Items.ItemStack;
import Domain.Storage;
import Domain.Store;
import External.Constants;
import Service.ItemService;
import Service.ItemStackService;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class MissingItemsScreen extends Screen {
    private Store storeRef;
    private Storage storageRef;
    private ItemService itemService;

    public MissingItemsScreen(PrintStream out, Scanner in, Store storeRef, Storage storageRef, ItemService itemService) {
        super(out, in);
        this.storeRef = storeRef;
        this.storageRef = storageRef;
        this.itemService = itemService;
    }

    @Override
    public int handleMsg() {
        ArrayList<ItemStack> allExistingItemsOnStore = itemService.getAllUniqueItems();
        String LEFT_ALIGN_FORMAT = "| %-11s | %-7s | %-9s | %-5s |%n";

        this.out.println();
        this.out.format("+-------------+---------+-----------+-------+%n");
        this.out.format("| Item        | Amount  | Required  | Valid |%n");
        this.out.format("+-------------+---------+-----------+-------+%n");

        for (ItemStack itemStack : allExistingItemsOnStore) {
            int itemRequiredAmount = itemStack.getItemType().getRequiredAmount();
            int itemCurrentAmount = itemStack.getItemCount();

            this.out.format(LEFT_ALIGN_FORMAT, itemStack.getItemType().getName(), itemCurrentAmount,
                    itemRequiredAmount, itemRequiredAmount <= itemCurrentAmount);
            this.out.format("+-------------+---------+-----------+-------+%n");
        }

        this.out.println();

        return Constants.USER_NO_INPUT;

    }




}
