package Domain;

import java.util.UUID;

public class ItemStack {
    private int itemCount;
    private StackLocation stackLocation;
    private ProductInStore itemType;

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

}
