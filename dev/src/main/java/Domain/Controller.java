package Domain;

import External.SuppliersConstants;
import Presentation.IO;
import Repositories.StoreRepository;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    private static Controller controller_instance = null;
    private List<Supplier> suppliers;
    private List<Store> stores;

    // Constructor for empty inventory
    public Controller() {
        this.suppliers = new ArrayList<>();
        this.stores = new ArrayList<>();
    }

    public static void setControllerInstance(Controller controller) {
        controller_instance = controller;
    }

    public static Controller controllerInstance() {
        // To ensure only one instance is created
        if (controller_instance == null) {
            controller_instance = new Controller();
        }
        return controller_instance;
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
                io.print(SuppliersConstants.INVALID_INPUT);
                break;
        }
    }

    public void addStore(Store store) {
        this.stores.add(store);
    }

    public void addStore(IO io) {
        Store store = Store.getStoreFromIO(io);
        this.stores.add(store);
        StoreRepository.add(store);
    }

    public List<Supplier> getSuppliers() {
        return suppliers;
    }
    public List<Store> getStores() {
        return stores;
    }
}
