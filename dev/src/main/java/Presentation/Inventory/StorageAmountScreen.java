package Presentation.Inventory;

import Domain.ItemStack;
import Domain.Storage;
import External.InventoryConstants;
import Presentation.Screen;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class StorageAmountScreen extends Screen {

    private Storage storageRef;

    public StorageAmountScreen(PrintStream out, Scanner in, Storage storageRef) {
        super(out, in);
        this.storageRef = storageRef;
    }

    @Override
    public int handleMsg() {

        this.out.print("\nInventory");
        this.displayItemsFrom(new ArrayList<>(this.storageRef.getInventory()));
        this.out.print("\nDefected Items");
        this.displayItemsFrom(new ArrayList<>(this.storageRef.getDefectiveItems()));

        return InventoryConstants.USER_NO_INPUT;
    }

    private void displayItemsFrom(ArrayList<ItemStack> itemStacks) {
        String LEFT_ALIGN_FORMAT = "| %-11s | %-16s | %-11s | %-6s |%n";

        this.out.println();
        this.out.format("+-------------+------------------+-------------+--------+%n");
        this.out.format("| Item        | Supplier         | Category    | Amount |%n");
        this.out.format("+-------------+------------------+-------------+--------+%n");
        for (int itemStackIndex = 0; itemStackIndex < itemStacks.size(); itemStackIndex++) {
            ItemStack itemStack = itemStacks.get(itemStackIndex);
            this.out.format(LEFT_ALIGN_FORMAT, itemStack.getItemType().getName(), itemStack.getItemType().getSupplier(), itemStack.getItemType().getCategory().getName(), itemStack.getItemCount());
        }

        this.out.format("+-------------+------------------+-------------+--------+%n");
        this.out.println();
    }
}
