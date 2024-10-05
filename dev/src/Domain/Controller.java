package Domain;

import External.Constants;
import Presentation.IO;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    private List<Supplier> suppliers;
    private List<Store> stores;

    // Constructor for empty inventory
    public Controller() {
        this.suppliers = new ArrayList<>();
        this.stores = new ArrayList<>();
    }

    // Constructor from given Store
    public Controller(Store store) {
        this.stores = new ArrayList<>();
        this.addStore(store);
        this.suppliers = new ArrayList<>();
    }

    public void addSupplier(Supplier supplier) {
        this.suppliers.add(supplier);
    }

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

    public void addStore(Store store) {
        this.stores.add(store);
    }

    public void addStore(IO io) {
        this.stores.add(Store.getStoreFromIO(io));
    }

    public List<Supplier> getSuppliers() {
        return suppliers;
    }
    public List<Store> getStores() {
        return stores;
    }

    // Gets product name and find the supplier with the cheapest contract for the product
    // ?? Does it need to calculate the discount and include it in "cheapest" calculation ??
    // Return null if not found product name in any supplier
    public static Supplier getCheapestSupplier(String product, List<Supplier> suppliers){
        Supplier minSup = null;
        double minPrice = Integer.MAX_VALUE;
        for (Supplier supplier: suppliers){
            for (ProductInContract conProduct: supplier.getAllProductsInContracts()){
                if (product.equals(conProduct.name()) && conProduct.price() < minPrice){
                    minPrice = conProduct.price();
                    minSup = supplier;
                }
            }
        }
        // Need to throw exception if not initialized?
        return minSup;
    }
}
