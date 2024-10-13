package Domain;

import Presentation.IO;

import java.util.List;
import java.util.UUID;

public class ProductInOrder {
    private Product product;
    public int amount;

    public  Supplier supplier;

    public ProductInOrder(Product product, Supplier supplier, int amount) {
        this.product = product;
        this.amount = amount;
        this.supplier = supplier;
    }

    public static ProductInOrder getOrderFromIO(IO io, List<Supplier> suppliers) {
        Product product = Product.getProductFromIO(io);
        Supplier supplier = Controller.getCheapestSupplier(product.name, suppliers);
        assert supplier != null;
        if (supplier != null){
            int amount = io.readInt("Enter amount of product:");
            return new ProductInOrder(product, supplier, amount);
        }
        else
            return null;
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
