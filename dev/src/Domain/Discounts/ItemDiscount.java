package Domain.Discounts;

import Domain.Items.Item;

import java.util.Date;

public class ItemDiscount extends Discount{
    private final Item item;

    public ItemDiscount(int percentage, Date startDate, Date finalDate, Item item) throws Exception {
        super(percentage, startDate, finalDate);
        this.item = item;
    }

    public Item getItem() {
        return item;
    }
}
