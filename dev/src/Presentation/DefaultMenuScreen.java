package Presentation;
import External.Constants;
import java.io.PrintStream;
import java.util.Scanner;

public class DefaultMenuScreen implements MenuScreen {

    private final PrintStream out;
    private final Scanner in;

    public DefaultMenuScreen(PrintStream out, Scanner in) {
        this.out = out;
        this.in = in;
    }

    public int handleMsg() {
        this.out.println(Constants.DEFAULT_MSG);

        this.out.print(Constants.CURRENT_ITEMS_INDEX + ". ");
        this.out.println(Constants.CURRENT_ITEMS);

        this.out.print(Constants.MISSING_ITEMS_RECORD_INDEX + ". ");
        this.out.println(Constants.MISSING_ITEMS_RECORD);

        this.out.print(Constants.DEAL_HISTORY_INDEX + ". ");
        this.out.println(Constants.DEAL_HISTORY);

        this.out.print(Constants.DISCOUNTS_HISTORY_INDEX + ". ");
        this.out.println(Constants.DISCOUNTS_HISTORY);

        this.out.print("\n" + Constants.USER_INPUT);
        int userInput = this.in.nextInt();
        this.in.close();
        return userInput;
    }
}
