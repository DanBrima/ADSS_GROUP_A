package Presentation.Inventory;

import Domain.DiscountsHistory;
import External.InventoryConstants;
import Presentation.Screen;

import java.io.PrintStream;
import java.util.Scanner;

public class AddItemDiscountScreen extends Screen {
    DiscountsHistory discountsHistoryRef;

    public AddItemDiscountScreen(PrintStream out, Scanner in, DiscountsHistory discountsHistoryRef) {
        super(out, in);
        this.discountsHistoryRef = discountsHistoryRef;
    }

    @Override
    public int handleMsg() {
        this.out.print(InventoryConstants.ENTER_ITEM + " ");
        String itemType = this.in.nextLine();
        this.out.print(InventoryConstants.PERCENTAGE + " ");
        int percentage = Integer.parseInt(this.in.nextLine());
        //TODO: add discount to item
//        DiscountService.addItemDiscount(discountsHistoryRef, itemType, percentage);
        this.out.println(InventoryConstants.SUCCESS);
        return InventoryConstants.USER_NO_INPUT;
    }
}
