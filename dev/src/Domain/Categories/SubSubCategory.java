package Domain.Categories;

public class SubSubCategory {
    private final String name;
    private final SubCategory upperCategory;

    public SubSubCategory(String name, SubCategory upperCategory) {
        this.name = name;
        this.upperCategory = upperCategory;
    }

    public String getName() {
        return name;
    }

    public SubCategory getUpperCategory() {
        return upperCategory;
    }
}
