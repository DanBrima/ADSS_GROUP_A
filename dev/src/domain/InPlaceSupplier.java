package domain;

import java.util.List;

public class InPlaceSupplier extends Supplier {
    private int deliveryTime;

    public InPlaceSupplier(boolean needsPickup, String activeAccount, int bankAccount, PaymentOption paymentOption, List<Contact> contacts, List<Contract> contracts, int deliveryTime) {
        super(needsPickup, activeAccount, bankAccount, paymentOption, contacts, contracts);
        this.deliveryTime = deliveryTime;
    }
}
