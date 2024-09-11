package Domain.Discounts;

import Domain.Categories.Category;

import java.util.Date;

public class CategoryDiscount extends Discount{

    private final Category category;

    public CategoryDiscount(int percentage, Date startDate, Date finalDate, Category category) throws Exception {
        super(percentage, startDate, finalDate);
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }
}
