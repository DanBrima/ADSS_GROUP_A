package Domain;

import Presentation.IO;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Contract {
    private int wholesaleThreshold;
    private boolean discount;
    private Supplier supplier;
    private List<ProductInContract> products;

    // wholesaleThreshold - maybe needs to be a static variable with constant value and not from input ?
    public Contract(int wholesaleThreshold, Supplier supplier, List<ProductInContract> products) {
        assert wholesaleThreshold > 0;
        assert !products.isEmpty();
        this.wholesaleThreshold = wholesaleThreshold;
        this.supplier = supplier;
        this.products = products;

        int amount = 0;
        for (ProductInContract product : products) {amount += product.count();}
        this.discount = amount >= wholesaleThreshold;
    }

    // It's not suppose to be "Set" contract?
    public static Contract getContractFromIO(IO io, Supplier contractSupplier) {
        int wholesaleThreshold = io.readInt("Enter the wholesale threshold (minimal number of products of a wholesale):");
        int productCount = io.readInt("Enter the number of products in the contract (must be 1 or more:");
        assert productCount > 0;
        List<ProductInContract> products = Stream.generate(() -> ProductInContract.getContractFromIO(io,wholesaleThreshold)).limit(productCount).toList();

        return new Contract(wholesaleThreshold, contractSupplier, products);
    }

    public boolean isDiscount(){
        return discount;
    }
    public List<ProductInContract> products() {
        return Collections.unmodifiableList(this.products);
    }
}
