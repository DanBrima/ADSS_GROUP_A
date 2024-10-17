package Domain;

import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Category {
    @Id
    @Column
    private String name;
    @ManyToOne
    private Category parent;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Discount> discounts;

    public Category() {
    }

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

    public String fullCategoryName() {
        String fullCategory = this.name;
        Category parentCategory = this.parent;

        if (parentCategory != null) {
            fullCategory += " - " + parentCategory.getName();

            Category grandParentCategory = parentCategory.getParent();
            if (grandParentCategory != null) {
                fullCategory += " - " + grandParentCategory.getName();
            }
        }

        return fullCategory;
    }
}
