package Presentation.Inventory;


import Domain.Category;
import Domain.Discount;
import Domain.DiscountsHistory;
import Domain.ProductInStore;
import External.InventoryConstants;
import Presentation.Screen;
import Repositories.CategoryRepository;
import Repositories.ProductInStoreRepository;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class DiscountsHistoryScreen extends Screen {

    public DiscountsHistoryScreen(PrintStream out, Scanner in) {
        super(out, in);
    }


    @Override
    public int handleMsg() {
        String LEFT_ALIGN_FORMAT = "| %-12s | %-9s | %-12s | %-28s | %-28s |%n";

        this.out.println();
        this.out.format("+--------------+-----------+--------------+------------------------------+------------------------------+%n");
        this.out.format("| Percentage   | Type      | Name     | Start Date                   | End Date                     |%n");
        this.out.format("+--------------+-----------+--------------+------------------------------+------------------------------+%n");

        String discountType = "General";
        String discountOfName = "all";

        CategoryRepository categoryRepository = new CategoryRepository();
        List<Category> categories = categoryRepository.getAll();

        for (Category category : categories) {
            discountType = "Category";
            discountOfName = category.getName();

            for (Discount discount : category.getDiscounts()) {
                this.out.format(LEFT_ALIGN_FORMAT,
                        discount.getPercentage() + "%", discountType, discountOfName, discount.getStartDate(), discount.getEndDate());
            }
        }

        ProductInStoreRepository productInStoreRepository = new ProductInStoreRepository();
        List<ProductInStore> products = productInStoreRepository.getAll();

        for (ProductInStore product : products) {
            discountType = "Products";
            discountOfName = product.getName();

            for (Discount discount : product.getDiscounts()) {
                this.out.format(LEFT_ALIGN_FORMAT,
                        discount.getPercentage() + "%", discountType, discountOfName, discount.getStartDate(), discount.getEndDate());
            }
        }

        this.out.format("+--------------+-----------+--------------+------------------------------+------------------------------+%n");
        this.out.println();

        return InventoryConstants.USER_NO_INPUT;
    }
}
