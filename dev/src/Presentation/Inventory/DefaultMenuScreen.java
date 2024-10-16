package Presentation.Inventory;
import External.InventoryConstants;
import External.SuppliersConstants;
import Presentation.Screen;

import java.io.PrintStream;
import java.util.Scanner;

public class DefaultMenuScreen extends Screen {

    public DefaultMenuScreen(PrintStream out, Scanner in) {
        super(out, in);
    }

    @Override
    public int handleMsg() {
        this.out.println(InventoryConstants.DEFAULT_MSG);

        this.out.print(InventoryConstants.CURRENT_ITEMS_INDEX + ". ");
        this.out.println(InventoryConstants.CURRENT_ITEMS);

        this.out.print(InventoryConstants.MISSING_ITEMS_RECORD_INDEX + ". ");
        this.out.println(InventoryConstants.MISSING_ITEMS_RECORD);

        this.out.print(InventoryConstants.DISCOUNTS_HISTORY_INDEX + ". ");
        this.out.println(InventoryConstants.DISCOUNTS_HISTORY);

        this.out.print(InventoryConstants.DISPLAY_DEFECTIVE_ITEMS_INDEX + ". ");
        this.out.println(InventoryConstants.DISPLAY_DEFECTIVE_ITEMS);

        this.out.print(InventoryConstants.TRANSFER_ITEMS_INDEX + ". ");
        this.out.println(InventoryConstants.TRANSFER_ITEMS);

        this.out.print(InventoryConstants.REMOVE_ITEMS_INDEX + ". ");
        this.out.println(InventoryConstants.REMOVE_ITEMS);

        this.out.print(InventoryConstants.ITEM_PRICE_HISTORY_INDEX + ". ");
        this.out.println(InventoryConstants.ITEM_PRICE_HISTORY);

        this.out.print(InventoryConstants.CREATE_NEW_DISCOUNT_INDEX + ". ");
        this.out.println(InventoryConstants.CREATE_NEW_DISCOUNT);

        this.out.print(InventoryConstants.RETURN_TO_MAIN_MENU_INVENTORY_INDEX + ". ");
        this.out.println(InventoryConstants.RETURN_TO_MAIN_MENU_INVENTORY);

        this.out.print("\n" + InventoryConstants.USER_INPUT);
        int userInput = Integer.parseInt(this.in.nextLine());
        return userInput;
    }
}
