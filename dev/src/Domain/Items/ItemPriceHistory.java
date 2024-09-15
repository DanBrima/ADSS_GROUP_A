package Domain.Items;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

public class ItemPriceHistory {
    private ArrayList<ItemPrice> itemPricesHistory;

    public ItemPriceHistory(BigDecimal price, BigDecimal cost) {
        this.itemPricesHistory = new ArrayList<>();
        this.itemPricesHistory.add(new ItemPrice(price, cost, new Date()));
    }

    public void addStorePriceToHistory(BigDecimal price) {
        BigDecimal lastCost = this.itemPricesHistory.get(this.itemPricesHistory.size()-1).getSupplierCost();
        this.itemPricesHistory.add(new ItemPrice(price, lastCost, new Date()));
    }

    public void addSupplierCostToHistory(BigDecimal cost) {
        BigDecimal lastPrice = this.itemPricesHistory.get(this.itemPricesHistory.size()-1).getStorePrice();
        this.itemPricesHistory.add(new ItemPrice(lastPrice, cost, new Date()));
    }

    public ArrayList<ItemPrice> getItemPricesHistory() {
        return itemPricesHistory;
    }

    public BigDecimal getCurrentPrice(){
        return this.itemPricesHistory.get(this.itemPricesHistory.size()-1).getStorePrice();
    }

    public BigDecimal getCurrentCost(){
        return this.itemPricesHistory.get(this.itemPricesHistory.size()-1).getSupplierCost();
    }
}
