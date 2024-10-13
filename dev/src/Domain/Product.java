package Domain;

import Presentation.IO;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name) && Objects.equals(manufacturer, product.manufacturer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, manufacturer);
    }

    @Override
    public String toString() {
        return name + ", manufacturer=" + manufacturer;
    }
}
