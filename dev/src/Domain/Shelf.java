package Domain;

import Domain.Items.ItemStack;
import Domain.Items.StackLocation;

import java.util.ArrayList;

public class Shelf {
    private int shelfId;

    private ArrayList<ItemStack> itemsOnShelf;

    // Constructor for empty inventory
    public Shelf() {
        this.itemsOnShelf = new ArrayList<ItemStack>();
    }

    // Constructor for empty inventory
    public Shelf(int shelfId) {
        this.shelfId = shelfId;
        this.itemsOnShelf = new ArrayList<ItemStack>();
    }

    // Constructor from item stack
    public Shelf(int shelfId, ItemStack itemStack) {
        this.shelfId = shelfId;
        this.itemsOnShelf = new ArrayList<ItemStack>();
        this.addItemStack(itemStack);
    }

    // Constructor from another shelf
    public Shelf(int shelfId, Shelf shelf) {
        this.shelfId = shelfId;
        this.itemsOnShelf = new ArrayList<ItemStack>(shelf.getItemsOnShelf());
    }

    public void setShelfId(int shelfId) {
        this.shelfId = shelfId;
    }

    public void addItemStack(ItemStack itemStack) {
        this.itemsOnShelf.add(itemStack);
        itemStack.setLocation(new StackLocation(this.shelfId, itemsOnShelf.size()-1));
    }

    public ArrayList<ItemStack> getItemsOnShelf() {
        return (ArrayList<ItemStack>) itemsOnShelf.clone();
    }
}
