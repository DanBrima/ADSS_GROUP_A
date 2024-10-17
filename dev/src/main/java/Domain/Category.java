package Domain;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private String name;
    private Category parent;
    private List<Discount> discounts;

    public Category(String name) {
        this.name = name;
        this.parent = null;
        this.discounts = new ArrayList<>();
    }

    public Category(String name, Category parent) {
        this.name = name;
        this.parent = parent;
        this.discounts = new ArrayList<>();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public List<Discount> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<Discount> discounts) {
        this.discounts = discounts;
    }
}
