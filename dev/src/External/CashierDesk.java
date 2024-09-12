package External;

import Domain.Discounts.DiscountsHistory;
import Domain.Discounts.ItemDiscount;
import Domain.Items.Item;
import Presentation.DefaultMenuScreen;
import Presentation.DiscountsHistoryScreen;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Scanner;

public class CashierDesk {
    private final PrintStream out;
    private final Scanner in;

    public CashierDesk(PrintStream out, Scanner in) {
        this.in = in;
        this.out = out;

    }

    public void turnOn() throws Exception {
        //TODO: maybe there is some default values to generate here (like history or something)
        DiscountsHistory discountsHistory = new DiscountsHistory();
        discountsHistory.discountList.add(new ItemDiscount(10, new Date(), new Date(),
                new Item("test", new BigDecimal(10), "more test", new BigDecimal(100), 10, null)));

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
                DiscountsHistoryScreen discountsHistoryScreen = new DiscountsHistoryScreen(this.out, this.in, discountsHistory);
                discountsHistoryScreen.handleMsg();
            }
        }
    }
}
