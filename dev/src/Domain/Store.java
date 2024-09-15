package Domain;

import Domain.Items.Item;
import Domain.Items.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

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
        int shelfIndex = this.shelves.size();
        shelf.setShelfId(shelfIndex);
        this.shelves.add(shelf);
    }

    public ArrayList<Shelf> getShelves() {
        return (ArrayList<Shelf>) shelves.clone();
    }

    public int getSpecificItemAmount(Item wantedItem) {
        int amount = 0;
        for (Shelf shelf : this.getShelves()) {
            for (ItemStack itemStack : shelf.getItemsOnShelf()) {
                if (itemStack.getItemType().getBARCODE() == wantedItem.getBARCODE())
                    amount += itemStack.getItemCount();
            }
        }

        return amount;
    }
}
