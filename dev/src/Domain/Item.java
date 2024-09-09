package Domain;

import java.math.BigDecimal;
import java.util.UUID;

public class Item {

    private final UUID BARCODE;
    private String name;
    private BigDecimal price;
    private String supplier;

    //TODO: add SubSubCategory
    private BigDecimal supplierCost;
    private int requiredAmount;

    public Item(String name, BigDecimal price, String supplier, BigDecimal supplierCost, int requiredAmount) {
        this.BARCODE = UUID.randomUUID();
        this.name = name;
        this.price = price;
        this.supplier = supplier;
        this.supplierCost = supplierCost;
        this.requiredAmount = requiredAmount;
    }

    public UUID getBARCODE() {
        return BARCODE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public BigDecimal getSupplierCost() {
        return supplierCost;
    }

    public void setSupplierCost(BigDecimal supplierCost) {
        this.supplierCost = supplierCost;
    }

    public int getRequiredAmount() {
        return requiredAmount;
    }

    public void setRequiredAmount(int requiredAmount) {
        this.requiredAmount = requiredAmount;
    }
}
