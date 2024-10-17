package Domain;

import Presentation.IO;

import javax.persistence.*;
import java.security.cert.CollectionCertStoreParameters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static javax.persistence.InheritanceType.SINGLE_TABLE;

@Entity
@Table
@Inheritance(strategy=SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING,
        name = "Supplier_Type")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private boolean needsPickup;
    @Column
    private String activeAccount;
    @Column
    private int bankAccount;
    @Column
    @Enumerated(EnumType.STRING)
    private PaymentOption paymentOption;
    @OneToMany
    @JoinColumn(name = "id")
    private List<Contact> contacts;
    @OneToMany
    @JoinColumn(name = "id")
    private List<Contract> contracts;

    public Supplier() {
    }

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
                .distinct()
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

    // To simplify the search if supplier hasProduct
    public Contract getContractSupplying(String productName) {
        return contracts.stream()
                .filter(contract -> contract.getProduct(productName) != null)
                .findFirst()
                .orElse(null);
    }
}
