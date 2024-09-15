package Domain;

import Domain.Items.ItemStack;
import Domain.Items.StackLocation;

import java.util.ArrayList;
import java.util.UUID;

public class Shelf {
    private UUID shelfId;

    private ArrayList<ItemStack> itemsOnShelf;

    // Constructor for empty inventory
    public Shelf() {
        this.itemsOnShelf = new ArrayList<ItemStack>();
    }


    // Constructor from item stack
    public Shelf(ItemStack itemStack) {
        this.shelfId = UUID.randomUUID();
        this.itemsOnShelf = new ArrayList<ItemStack>();
        this.addItemStack(itemStack);
    }

    // Constructor from another shelf
    public Shelf(Shelf shelf) {
        this.shelfId = UUID.randomUUID();
        this.itemsOnShelf = new ArrayList<ItemStack>(shelf.getItemsOnShelf());
    }


    public void addItemStack(ItemStack itemStack) {
        this.itemsOnShelf.add(itemStack);
        itemStack.setLocation(new StackLocation(this.shelfId, itemsOnShelf.size()-1));
    }

    public UUID getShelfId() {
        return shelfId;
    }

    public ArrayList<ItemStack> getItemsOnShelf() {
        return (ArrayList<ItemStack>) itemsOnShelf.clone();
    }
}
