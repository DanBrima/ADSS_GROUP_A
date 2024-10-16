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

    public static Contract getContractFromIO(IO io, Supplier contractSupplier) {
        int wholesaleThreshold = io.readInt("Enter the wholesale threshold (minimal number of products of a wholesale):");
        int productCount = io.readInt("Enter the number of products in the contract (must be 1 or more):");
        assert productCount > 0;
        List<ProductInContract> products = Stream.generate(() -> ProductInContract.getContractFromIO(io)).limit(productCount).toList();

        return new Contract(wholesaleThreshold, contractSupplier, products);
    }

    public ProductInContract getProduct(String productName) {
        return products.stream()
                .filter(product -> product.name().equals(productName))
                .findFirst()
                .orElse(null);  // Returns null if no product is found
    }

    public boolean isDiscount(int amount){
        return wholesaleThreshold < amount;
    }

    public List<ProductInContract> products() {
        return Collections.unmodifiableList(this.products);
    }
}
