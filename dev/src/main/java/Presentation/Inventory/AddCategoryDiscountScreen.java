package Presentation.Inventory;

import Domain.DiscountsHistory;
import Presentation.Screen;

import java.io.PrintStream;
import java.util.Scanner;
// TODO: Change the class to match the class diagram
public class AddCategoryDiscountScreen extends Screen {
    DiscountsHistory discountsHistoryRef;

    public AddCategoryDiscountScreen(PrintStream out, Scanner in, DiscountsHistory discountsHistoryRef) {
        super(out, in);
        this.discountsHistoryRef = discountsHistoryRef;
    }

//    @Override
//    public int handleMsg() {
//        this.out.print(InventoryConstants.CATEGORY + " ");
//        String category = this.in.nextLine();
//        this.out.print(InventoryConstants.PERCENTAGE + " ");
//        int percentage = Integer.parseInt(this.in.nextLine());
//        DiscountService.addCategoryDiscount(discountsHistoryRef, category, percentage);
//        this.out.println(InventoryConstants.SUCCESS);
//        return InventoryConstants.USER_NO_INPUT;
//    }
//

}
