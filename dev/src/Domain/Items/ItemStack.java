package Domain.Items;

import java.util.ArrayList;

public class ItemStack {

    private Item itemType;

    private ArrayList<ItemInstance> itemsList;

    // Constructor for empty stack of certain type
    public ItemStack(Item itemType) {
        this.itemType = itemType;
        this.itemsList = new ArrayList<ItemInstance>();
    }

    // Constructor for a new stack from one item
    public ItemStack(ItemInstance item) {
        this.itemType = item.getItemType();
        this.itemsList = new ArrayList<ItemInstance>();
        this.addItem(item);
    }

    public ItemStack(ItemInstance item, int amount) {
        this.itemType = item.getItemType();
        this.itemsList = new ArrayList<ItemInstance>();
        for (int amountIndex = 0; amountIndex < amount; amountIndex++) {
            this.addItem(item);
        }
    }

    // Add item if of the right type
    public void addItem(ItemInstance item) {
        if (item.getItemType().getBARCODE() != this.itemType.getBARCODE()) {
            throw new RuntimeException("The item " + item.getItemType() + " with barcode: " + item.getItemType().getBARCODE()
                    + " doesn't fit the barcode of type " + this.itemType.getName() + " which is: " + this.itemType.getBARCODE());
        } else {
            this.itemsList.add(item);
        }
    }

    public int getItemSize() {
        return this.itemsList.size();
    }

    public Item getItemType() {
        return itemType;
    }

    public ArrayList<ItemInstance> getItemsList() {
        return itemsList;
    }
}
