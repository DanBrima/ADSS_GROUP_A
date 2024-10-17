package Domain;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table
public class ItemStack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private int itemCount;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "stack_location_id", referencedColumnName = "id")
    private StackLocation stackLocation;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_in_store_id", referencedColumnName = "id")
    private ProductInStore itemType;

    // Default constructor for JPA
    public ItemStack() {}

    // Constructor with full parameters
    public ItemStack(int itemCount, StackLocation stackLocation, ProductInStore itemType) {
        this.itemCount = itemCount;
        this.stackLocation = stackLocation;
        this.itemType = itemType;
    }

    // Constructor with item type and amount
    public ItemStack(ProductInStore ProductInStore, int amount) {
        this.itemType = ProductInStore;
        this.itemCount = amount;
    }

    // Getters and setters
    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public StackLocation getStackLocation() {
        return stackLocation;
    }

    public void setLocation(StackLocation stackLocation) {
        this.stackLocation = stackLocation;
    }

    public ProductInStore getItemType() {
        return itemType;
    }

    public void setItemType(ProductInStore itemType) {
        this.itemType = itemType;
    }

    public void addItems(UUID barcode, int amount) {
        if (barcode.equals(this.itemType.getBarcode())) {
            this.itemCount += amount;
        }
    }
}
