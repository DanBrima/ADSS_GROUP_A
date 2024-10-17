package Domain;

import Presentation.IO;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table
public class Product {
    @Id
    @Column
    public String name;
    @ManyToOne
    private Manufacturer manufacturer;

    public Product() {
    }

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
