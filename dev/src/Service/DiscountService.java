package Service;

import Domain.Categories.Category;
import Domain.Discounts.CategoryDiscount;
import Domain.Discounts.DiscountsHistory;
import Domain.Discounts.ItemDiscount;
import Domain.Items.Item;

import java.math.BigDecimal;
import java.util.Date;

public class DiscountService {

    public static void addItemDiscount(DiscountsHistory discountsHistoryRef, String itemType, int percentage) {
        try {
            discountsHistoryRef.discountList.add(new ItemDiscount(percentage, new Date(), new Date(),
                    new Item(itemType, new BigDecimal(-1), "", new BigDecimal(-1), -1, null)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void addCategoryDiscount(DiscountsHistory discountsHistoryRef, String category, int percentage) {
        try {
            discountsHistoryRef.discountList.add(new CategoryDiscount(percentage, new Date(), new Date(), new Category(category)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
