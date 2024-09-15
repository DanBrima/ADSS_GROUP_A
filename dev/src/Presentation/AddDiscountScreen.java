package Presentation;

import External.Constants;

import java.io.PrintStream;
import java.util.Scanner;

public class AddDiscountScreen extends Screen {
    public AddDiscountScreen(PrintStream out, Scanner in) {
        super(out, in);
    }

    @Override
    public int handleMsg() {
        this.out.println(Constants.ENTER_NEW_DISCOUNT);
        this.out.print(Constants.DISCOUNT_TYPE_CATEGORY_INDEX + ". ");
        this.out.println(Constants.DISCOUNT_TYPE_CATEGORY);
        this.out.print(Constants.DISCOUNT_TYPE_ITEM_INDEX + ". ");
        this.out.println(Constants.DISCOUNT_TYPE_ITEM);

        this.out.print("\n" + Constants.USER_INPUT);
        int userInput = Integer.parseInt(this.in.nextLine());
        return userInput;
    }
}
