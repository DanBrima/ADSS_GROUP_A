package domain;

public class InPlaceSupplier extends Supplier {
    private int deliveryTime;

    public InPlaceSupplier(boolean needsPickup, String activeAccount, int bankAccount, PaymentOption paymentOption, Contact[] contacts, Contract[] contracts, int deliveryTime) {
        super(needsPickup, activeAccount, bankAccount, paymentOption, contacts, contracts);
        this.deliveryTime = deliveryTime;
    }
}
