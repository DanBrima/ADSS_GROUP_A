package Domain;

import Presentation.IO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "\"Order\"")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary key for Order

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_id")
    private List<ProductInOrder> products;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "store_id") // Foreign key column in Order table
    private Store store; // Reference to Store


    // Default constructor for Hibernate
    public Order() {
        this.products = new ArrayList<>();
        this.date = new Date();
    }

    public Order(List<ProductInOrder> products) {
        this.products = products;
        this.date = new Date();
    }

    public static Order getOrderFromIO(IO io, List<Supplier> suppliers) {
        int productCount = io.readInt("Enter the number of different products in the order (must be 1 or more:)");
        assert productCount > 0;
        List<ProductInOrder> products = new ArrayList<>();
        for (int i = 0; i < productCount; i++) {
            ProductInOrder product = ProductInOrder.getOrderFromIO(io, suppliers);
            if (product != null)
                products.add(product);
        }
        return new Order(products);
    }

    public boolean addProduct(ProductInOrder orderProduct) {
        products.add(orderProduct);
        return true;
    }

    public List<ProductInOrder> getProducts() {
        return this.products;
    }

    public Date getDate() {
        return date;
    }
}
