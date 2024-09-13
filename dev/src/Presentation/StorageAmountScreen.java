package Presentation;

import Domain.Items.ItemStack;
import Domain.Shelf;
import Domain.Storage;
import External.Constants;

import java.io.PrintStream;
import java.util.Scanner;

public class StorageAmountScreen extends Screen{

    private Storage storageRef;

    public StorageAmountScreen(PrintStream out, Scanner in, Storage storageRef) {
        super(out, in);
        this.storageRef = storageRef;
    }

    @Override
    public int handleMsg() {
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

        return Constants.USER_NO_INPUT;
    }
}
