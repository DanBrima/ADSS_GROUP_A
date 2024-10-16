package Domain;

import Presentation.IO;

public class Manufacturer {
    private String name;

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
