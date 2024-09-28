package Domain;

import External.Constants;
import Presentation.IO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Store {
    private String name;
    private List<Order> orders;

    // TOBE DELETED: Store don't collect suppliers any more - only orders
    private List<Supplier> suppliers;

    // TO BE DELETED: Store with empty name
    public Store() {
        this.orders = new ArrayList<>();
        // TBD
        this.suppliers = new ArrayList<>();
    }

    // Constructor for empty inventory
    public Store(String name) {
        this.name = name;
        this.orders = new ArrayList<>();
        // TBD
        this.suppliers = new ArrayList<>();
    }

    // Constructor from given order
    public Store(String name, Order order) {
        this.name = name;
        this.orders = new ArrayList<>();
        this.addOrder(order);
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }

    // TBD
    public void addSupplier(Supplier supplier) {
        this.suppliers.add(supplier);
    }

    public void addOrder(IO io, Supplier supplier){
        this.orders.add(Order.getOrderFromIO(io, supplier));
    }

    // TOBE DELETED
    public void addSupplier(IO io) {
        int delivery = io.readInt("Choose\n 1. Fixed days delivery\n 2. In place delivery:\n");
        switch (delivery) {
            case 1:
                this.suppliers.add(FixedDaysSupplier.getFixedDaysSupplierFromIO(io));
                break;
            case 2:
                this.suppliers.add(InPlaceSupplier.getInPlaceSupplierFromIO(io));
                break;
            default:
                io.print(Constants.INVALID_INPUT);
                break;
        }
    }

    public String getName(){
        return name;
    }

    protected static Store getStoreFromIO(IO io) {
        String name = io.readString("Enter the store's name:");
        return new Store(name);
    }

    public List<Supplier> getSuppliers() {
        return suppliers;
    }

    public List<Order> getOrders(){ return orders;}
}
