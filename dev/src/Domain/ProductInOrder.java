package Domain;

import Presentation.IO;

import java.util.UUID;

public class ProductInOrder {
    private Product product;
    public int amount;

    public ProductInOrder(Product product, int amount) {
        this.product = product;
        this.amount = amount;
    }

    public static ProductInOrder getOrderFromIO(IO io) {
        Product product = Product.getProductFromIO(io);
        int amount = io.readInt("Enter amount of product:");
        return new ProductInOrder(product, amount);
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
