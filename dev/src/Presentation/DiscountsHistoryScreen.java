package Presentation;

import Domain.Categories.Category;
import Domain.Discounts.CategoryDiscount;
import Domain.Discounts.Discount;
import Domain.Discounts.DiscountsHistory;
import Domain.Discounts.ItemDiscount;
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
        String LEFT_ALIGN_FORMAT = "| %-12s | %-9s | %-12s | %-28s | %-28s |%n";

        this.out.println();
        this.out.format("+--------------+-----------+--------------+------------------------------+------------------------------+%n");
        this.out.format("| Percentage   | Type      | includes     | Start Date                   | End Date                     |%n");
        this.out.format("+--------------+-----------+--------------+------------------------------+------------------------------+%n");
        for (int discountIndex = 0; discountIndex < discountsHistory.discountList.size(); discountIndex++) {
            Discount discount = discountsHistory.discountList.get(discountIndex);

            String discountType = "General";
            String discountIncludes = "all";
            if (discount instanceof CategoryDiscount) {
                discountType = "Category";
                discountIncludes = ((CategoryDiscount)discount).getCategory().getName();
            }
            else if (discount instanceof ItemDiscount) {
                discountType = "Items";
                discountIncludes = ((ItemDiscount)discount).getItem().getName();
            }

            this.out.format(LEFT_ALIGN_FORMAT,
                    discount.getPercentage() + "%", discountType, discountIncludes, discount.getStartDate(), discount.getFinalDate());
        }

        this.out.format("+--------------+-----------+--------------+------------------------------+------------------------------+%n");
        this.out.println();

        return Constants.USER_NO_INPUT;
    }
}
