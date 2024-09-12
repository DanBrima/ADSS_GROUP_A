package External;

import Presentation.DefaultMenuScreen;

import java.io.PrintStream;
import java.util.Scanner;

public class CashierDesk {
    private final PrintStream out;
    private final Scanner in;

    public CashierDesk(PrintStream out, Scanner in) {
        this.in = in;
        this.out = out;

    }

    public void turnOn() {
        // Activate Menu
        DefaultMenuScreen defaultMenuScreen = new DefaultMenuScreen(this.out, this.in);
        int userInput = defaultMenuScreen.handleMsg();
        switch (userInput) {
            case Constants.CURRENT_ITEMS_INDEX: {
                //TODO: create this screen
            }
            case Constants.DEAL_HISTORY_INDEX: {
                //TODO: create this screen
            }
            case Constants.MISSING_ITEMS_RECORD_INDEX: {
                //TODO: create this screen
            }
            case Constants.DISCOUNTS_HISTORY_INDEX: {
                //TODO: create this screen
            }
        }
        //TODO: maybe there is some defualt values to generate here (like history or something)
    }
}
