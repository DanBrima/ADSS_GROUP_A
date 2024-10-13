package Domain;

import Presentation.IO;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ProductInOrder {
    private Product product;
    public int amount;

    public ProductInOrder(Product product, int amount) {
        this.product = product;
        this.amount = amount;
    }

    public static ProductInOrder getOrderFromIO(IO io, List<Supplier> suppliers) {
        Product product = Product.getProductFromIO(io);
        int amount = io.readInt("Enter amount of product:");

        return new ProductInOrder(product, amount);
    }

    public Product product() {
        return product;
    }

    public String name() {
        return product.name;
    }

    public int amount() {
        return amount;
    }

    @Override
    public String toString() {
        return "{product=" + product +
                ", count=" + amount +
                '}';
    }
}
