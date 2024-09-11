package Domain.Categories;

import Domain.Categories.Category;

public class SubCategory {
    private final Category upperCategory;
    private final String name;

    public SubCategory(Category upperCategory, String name) {
        this.upperCategory = upperCategory;
        this.name = name;
    }

    public Category getUpperCategory() {
        return upperCategory;
    }

    public String getName() {
        return name;
    }
}
