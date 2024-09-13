package Presentation;

import Domain.Discounts.Discount;
import Domain.Discounts.DiscountsHistory;
import External.Constants;

import java.io.PrintStream;
import java.util.Scanner;

public class DiscountsHistoryScreen extends Screen {

    private final DiscountsHistory discountsHistory;

    public DiscountsHistoryScreen(PrintStream out, Scanner in, DiscountsHistory discountsHistory) {
        super(out, in);
        this.discountsHistory = discountsHistory;
    }

    @Override
    public int handleMsg() {
        String LEFT_ALIGN_FORMAT = "| %-12s | %-28s | %-28s |%n";

        this.out.println();
        this.out.format("+--------------+------------------------------+------------------------------+%n");
        this.out.format("| Percentage   | Start Date                   | End Date                     |%n");
        this.out.format("+--------------+------------------------------+------------------------------+%n");
        for (int discountIndex = 0; discountIndex < discountsHistory.discountList.size(); discountIndex++) {
            Discount discount = discountsHistory.discountList.get(discountIndex);
            this.out.format(LEFT_ALIGN_FORMAT,
                    discount.getPercentage() + "%", discount.getStartDate(), discount.getFinalDate());
        }

        this.out.format("+--------------+------------------------------+------------------------------+%n");
        this.out.println();

        return Constants.USER_NO_INPUT;
    }
}
