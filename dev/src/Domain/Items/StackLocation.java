package Domain.Items;

public class StackLocation {
    private String place; // "Storage" or "Store"
    private int shelfIndex; // Shelf index (if the product is on a shelf)
    private String type; // "inventory" or "defect" (if the product is in the storage)
    private int itemStackIndex;

    public StackLocation(int shelfIndex, int itemStackIndex) {
        this.place = "Store";
        this.shelfIndex = shelfIndex;
        this.itemStackIndex = itemStackIndex;
    }

    public StackLocation(String type, int itemStackIndex) {
        this.place = "Storage";
        this.type = type;
        this.itemStackIndex = itemStackIndex;
    }

    public int getShelfIndex() {
        return shelfIndex;
    }

    public int getItemStackIndex() {
        return itemStackIndex;
    }

    public void setShelfIndex(int shelfIndex) {
        this.shelfIndex = shelfIndex;
    }

    public void setItemStackIndex(int itemStackIndex) {
        this.itemStackIndex = itemStackIndex;
    }
}