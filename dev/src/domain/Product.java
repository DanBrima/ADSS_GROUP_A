package domain;

import presentation.IO;

public class Product {
    private String name;
    private Manufacturer manufacturer;

    public Product(String name, Manufacturer manufacturer) {
        this.name = name;
        this.manufacturer = manufacturer;
    }

    public static Product getProductFromIO(IO io) {
        String name = io.readString("Enter the name of the product:");
        Manufacturer manufacturer = Manufacturer.getManufacturerFromIO(io);

        return new Product(name, manufacturer);
    }
}
