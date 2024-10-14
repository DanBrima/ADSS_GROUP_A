package Domain;

import Presentation.IO;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Stream;


// Order has supplier and products
// Do in place order needs to have specific date? Otherwise it will stay in the system forever
public class Order {
    private List<ProductInOrder> products;

    // We can add reference to the product in contract instead of look for the related one every time

    public Order(){
        this.products = new ArrayList<>();
    }

    public Order(List<ProductInOrder> products){
        this.products = products;
    }


    // What will happen in here is that ProductInOrder will be created without
    public static Order getOrderFromIO(IO io, List<Supplier> suppliers) {
        int productCount = io.readInt("Enter the number of different products in the order (must be 1 or more:");
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


//    public boolean isDiscount(ProductInOrder product) {
//        for (Contract contract: supplier.contracts()){
//            for (ProductInContract contractProduct: contract.products()){
//                if (Objects.equals(contractProduct.name(), product.name()) && contract.isDiscount(product.amount())){
//                    return true;
//                }
//            }
//        }
//
//        return false;
//    }
}
