package Domain.Items;

import Domain.Categories.SubSubCategory;

import java.math.BigDecimal;
import java.util.UUID;

public class Item {

    private final UUID BARCODE;
    private String name;
    private BigDecimal price;
    private String supplier;

    private SubSubCategory category;
    private BigDecimal supplierCost;
    private int requiredAmount;

    private ItemPriceHistory itemPriceHistory;

    public Item(String name, BigDecimal price, String supplier, BigDecimal supplierCost, int requiredAmount, SubSubCategory category) {
        this.BARCODE = UUID.randomUUID();
        this.name = name;
        this.price = price;
        this.supplier = supplier;
        this.supplierCost = supplierCost;
        this.requiredAmount = requiredAmount;
        this.category = category;
        this.itemPriceHistory = new ItemPriceHistory(price, supplierCost);
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
        this.itemPriceHistory.addStorePriceToHistory(price);
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
        this.itemPriceHistory.addSupplierCostToHistory(supplierCost);
    }

    public int getRequiredAmount() {
        return requiredAmount;
    }

    public void setRequiredAmount(int requiredAmount) {
        this.requiredAmount = requiredAmount;
    }

    public SubSubCategory getCategory() {
        return category;
    }

    public ItemPriceHistory getItemPriceHistory() {
        return itemPriceHistory;
    }
}
