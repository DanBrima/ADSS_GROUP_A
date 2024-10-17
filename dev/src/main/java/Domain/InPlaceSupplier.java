package Domain;

import Presentation.IO;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@Entity
@DiscriminatorValue("InPlaceSupplier")
public class InPlaceSupplier extends Supplier {
    @Column
    private int deliveryDays;

    public InPlaceSupplier(boolean needsPickup, String activeAccount, int bankAccount, PaymentOption paymentOption, List<Contact> contacts, int deliveryTime) {
        super(needsPickup, activeAccount, bankAccount, paymentOption, contacts);
        this.deliveryDays = deliveryTime;
    }

    public static InPlaceSupplier getInPlaceSupplierFromIO(IO io) {
        Supplier supplier = Supplier.getSupplierFromIO(io);
        int deliveryDays = io.readInt("Enter the delivery days in hours:");

        return new InPlaceSupplier(supplier.needsPickup(), supplier.activeAccount(), supplier.bankAccount(), supplier.paymentOption(), supplier.contacts(), deliveryDays);
    }

    public int getDeliveryDays() {
        return deliveryDays;
    }

    public void setDeliveryDays(int deliveryDays) {
        this.deliveryDays = deliveryDays;
    }
}
