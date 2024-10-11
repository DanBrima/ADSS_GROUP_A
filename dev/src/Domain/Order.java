package Domain;

import Presentation.IO;

import java.util.ArrayList;
import java.util.List;
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

    public static Order getOrderFromIO(IO io, List<Supplier> suppliers) {
        int productCount = io.readInt("Enter the number of different products in the order (must be 1 or more):");
        assert productCount > 0;
        List<ProductInOrder> products = new ArrayList<>();
//        List<ProductInOrder> products = Stream.generate(() -> ProductInOrder.getOrderFromIO(io)).limit(productCount).toList();
//        return new Order(products);

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

    public double getPrice(){
        double price = 0;
        for (ProductInOrder orderProduct: products){
            for (Contract contract: orderProduct.supplier.contracts()){
                for (ProductInContract contractProduct: contract.products()){
                    if (contractProduct.name() == orderProduct.name()){
                        if (contract.isDiscount(orderProduct.amount))
                            price+=(contractProduct.priceWithDiscount()*orderProduct.amount);
                        else
                            price+=(contractProduct.price()*orderProduct.amount);
                    }
                }
            }
        }
        return price;
    }

//    // Example on how to nevigate from supplier to ProductInContract to compare to ProductInOrder
//    public boolean isDiscount() {
//        for (ProductInOrder orderProduct: products){
//            for (Contract contract: supplier.contracts()){
//                for (ProductInContract contractProduct: contract.products()){
//                    if (contractProduct.name() == orderProduct.name()){
//                        if (contract.isDiscount(orderProduct.amount))
//                                return true;
//                    }
//                }
//            }
//        }
//        return false;
//    }
//
//
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
