package domain;

import presentation.IO;

public class Contact {
    private String name;
    private String phoneNumber;

    public Contact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public static Contact getContactFromIO(IO io) {
        return new Contact(io.readString("Enter the contact's name:"),
                io.readString("Enter the contact's phone number:"));
    }
}
