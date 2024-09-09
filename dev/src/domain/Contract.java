package domain;

import presentation.IO;

import java.util.stream.Stream;

public class Contract {
    private int totalDiscountPercentage;
    private ProductInContract[] products;

    public Contract(int totalDiscountPercentage, ProductInContract[] products) {
        this.totalDiscountPercentage = totalDiscountPercentage;
        this.products = products;
    }

    public static Contract getContractFromIO(IO io) {
        int totalDiscountPercentage = io.readInt("Enter the total discount percentage:");
        int productCount = io.readInt("Enter the number of products:");
        ProductInContract[] products = Stream.generate(() -> ProductInContract.getContractFromIO(io)).limit(productCount).toArray();


        return new Contract(totalDiscountPercentage, products);
    }
}
