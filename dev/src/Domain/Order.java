package Domain;

import Presentation.IO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


// Order has creation Date and Products
public class Order {
    private List<ProductInOrder> products;
    private Date date;

    public Order(){
        this.products = new ArrayList<>();
        this.date = new Date();
    }

    public Order(List<ProductInOrder> products){
        this.products = products;
    }


    // What will happen in here is that ProductInOrder will be created without
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

    public boolean addProduct(ProductInOrder orderProduct){
        products.add(orderProduct);
        return true;
    }

    public List<ProductInOrder> getProducts(){
        return this.products;
    }

}
