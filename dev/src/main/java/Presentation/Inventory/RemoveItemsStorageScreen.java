package Presentation.Inventory;

import Domain.ItemStack;
import Domain.Storage;
import External.InventoryConstants;

import Presentation.Screen;
import Service.ItemService;

import java.io.PrintStream;
import java.util.Scanner;

public class RemoveItemsStorageScreen extends Screen {
    private Storage storageRef;

    public RemoveItemsStorageScreen(PrintStream out, Scanner in, Storage storageRef) {
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
            this.out.format(LEFT_ALIGN_FORMAT, itemStack.getItemType().getName(), itemStack.getItemCount());
        }
        this.out.format("+-------------+--------+%n");
        this.out.println();

        this.out.println(InventoryConstants.CHOOSE_TYPE_AMOUNT);
        this.out.print(InventoryConstants.ENTER_ITEM + " ");
        String userType = this.in.nextLine();
        this.out.print(InventoryConstants.ENTER_AMOUNT + " ");
        int userAmount = Integer.parseInt(this.in.nextLine());

        if (ItemService.removeItemFromStorage(this.storageRef, userType, userAmount)) {
            this.out.println(InventoryConstants.SUCCESS);
        } else {
            this.out.println("\n" + InventoryConstants.INVALID_INPUT + "\n");
        }

        return InventoryConstants.USER_NO_INPUT;
    }
}
