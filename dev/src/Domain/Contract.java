package Domain;

import Presentation.IO;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Contract {
    private int wholesaleThreshold;
    private Supplier supplier;
    private List<ProductInContract> products;

    public Contract(int wholesaleThreshold, Supplier supplier, List<ProductInContract> products) {
        assert wholesaleThreshold > 0;
        assert !products.isEmpty();
        this.wholesaleThreshold = wholesaleThreshold;
        this.supplier = supplier;
        this.products = products;
    }

    // It's not suppose to be "Set" contract?
    public static Contract getContractFromIO(IO io, Supplier contractSupplier) {
        int wholesaleThreshold = io.readInt("Enter the wholesale threshold (minimal number of products of a wholesale):");
        int productCount = io.readInt("Enter the number of products in the contract (must be 1 or more:");
        assert productCount > 0;
        List<ProductInContract> products = Stream.generate(() -> ProductInContract.getContractFromIO(io, wholesaleThreshold)).limit(productCount).toList();

        return new Contract(wholesaleThreshold, contractSupplier, products);
    }

    public boolean isDiscount() {
        return wholesaleThreshold < this.products.stream().mapToDouble(ProductInContract::count).sum();
    }

    public List<ProductInContract> products() {
        return Collections.unmodifiableList(this.products);
    }
}
