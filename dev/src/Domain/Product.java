package Domain;

import Presentation.IO;

public class Product {
    public String name;
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


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        return name + ", manufacturer=" + manufacturer;
    }
}
