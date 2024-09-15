package Domain.Items;

import java.util.UUID;

public class StackLocation {
    private String place; // "Storage" or "Store"
    private UUID shelfId; // Shelf index (if the product is on a shelf)
    private String type; // "inventory" or "defect" (if the product is in the storage)
    private int itemStackIndex;

    public StackLocation(UUID SHELF_ID, int itemStackIndex) {
        this.place = "Store";
        this.shelfId = SHELF_ID;
        this.itemStackIndex = itemStackIndex;
    }

    public StackLocation(String type, int itemStackIndex) {
        this.place = "Storage";
        this.type = type;
        this.itemStackIndex = itemStackIndex;
    }

    public UUID getShelfIndex() {
        return shelfId;
    }

    public int getItemStackIndex() {
        return itemStackIndex;
    }

    public void setShelfIndex(UUID shelfIndex) {
        this.shelfId = shelfIndex;
    }

    public void setItemStackIndex(int itemStackIndex) {
        this.itemStackIndex = itemStackIndex;
    }
}