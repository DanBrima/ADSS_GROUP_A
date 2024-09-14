package Domain;

import Presentation.IO;

public class Product {
    public String name;
    public int count;
    private Manufacturer manufacturer;

    public Product(String name, int count, Manufacturer manufacturer) {
        this.name = name;
        this.count = count;
        this.manufacturer = manufacturer;
    }

    public static Product getProductFromIO(IO io) {
        String name = io.readString("Enter the name of the product:");
        int count = io.readInt("Enter amount of product:");
        Manufacturer manufacturer = Manufacturer.getManufacturerFromIO(io);

        return new Product(name, count, manufacturer);
    }

    @Override
    public String toString() {
        return name +
                ", manufacturer=" + manufacturer +
                ", count=" + count;
    }
}
