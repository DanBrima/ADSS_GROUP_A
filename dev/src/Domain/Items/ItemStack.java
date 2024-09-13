package Domain.Items;

import java.util.ArrayList;

public class ItemStack {

    private Item itemType;

    private ArrayList<Item> itemsList;

    private StackLocation stackLocation;

    // Constructor for a new stack from one item
    public ItemStack(Item item) {
        this.itemType = item;
        this.itemsList = new ArrayList<Item>();
        this.addItem(item);
    }

    public ItemStack(Item item, int amount) {
        this.itemType = item;
        this.itemsList = new ArrayList<Item>();
        for (int amountIndex = 0; amountIndex < amount; amountIndex++) {
            this.addItem(item);
        }
    }

    // Add item if of the right type
    public void addItem(Item item) {
        this.itemsList.add(item);
    }

    public Item getItemType() {
        return itemType;
    }

    public ArrayList<Item> getItemsList() {
        return itemsList;
    }

    public String getLocationDetails() {
        return stackLocation.toString();
    }

    public void setLocation(StackLocation stackLocation) {
        this.stackLocation = stackLocation;
    }
}
