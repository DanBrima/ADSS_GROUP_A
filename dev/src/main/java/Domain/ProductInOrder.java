package Domain;

import Presentation.IO;

import javax.persistence.*;
import java.util.List;

@Entity
public class ProductInOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary key for ProductInOrder

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int amount;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    public Supplier supplier;

    // Constructor
    public ProductInOrder(Product product, Supplier supplier, int amount) {
        this.product = product;
        this.amount = amount;
        this.supplier = supplier;
    }

    // Static method for IO
    public static ProductInOrder getOrderFromIO(IO io, List<Supplier> suppliers) {
        Product product = Product.getProductFromIO(io);
        int amount = io.readInt("Enter amount of product:");

        Supplier supplier = getCheapestSupplier(product.getName(), amount, suppliers);

        if (supplier != null) {
            return new ProductInOrder(product, supplier, amount);
        }
        throw new RuntimeException("No supplier provides");
    }

    // Getters
    public String getName() {
        return product.getName();
    }

    public int getAmount() {
        return amount;
    }



    // Static method to get the cheapest supplier
    public static Supplier getCheapestSupplier(String productName, int amount, List<Supplier> suppliers) {
        Supplier minSup = null;
        double minPrice = Double.MAX_VALUE; // Change to Double.MAX_VALUE for proper comparisons
        for (Supplier supplier : suppliers) {
            Contract con = supplier.getContractSupplying(productName);
            if (con != null) {
                double price = con.isDiscount(amount) ? con.getProduct(productName).priceWithDiscount() : con.getProduct(productName).price();
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
                ", amount=" + amount +
                ", supplier=" + supplier +
                '}';
    }
}
