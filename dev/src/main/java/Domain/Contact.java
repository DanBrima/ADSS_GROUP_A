package Domain;

import Presentation.IO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Contact {
    @Id
    @Column
    private String name;
    @Column
    private String phoneNumber;

    public Contact() {
    }

    public Contact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public static Contact getContactFromIO(IO io) {
        return new Contact(io.readString("Enter the contact's name:"),
                io.readString("Enter the contact's phone number:"));
    }

    public String name() {
        return this.name;
    }

    @Override
    public String toString() {
        return name + ": " + phoneNumber;
    }
}
