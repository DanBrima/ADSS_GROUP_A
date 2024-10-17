package Domain;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table
public class ItemStack {
    @Column
    private int itemCount;
    @OneToOne
    private StackLocation stackLocation;
    @ManyToOne
    private ProductInStore itemType;
    @Id
    private Long id;

    public ItemStack() {
    }

    public ItemStack(int itemCount, StackLocation stackLocation, ProductInStore itemType) {
        this.itemCount = itemCount;
        this.stackLocation = stackLocation;
        this.itemType = itemType;
    }

    public ItemStack(ProductInStore ProductInStore, int amount) {
        this.itemType = ProductInStore;
        this.itemCount = amount;
    }

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
        if (barcode == this.itemType.getBarcode()) {
            this.itemCount+=amount;
        }
    }

    public ItemStack deepCopy() {
        ItemStack newItem = new ItemStack();
        newItem.setItemCount(this.itemCount);
        newItem.setLocation(new StackLocation(this.stackLocation.getPlace(), this.stackLocation.getType(),
                this.stackLocation.getShelfIndex(), this.stackLocation.getItemStackIndex()));
        newItem.setItemType(new ProductInStore(this.itemType.getProduct(), this.itemType.getRequiredAmount(),
                this.itemType.getCategory(), this.itemType.getPrice()));

        return newItem;
    }
}
