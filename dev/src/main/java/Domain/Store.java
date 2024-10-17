package Domain;

import Presentation.IO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "store")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column
    private String name;

    // One store can have many orders (OneToMany relationship)
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Shelf> shelves = new ArrayList<>();

    // One-to-One relationship with Storage
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "store")

    private Storage storage;

    public Store() {
    }

    // Constructor
    public Store(String name) {
        this.name = name;
        this.storage = new Storage();
    }

    public Store(String name, Order order) {
        this.name = name;
        this.addOrder(order);
        this.storage = new Storage();
    }

    // Methods
    public void addOrder(Order order) {
        this.orders.add(order);
    }

    public void addOrder(IO io, List<Supplier> suppliers) {
        this.orders.add(Order.getOrderFromIO(io, suppliers));
    }

    public void addItem(ProductInStore productInStore) {
        if (this.shelves.isEmpty()) {
            this.shelves.add(new Shelf(new ItemStack(productInStore, 1)));
        } else {
            this.shelves.get(0).addItemStack(new ItemStack(productInStore, 1));
        }
    }

    public String getName() {
        return name;
    }

    public static Store getStoreFromIO(IO io) {
        String name = io.readString("Enter the store's name:");
        return new Store(name);
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addShelf(Shelf shelf) {
        this.shelves.add(shelf);
    }

    public ArrayList<Shelf> getShelves() {
        return new ArrayList<>(shelves);
    }

    public int getSpecificItemAmount(ProductInStore wantedItem) {
        int amount = 0;
        for (Shelf shelf : this.getShelves()) {
            for (ItemStack itemStack : shelf.getItemsOnShelf()) {
                if (itemStack.getItemType().getBarcode() == wantedItem.getBarcode()) {
                    amount += itemStack.getItemCount();
                }
            }
        }
        return amount;
    }
}
