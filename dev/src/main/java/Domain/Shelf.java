package Domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table
public class Shelf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private UUID shelfId;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id")
    private List<ItemStack> itemsOnShelf = new ArrayList<>();

    // Constructor for empty shelf
    public Shelf() {
        this.shelfId = UUID.randomUUID();
        this.itemsOnShelf = new ArrayList<>();
    }

    // Constructor from item stack
    public Shelf(ItemStack itemStack) {
        this.shelfId = UUID.randomUUID();
        this.itemsOnShelf = new ArrayList<>();
        this.addItemStack(itemStack);
    }

    // Constructor from another shelf
    public Shelf(Shelf shelf) {
        this.shelfId = UUID.randomUUID();
        this.itemsOnShelf = new ArrayList<>(shelf.getItemsOnShelf());
    }

    // Add an item stack to the shelf
    public void addItemStack(ItemStack itemStack) {
        this.itemsOnShelf.add(itemStack);
        itemStack.setLocation(new StackLocation(this.shelfId, itemsOnShelf.size() - 1));
    }

    // Getters
    public UUID getShelfId() {
        return shelfId;
    }

    public List<ItemStack> getItemsOnShelf() {
        return new ArrayList<>(itemsOnShelf);
    }
}
