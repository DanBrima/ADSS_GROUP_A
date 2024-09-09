package domain;

import presentation.IO;

public class Manufacturer {
    private String name;

    public Manufacturer(String name) {
        this.name = name;
    }

    public static Manufacturer getManufacturerFromIO(IO io) {
        String name = io.readString("Enter the manufacturer name:");
        
        return new Manufacturer(name);
    }
}
