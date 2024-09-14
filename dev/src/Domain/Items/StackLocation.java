package Domain.Items;

public class StackLocation {
    private String place; // "Storage" or "Store"
    private int shelfIndex; // Shelf index (if the product is on a shelf)
    private int itemStackIndex;

    public StackLocation(int shelfIndex, int itemStackIndex) {
        this.place = "Store";
        this.shelfIndex = shelfIndex;
        this.itemStackIndex = itemStackIndex;
    }

    public StackLocation(int itemStackIndex) {
        this.place = "Storage";
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

    @Override
    public String toString() {
        return "Location{" +
                "place='" + place + '\'' +
                ", shelf=" + (shelfIndex + 1) +
                ", itemStack=" + (itemStackIndex + 1) +
                '}';
    }
}