package Domain;

import Presentation.IO;

import java.util.UUID;

public class ProductInContract {
    private Product product;
    private double price;
    public int amount; //TODO: delete - it is out of the design and also not used
    private int productDiscountPercentage;
    private UUID supplierCatalogID;

    public ProductInContract(Product product, int price, int amount, int productDiscountPercentage) {
        this.product = product;
        this.price = price;
        this.amount = amount;
        this.productDiscountPercentage = productDiscountPercentage;
        this.supplierCatalogID = UUID.randomUUID();
    }

    public static ProductInContract getContractFromIO(IO io) {
        Product product = Product.getProductFromIO(io);
        int price = io.readInt("Enter the price of the product:");
        int amount = io.readInt("Enter amount of product:");
        int productDiscountPercentage = io.readInt("Enter the discount percentage of the product:");
        return new ProductInContract(product, price, amount, productDiscountPercentage);
    }

    public String name() {
        return product.name;
    }

    public int amount() {
        return amount;
    }

    public UUID supplierCatalogID() {
        return this.supplierCatalogID;
    }

    public Product product() {
        return this.product;
    }

    public double price() {
        return this.price;
    }

    public double priceWithDiscount() {
        return this.price * (1 - this.productDiscountPercentage / 100.0);
    }

    @Override
    public String toString() {
        return "{product=" + product +
                ", price=" + price +
                ", count=" + amount +
                ", productDiscountPercentage=" + productDiscountPercentage +
                ", supplierCatalogID=" + supplierCatalogID +
                '}';
    }
}
