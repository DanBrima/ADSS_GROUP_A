package Domain;

import External.Constants;
import Presentation.IO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Store {
    private List<Supplier> suppliers;

    // Constructor for empty inventory
    public Store() {
        this.suppliers = new ArrayList<Supplier>();
    }

    // Constructor from given Supplier
    public Store(Supplier supplier) {
        this.suppliers = new ArrayList<Supplier>();
        this.addSupplier(supplier);
    }

    public void addSupplier(Supplier supplier) {
        int supplierIndex = this.suppliers.size();

	// Do we want to maintain a suppliers IDs?
        //shelf.setSupplierId(supplierIndex);
        this.suppliers.add(supplier);
    }

    public void addSupplier(IO io) {
        int delivery = io.readInt("Choose\n 1. Fixed days delivery\n 2. In place delivery:\n");
        switch (delivery){
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

    public List<Supplier> getSuppliers() {
        return suppliers;
    }
}
