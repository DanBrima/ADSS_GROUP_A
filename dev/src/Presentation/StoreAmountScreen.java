package Presentation;

import Domain.Discounts.Discount;
import Domain.Items.ItemStack;
import Domain.Shelf;
import Domain.Store;
import External.Constants;

import java.io.PrintStream;
import java.util.Scanner;

public class StoreAmountScreen extends Screen {
    private Store storeRef;

    public StoreAmountScreen(PrintStream out, Scanner in, Store storeRef) {
        super(out, in);
        this.storeRef = storeRef;
    }

    @Override
    public int handleMsg() {
        for (int shelfIndex = 0; shelfIndex < this.storeRef.getShelves().size(); shelfIndex++) {
            this.out.print("\nShelf number #" + (shelfIndex + 1));
            this.displayShelf(this.storeRef.getShelves().get(shelfIndex));
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
            this.out.format(LEFT_ALIGN_FORMAT, itemStack.getItemType().getName(), itemStack.getItemSize());
        }
        this.out.format("+-------------+--------+%n");
        this.out.println();
    }
}
