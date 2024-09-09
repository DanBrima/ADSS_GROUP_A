package domain;

public class FixedDaysSupplier extends Supplier {
    private WeekDay[] arrivalDays;

    public FixedDaysSupplier(boolean needsPickup, String activeAccount, int bankAccount, PaymentOption paymentOption, Contact[] contacts, Contract[] contracts, WeekDay[] arrivalDays) {
        super(needsPickup, activeAccount, bankAccount, paymentOption, contacts, contracts);
        this.arrivalDays = arrivalDays;
    }
}
