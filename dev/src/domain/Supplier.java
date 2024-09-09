package domain;

abstract public class Supplier {
    private boolean needsPickup;
    private String activeAccount;
    private int bankAccount;
    private PaymentOption paymentOption;
    private Contact[] contacts;
    private Contract[] contracts;

    public Supplier(boolean needsPickup, String activeAccount, int bankAccount, PaymentOption paymentOption, Contact[] contacts, Contract[] contracts) {
        this.needsPickup = needsPickup;
        this.activeAccount = activeAccount;
        this.bankAccount = bankAccount;
        this.paymentOption = paymentOption;
        this.contacts = contacts;
        this.contracts = contracts;
    }
}
