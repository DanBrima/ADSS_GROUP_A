package Presentation.Inventory;

import Domain.Category;
import Domain.ItemStack;
import Domain.Shelf;
import Domain.Store;
import External.InventoryConstants;
import Presentation.Screen;

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

        return InventoryConstants.USER_NO_INPUT;
    }

    private void displayShelf(Shelf shelf) {
        String LEFT_ALIGN_FORMAT = "| %-11s | %-16s | %-11s | %-6s |%n";

        this.out.println();
        this.out.format("+-------------+------------------+-------------+--------+%n");
        this.out.format("| Item        | Supplier         | Category    | Amount |%n");
        this.out.format("+-------------+------------------+-------------+--------+%n");
        for (int itemStackIndex = 0; itemStackIndex < shelf.getItemsOnShelf().size(); itemStackIndex++) {
            ItemStack itemStack = shelf.getItemsOnShelf().get(itemStackIndex);

            this.out.format(LEFT_ALIGN_FORMAT, itemStack.getItemType().getName(), itemStack.getItemType().getSupplier(), itemStack.getItemType().getCategory().fullCategoryName(), itemStack.getItemCount());
        }

        this.out.format("+-------------+------------------+-------------+--------+%n");
        this.out.println();
    }
}
