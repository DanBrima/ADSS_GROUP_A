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
        int amount = io.readInt("Enter amount of product:");


        // Remain calling "cheapestSupplier" in PIO since we need to create the "product" first and Order isn't
        // associated with Product, only then check for supplier and lastly create the PIO and return it
        Supplier supplier = getCheapestSupplier(product.name, amount, suppliers);

        if (supplier != null){
            return new ProductInOrder(product, supplier, amount);
        }
        else
            throw new RuntimeException("No supplier provides");
    }

    public String name() {
        return product.name;
    }

    public int amount() {
        return amount;
    }


    // Gets also the amount to check if there is a discount for the checked supplier
    public static Supplier getCheapestSupplier(String product, int amount, List<Supplier> suppliers){
        Supplier minSup = null;
        double minPrice = Integer.MAX_VALUE;
        for (Supplier supplier: suppliers){

            // This is weird we use "Contract" resource from PIO, but I have no idea how to deal with that
            Contract con = supplier.getContractSupplying(product);
            if (con != null) {
                double price = con.isDiscount(amount) ? con.hasProduct(product).priceWithDiscount() : con.hasProduct(product).price();
                if (minPrice > price) {
                    minPrice = price;
                    minSup = supplier;
                }
            }
        }
        return minSup;
    }

    @Override
    public String toString() {
        return "{product=" + product +
                ", count=" + amount +
                ", supplier=" + supplier +
                '}';
    }
}
