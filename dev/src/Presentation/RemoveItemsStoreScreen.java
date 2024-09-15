package Presentation;

import Domain.Items.ItemStack;
import Domain.Shelf;
import Domain.Storage;
import Domain.Store;
import External.Constants;
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

        this.out.println(Constants.CHOOSE_TYPE_AMOUNT);
        this.out.print("Type: " );
        String userType = this.in.nextLine();
        this.out.print("Amount: " );
        int userAmount = Integer.parseInt(this.in.nextLine());

        if (ItemService.removeItemFromStore(this.storeRef, userType, userAmount)) {
            this.out.println(Constants.SUCCESS);
        } else {
            this.out.println("\n" + Constants.INVALID_INPUT + "\n");
        }

        return Constants.USER_NO_INPUT;
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
