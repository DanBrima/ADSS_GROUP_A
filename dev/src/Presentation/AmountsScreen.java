package Presentation;

import External.Constants;

import java.io.PrintStream;
import java.util.Scanner;

public class AmountsScreen extends Screen {
    public AmountsScreen(PrintStream out, Scanner in) {
        super(out, in);
    }

    @Override
    public int handleMsg() {
        this.out.print(Constants.DISPLAY_STORE_ITEMS_INDEX + ". ");
        this.out.println(Constants.DISPLAY_STORE_ITEMS);

        this.out.print(Constants.DISPLAY_STORAGE_ITEMS_INDEX + ". ");
        this.out.println(Constants.DISPLAY_STORAGE_ITEMS);

        this.out.print("\n" + Constants.USER_INPUT);
        int userInput = this.in.nextInt();
        return userInput;
    }
}
