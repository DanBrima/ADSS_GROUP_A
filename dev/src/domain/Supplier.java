package domain;

import presentation.IO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Supplier {
    private boolean needsPickup;
    private String activeAccount;
    private int bankAccount;
    private PaymentOption paymentOption;
    private List<Contact> contacts;
    private List<Contract> contracts;

    protected Supplier(boolean needsPickup, String activeAccount, int bankAccount, PaymentOption paymentOption, List<Contact> contacts) {
        this.needsPickup = needsPickup;
        this.activeAccount = activeAccount;
        this.bankAccount = bankAccount;
        this.paymentOption = paymentOption;
        this.contacts = contacts;
        this.contracts = new ArrayList<>();
    }

    // creates supplier without contracts
    protected static Supplier getSupplierFromIO(IO io) {
        boolean needsPickup = "yes".equals(io.readString("Does the supplier need pickup (type yes/no)?"));
        String activeAccount = io.readString("Enter the supplier's active account:");
        int bankAccount = io.readInt("Enter the supplier's bank account:");
        PaymentOption paymentOption = PaymentOption.getPaymentOptionFromIO(io);
        int contactCount = io.readInt("Enter the number of contacts:");
        List<Contact> contacts = Stream.generate(() -> Contact.getContactFromIO(io)).limit(contactCount).toList();

        return new Supplier(needsPickup, activeAccount, bankAccount, paymentOption, contacts);
    }

    public List<ProductInContract> getAllProductsInContracts() {
        return contracts.stream()
                .flatMap(contract -> contract.products().stream())
                .collect(Collectors.toList());
    }

    public void addContract(Contract contract) {
        this.contracts.add(contract);
    }

    public boolean needsPickup() {
        return this.needsPickup;
    }

    public String activeAccount() {
        return this.activeAccount;
    }

    public int bankAccount() {
        return this.bankAccount;
    }

    public PaymentOption paymentOption() {
        return this.paymentOption;
    }

    public List<Contact> contacts() {
        return Collections.unmodifiableList(this.contacts);
    }

    public List<Contract> contracts() {
        return Collections.unmodifiableList(this.contracts);
    }
}
