package Domain;

import Domain.Items.ItemStack;

import java.util.ArrayList;

public class Store {
    private ArrayList<Shelf> shelves;

    // Constructor for empty inventory
    public Store() {
        this.shelves = new ArrayList<Shelf>();
    }

    // Constructor from Shelf
    public Store(Shelf shelf) {
        this.shelves = new ArrayList<Shelf>();
        this.addShelf(shelf);
    }

    public void addShelf(Shelf shelf) {
        this.shelves.add(new Shelf(shelf));
    }

    public ArrayList<ItemStack> getShelves() {
        return (ArrayList<ItemStack>) shelves.clone();
    }
}
