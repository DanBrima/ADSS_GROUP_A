package Presentation.Inventory;

import External.InventoryConstants;
import Presentation.Screen;

import java.io.PrintStream;
import java.util.Scanner;

public class AddDiscountScreen extends Screen {
    public AddDiscountScreen(PrintStream out, Scanner in) {
        super(out, in);
    }

    @Override
    public int handleMsg() {
        this.out.println(InventoryConstants.ENTER_NEW_DISCOUNT);
        this.out.print(InventoryConstants.DISCOUNT_TYPE_CATEGORY_INDEX + ". ");
        this.out.println(InventoryConstants.DISCOUNT_TYPE_CATEGORY);
        this.out.print(InventoryConstants.DISCOUNT_TYPE_ITEM_INDEX + ". ");
        this.out.println(InventoryConstants.DISCOUNT_TYPE_ITEM);

        this.out.print("\n" + InventoryConstants.USER_INPUT);
        return Integer.parseInt(this.in.nextLine());
    }
}
