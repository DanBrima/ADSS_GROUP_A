package Presentation;
import External.Constants;
import java.io.PrintStream;
import java.util.Scanner;

public class DefaultMenuScreen extends Screen {

    public DefaultMenuScreen(PrintStream out, Scanner in) {
        super(out, in);
    }

    @Override
    public int handleMsg() {
        this.out.println(Constants.DEFAULT_MSG);

        this.out.print(Constants.CURRENT_ITEMS_INDEX + ". ");
        this.out.println(Constants.CURRENT_ITEMS);

        this.out.print(Constants.MISSING_ITEMS_RECORD_INDEX + ". ");
        this.out.println(Constants.MISSING_ITEMS_RECORD);

        this.out.print(Constants.DISCOUNTS_HISTORY_INDEX + ". ");
        this.out.println(Constants.DISCOUNTS_HISTORY);

        this.out.print(Constants.DISPLAY_DEFECTIVE_ITEMS_INDEX + ". ");
        this.out.println(Constants.DISPLAY_DEFECTIVE_ITEMS);

        this.out.print(Constants.TRANSFER_ITEMS_INDEX + ". ");
        this.out.println(Constants.TRANSFER_ITEMS);

        this.out.print(Constants.REMOVE_ITEMS_INDEX + ". ");
        this.out.println(Constants.REMOVE_ITEMS);

        this.out.print(Constants.ITEM_PRICE_HISTORY_INDEX + ". ");
        this.out.println(Constants.ITEM_PRICE_HISTORY);

        this.out.print(Constants.TURN_OFF_INDEX + ". ");
        this.out.println(Constants.TURN_OFF);

        this.out.print("\n" + Constants.USER_INPUT);
        int userInput = Integer.parseInt(this.in.nextLine());
        return userInput;
    }
}
