package domain;

public class ProductInContract {
    private Product product;
    private int price;
    private int productDiscountPercentage;
    private String supplierCatalogID;

    public ProductInContract(Product product, int price, int productDiscountPercentage, String supplierCatalogID) {
        this.product = product;
        this.price = price;
        this.productDiscountPercentage = productDiscountPercentage;
        this.supplierCatalogID = supplierCatalogID;
    }
}
