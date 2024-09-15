package Domain.Items;

import java.util.ArrayList;
import java.util.Objects;
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
            this.itemCount+=amount;
        }
    }

    public Item getItemType() {
        return this.itemType;
    }

    public int getItemCount() {
        return this.itemCount;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemStack itemStack = (ItemStack) o;
        return itemCount == itemStack.itemCount && itemStack.getItemType().getBARCODE() == this.itemType.getBARCODE();
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemType, itemCount, stackLocation);
    }
}
