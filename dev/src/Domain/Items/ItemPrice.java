package Domain.Items;

import java.math.BigDecimal;
import java.util.Date;

public class ItemPrice {
    private BigDecimal storePrice;
    private BigDecimal supplierCost;
    private Date updateDate;

    public ItemPrice(BigDecimal storePrice, BigDecimal supplierCost, Date updateDate) {
        this.storePrice = storePrice;
        this.supplierCost = supplierCost;
        this.updateDate = updateDate;
    }

    public BigDecimal getStorePrice() {
        return storePrice;
    }

    public BigDecimal getSupplierCost() {
        return supplierCost;
    }

    public Date getUpdateDate() {
        return updateDate;
    }
}
