package Domain.Items;

import java.util.ArrayList;
import java.util.UUID;

public class ItemStack {

    private Item itemType;
    private int itemCount;

    private StackLocation stackLocation;

    // Constructor for a new stack from one item
    public ItemStack(Item item) {
        this.itemType = item;
        this.addItem(item);
    }

    public ItemStack(Item item, int amount) {
        this.itemType = item;
        this.itemCount = amount;
    }

    // Add item if of the right type
    public void addItem(Item item) {
        if (item.getBARCODE() == this.itemType.getBARCODE()) {
            this.itemCount++;
        }
    }

    public void addItem(UUID barcode) {
        if (barcode == this.itemType.getBARCODE()) {
            this.itemCount++;
        }
    }

    public void addItems(UUID barcode, int amount) {
        if (barcode == this.itemType.getBARCODE()) {
            this.itemCount++;
        }
    }

    public Item getItemType() {
        return this.itemType;
    }

    public int getItemCount() {
        return this.itemCount;
    }

    public String getLocationDetails() {
        return stackLocation.toString();
    }

    public void setLocation(StackLocation stackLocation) {
        this.stackLocation = stackLocation;
    }

}
