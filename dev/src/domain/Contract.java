package domain;

import java.util.Collections;
import java.util.List;

public class Contract {
    private Supplier supplier;
    private int wholesaleThreshold;
    private List<ProductInContract> products;

    public Contract(int wholesaleThreshold, Supplier supplier, List<ProductInContract> products) {
        assert wholesaleThreshold > 0;
        assert !products.isEmpty();

        this.wholesaleThreshold = wholesaleThreshold;
        this.supplier = supplier;
        this.products = products;
    }

    public List<ProductInContract> products() {
        return Collections.unmodifiableList(this.products);
    }
}
