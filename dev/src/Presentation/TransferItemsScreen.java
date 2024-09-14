package Presentation;

import Domain.Items.ItemStack;
import Domain.Storage;
import Domain.Store;
import External.Constants;

import java.io.PrintStream;
import java.util.Scanner;

public class TransferItemsScreen extends Screen {
    private Store storeRef;
    private Storage storageRef;

    public TransferItemsScreen(PrintStream out, Scanner in, Store storeRef, Storage storageRef) {
        super(out, in);
        this.storeRef = storeRef;
        this.storageRef = storageRef;
    }

    @Override
    public int handleMsg() {
        this.displayStorageItems();
        this.out.println(Constants.TRANSFER_FROM_STORAGE);
        this.out.print(Constants.ENTER_ITEM);
        String userInput = this.in.nextLine();
        this.out.println();

        this.out.println(Constants.YOU_CHOSE + userInput);

        ItemStack itemStack = this.storageRef.pullItemsStack(userInput);

        if (itemStack == null)
            this.out.println(Constants.NO_ITEMS_IN_STORAGE);
        else {
            this.out.println(Constants.SUCCESS);
            this.storeRef.getShelves().get(0).addItemStack(itemStack);
        }

        return Constants.USER_NO_INPUT;
    }

    private void displayStorageItems() {
        String LEFT_ALIGN_FORMAT = "| %-11s | %-6s |%n";

        this.out.println();
        this.out.format("+-------------+--------+%n");
        this.out.format("| Item        | Amount |%n");
        this.out.format("+-------------+--------+%n");
        for (int itemStackIndex = 0; itemStackIndex < this.storageRef.getInventory().size(); itemStackIndex++) {
            ItemStack itemStack = this.storageRef.getInventory().get(itemStackIndex);
            this.out.format(LEFT_ALIGN_FORMAT, itemStack.getItemType().getName(), itemStack.getItemsList().size());
        }
        this.out.format("+-------------+--------+%n");
        this.out.println();
    }
}
