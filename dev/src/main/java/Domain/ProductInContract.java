package Domain;

import Presentation.IO;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "product_in_contract")
public class ProductInContract {
    @Id
    @Column
    private UUID supplierCatalogID;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @Column
    private double price;
    @Column
    private int productDiscountPercentage;

    public ProductInContract() {
    }

    public ProductInContract(Product product, int price, int productDiscountPercentage) {
        this.product = product;
        this.price = price;
        this.productDiscountPercentage = productDiscountPercentage;
        this.supplierCatalogID = UUID.randomUUID();
    }

    public static ProductInContract getContractFromIO(IO io) {
        Product product = Product.getProductFromIO(io);
        int price = io.readInt("Enter the price of the product:");
        int productDiscountPercentage = io.readInt("Enter the discount percentage of the product:");
        return new ProductInContract(product, price, productDiscountPercentage);
    }

    public String name() {
        return product.name;
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
                ", productDiscountPercentage=" + productDiscountPercentage +
                ", supplierCatalogID=" + supplierCatalogID +
                '}';
    }
}
