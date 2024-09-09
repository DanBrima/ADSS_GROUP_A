package domain;

import java.util.UUID;

public class ProductInContract {
    private Product product;
    private int price;
    private int productDiscountPercentage;
    private UUID supplierCatalogID;

    public ProductInContract(Product product, int price, int productDiscountPercentage) {
        this.product = product;
        this.price = price;
        this.productDiscountPercentage = productDiscountPercentage;
        this.supplierCatalogID = UUID.randomUUID();
    }
}
