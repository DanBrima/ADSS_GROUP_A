package Presentation;

import Domain.Discounts.DiscountsHistory;
import External.Constants;
import Service.DiscountService;

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
        this.out.print(Constants.ENTER_ITEM + " ");
        String itemType = this.in.nextLine();
        this.out.print(Constants.PERCENTAGE + " ");
        int percentage = Integer.parseInt(this.in.nextLine());
        DiscountService.addItemDiscount(discountsHistoryRef, itemType, percentage);
        this.out.println(Constants.SUCCESS);
        return Constants.USER_NO_INPUT;
    }
}
