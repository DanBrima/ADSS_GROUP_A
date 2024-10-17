package Domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table
public class Shelf {
    @Id
    private UUID shelfId;
    @OneToMany(fetch = FetchType.EAGER)
    private List<ItemStack> itemsOnShelf;

    // Constructor for empty inventory
    public Shelf() {
        this.itemsOnShelf = new ArrayList<ItemStack>();
    }


    // Constructor from item stack
    public Shelf(ItemStack itemStack) {
        this.shelfId = UUID.randomUUID();
        this.itemsOnShelf = new ArrayList<ItemStack>();
        this.addItemStack(itemStack);
    }

    // Constructor from another shelf
    public Shelf(Shelf shelf) {
        this.shelfId = UUID.randomUUID();
        this.itemsOnShelf = new ArrayList<ItemStack>(shelf.getItemsOnShelf());
    }


    public void addItemStack(ItemStack itemStack) {
        this.itemsOnShelf.add(itemStack);
        itemStack.setLocation(new StackLocation(this.shelfId, itemsOnShelf.size()-1));
    }

    public UUID getShelfId() {
        return shelfId;
    }

    public ArrayList<ItemStack> getItemsOnShelf() {
        ArrayList<ItemStack> itemCopies = new ArrayList<>();
        for (ItemStack itemStack : this.itemsOnShelf) {
            itemCopies.add(itemStack.deepCopy());
        }
        return itemCopies;
    }
}
