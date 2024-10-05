package Domain;

import External.Constants;
import Presentation.IO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Store {
    private String name;
    private List<Order> orders;

    // Constructor for empty inventory
    public Store(String name) {
        this.name = name;
        this.orders = new ArrayList<>();
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

    public void addOrder(IO io, List<Supplier> suppliers){

        this.orders.add(Order.getOrderFromIO(io, suppliers));
    }

    public String getName(){
        return name;
    }

    protected static Store getStoreFromIO(IO io) {
        String name = io.readString("Enter the store's name:");
        return new Store(name);
    }

    public List<Order> getOrders(){ return orders;}
}
