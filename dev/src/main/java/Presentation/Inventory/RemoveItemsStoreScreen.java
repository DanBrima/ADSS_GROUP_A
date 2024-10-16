package Presentation.Inventory;

import Domain.ItemStack;
import Domain.Shelf;
import Domain.Store;
import External.InventoryConstants;
import Presentation.Screen;
import Service.ItemService;

import java.io.PrintStream;
import java.util.Scanner;

public class RemoveItemsStoreScreen extends Screen {
    private Store storeRef;

    public RemoveItemsStoreScreen(PrintStream out, Scanner in, Store storeRef) {
        super(out, in);
        this.storeRef = storeRef;
    }

    @Override
    public int handleMsg() {
        String LEFT_ALIGN_FORMAT = "| %-11s | %-6s |%n";

        for (int shelfIndex = 0; shelfIndex < this.storeRef.getShelves().size(); shelfIndex++) {
            this.out.print("\nShelf number #" + (shelfIndex + 1));
            this.displayShelf(this.storeRef.getShelves().get(shelfIndex));
        }

        this.out.println(InventoryConstants.CHOOSE_TYPE_AMOUNT);
        this.out.print("Type: " );
        String userType = this.in.nextLine();
        this.out.print("Amount: " );
        int userAmount = Integer.parseInt(this.in.nextLine());

        if (ItemService.removeItemFromStore(this.storeRef, userType, userAmount)) {
            this.out.println(InventoryConstants.SUCCESS);
        } else {
            this.out.println("\n" + InventoryConstants.INVALID_INPUT + "\n");
        }

        return InventoryConstants.USER_NO_INPUT;
    }

    private void displayShelf(Shelf shelf) {
        String LEFT_ALIGN_FORMAT = "| %-11s | %-6s |%n";

        this.out.println();
        this.out.format("+-------------+--------+%n");
        this.out.format("| Item        | Amount |%n");
        this.out.format("+-------------+--------+%n");
        for (int itemStackIndex = 0; itemStackIndex < shelf.getItemsOnShelf().size(); itemStackIndex++) {
            ItemStack itemStack = shelf.getItemsOnShelf().get(itemStackIndex);
            this.out.format(LEFT_ALIGN_FORMAT, itemStack.getItemType().getName(), itemStack.getItemCount());
        }
        this.out.format("+-------------+--------+%n");
        this.out.println();
    }
}
