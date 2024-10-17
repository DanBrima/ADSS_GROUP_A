package Domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Storage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "storage_id")
    private List<ItemStack> inventory = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "defective_storage_id")
    private List<ItemStack> defectiveItems = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "store_id") // Foreign key to Store
    private Store store; // Reference to Store


    // Constructor for empty inventory
    public Storage() {
        this.inventory = new ArrayList<>();
        this.defectiveItems = new ArrayList<>();
    }

    // Constructor with item stack
    public Storage(ItemStack itemStack) {
        this();
        this.addItemStack(itemStack);
    }

    // Methods
    public void addItemStack(ItemStack itemStack) {
        this.inventory.add(itemStack);
        itemStack.setLocation(new StackLocation("inventory", this.inventory.size() - 1));
    }

    public void addDefectiveItemStack(ItemStack itemStack) {
        this.defectiveItems.add(itemStack);
        itemStack.setLocation(new StackLocation("defect", this.defectiveItems.size() - 1));
    }

    public List<ItemStack> getInventory() {
        return inventory;
    }

    public ItemStack pullItemsStack(String itemName) {
        for (int i = 0; i < this.inventory.size(); i++) {
            ItemStack itemStack = this.inventory.get(i);
            if (itemStack.getItemType().getName().equals(itemName)) {
                return this.inventory.remove(i);
            }
        }
        return null;
    }

    public List<ItemStack> getDefectiveItems() {
        return new ArrayList<>(defectiveItems);
    }
}
