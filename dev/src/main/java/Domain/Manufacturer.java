package Domain;

import Presentation.IO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Manufacturer {
    @Id
    @Column
    private String name;

    public Manufacturer() {
    }

    public Manufacturer(String name) {
        this.name = name;
    }

    public static Manufacturer getManufacturerFromIO(IO io) {
        String name = io.readString("Enter the manufacturer name:");
        
        return new Manufacturer(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
