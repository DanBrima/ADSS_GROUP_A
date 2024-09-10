package domain;

import presentation.IO;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Contract {
    private List<ProductInContract> products;
    private int wholesaleThreshold;

    public Contract(int wholesaleThreshold, List<ProductInContract> products) {
        assert wholesaleThreshold > 0;
        assert !products.isEmpty();

        this.wholesaleThreshold = wholesaleThreshold;
        this.products = products;
    }

    public static Contract getContractFromIO(IO io) {
        int wholesaleThreshold = io.readInt("Enter the wholesale threshold (minimal number of products of a wholesale):");
        int productCount = io.readInt("Enter the number of products in the contract (must be 1 or more:");
        assert productCount > 0;
        List<ProductInContract> products = Stream.generate(() -> ProductInContract.getContractFromIO(io)).limit(productCount).toList();

        return new Contract(wholesaleThreshold, products);
    }

    public List<ProductInContract> products() {
        return Collections.unmodifiableList(this.products);
    }
}
