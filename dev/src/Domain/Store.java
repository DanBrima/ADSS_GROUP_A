package Domain;

import Presentation.IO;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private String name;
    private List<Order> orders;
    private ArrayList<Shelf> shelves;
    private Storage storage;

    // Constructor for empty inventory
    public Store(String name) {
        this.name = name;
        this.orders = new ArrayList<>();
        this.shelves = new ArrayList<>();
        this.storage = new Storage();
    }

    // Constructor from given order
    public Store(String name, Order order) {
        this.name = name;
        this.orders = new ArrayList<>();
        this.addOrder(order);
        this.storage = new Storage();
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }

    public void addOrder(IO io, List<Supplier> suppliers){
        this.orders.add(Order.getOrderFromIO(io, suppliers));
    }

    public void addItem(ProductInStore productInStore) {
        if (this.shelves.size() == 0) {
            this.shelves.add(new Shelf(new ItemStack(productInStore, 1)));
        } else {
           //this.shelves.getFirst().addItemStack(new ItemStack(productInStore, 1));
        }
    }

    public String getName(){
        return name;
    }

    protected static Store getStoreFromIO(IO io) {
        String name = io.readString("Enter the store's name:");
        return new Store(name);
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public List<Order> getOrders(){ return orders;}

    public void addShelf(Shelf shelf) {
        this.shelves.add(shelf);
    }

    public ArrayList<Shelf> getShelves() {
        return (ArrayList<Shelf>) shelves.clone();
    }

    public int getSpecificItemAmount(ProductInStore wantedItem) {
        int amount = 0;
        for (Shelf shelf : this.getShelves()) {
            for (ItemStack itemStack : shelf.getItemsOnShelf()) {
                if (itemStack.getItemType().getBarcode() == wantedItem.getBarcode())
                    amount += itemStack.getItemCount();
            }
        }

        return amount;
    }
}
