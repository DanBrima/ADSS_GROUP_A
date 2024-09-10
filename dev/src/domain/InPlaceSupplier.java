package domain;

import presentation.IO;

import java.util.List;

public class InPlaceSupplier extends Supplier {
    private int deliveryTime;

    public InPlaceSupplier(boolean needsPickup, String activeAccount, int bankAccount, PaymentOption paymentOption, List<Contact> contacts, List<Contract> contracts, int deliveryTime) {
        super(needsPickup, activeAccount, bankAccount, paymentOption, contacts, contracts);
        this.deliveryTime = deliveryTime;
    }
    
    public static InPlaceSupplier getInPlaceSupplierFromIO(IO io) {
        Supplier supplier = Supplier.getSupplierFromIO(io);
        int deliveryTime = io.readInt("Enter the delivery time in hours:");

        return new InPlaceSupplier(supplier.needsPickup(), supplier.activeAccount(), supplier.bankAccount(), supplier.paymentOption(), supplier.contacts(), supplier.contracts(), deliveryTime);
    }
}
