package Domain;

import java.util.ArrayList;

public class Shelf {
    private ArrayList<ItemStack> itemsOnShelf;

    // Constructor for empty inventory
    public Shelf() {
        this.itemsOnShelf = new ArrayList<ItemStack>();
    }

    // Constructor from item stack
    public Shelf(ItemStack itemStack) {
        this.itemsOnShelf = new ArrayList<ItemStack>();
        this.addItemStack(itemStack);
    }

    // Constructor from another shelf
    public Shelf(Shelf shelf) {
        this.itemsOnShelf = new ArrayList<ItemStack>(shelf.getItemsOnShelf());
    }

    public void addItemStack(ItemStack itemStack) {
        this.itemsOnShelf.add(itemStack);
    }

    public ArrayList<ItemStack> getItemsOnShelf() {
        return (ArrayList<ItemStack>) itemsOnShelf.clone();
    }
}
