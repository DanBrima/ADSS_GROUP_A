package Domain;

import Presentation.IO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


// Order has supplier and products
// Do in place order needs to have specific date? Otherwise it will stay in the system forever
public class Order {

    private Supplier supplier;
    // TOBE DELETED Since store creates order, the order doesn't need to keep the store
    //private Store store;
    private List<ProductInOrder> products;

    // We can add reference to the product in contract instead of look for the related one every time

    public Order(Supplier supplier){
//        this.store = store;
        this.supplier = supplier;
        this.products = new ArrayList<>();
    }

    public Order(Supplier supplier, List<ProductInOrder> products){
//        this.store = store;
        this.supplier = supplier;
        this.products = products;
    }

    public static Order getOrderFromIO(IO io, Supplier orderSupplier) {
        int productCount = io.readInt("Enter the number of different products in the order (must be 1 or more:");
        assert productCount > 0;
        // Doesn't validate the products in contracts as "addProduct" does
        List<ProductInOrder> products = Stream.generate(() -> ProductInOrder.getOrderFromIO(io)).limit(productCount).toList();
        return new Order(orderSupplier, products);
    }

    public void addProduct(ProductInOrder orderProduct){
        for (ProductInContract contractProduct: supplier.getAllProductsInContracts()){
            if (orderProduct.name() == contractProduct.name())
                products.add(orderProduct);
        }
        // throw invalid product from supplier
    }

    public List<ProductInOrder> getProducts(){
        return this.products;
    }
    public Supplier getSupplier(){ return this.supplier; }

    // Example on how to nevigate from supplier to ProductInContract to compare to ProductInOrder
    public boolean isDiscount() {
        for (ProductInOrder orderProduct: products){
            for (Contract contract: supplier.contracts()){
                for (ProductInContract contractProduct: contract.products()){
                    if (contractProduct.name() == orderProduct.name()){
                        if (contract.isDiscount(orderProduct.amount))
                                return true;
                    }
                }
            }
        }
        return false;
    }
}
