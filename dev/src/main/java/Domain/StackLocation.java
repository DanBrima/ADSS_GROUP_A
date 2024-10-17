package Domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table
public class StackLocation {
    @Column
    private String place; // "Storage" or "Store"
    @Column
    private UUID shelfId; // Shelf index (if the product is on a shelf)
    @Column
    private String type; // "inventory" or "defect" (if the product is in the storage)
    @Column
    private int itemStackIndex;
    @Id
    private Long id;

    public StackLocation() {
    }

    public StackLocation(UUID SHELF_ID, int itemStackIndex) {
        this.place = "Store";
        this.shelfId = SHELF_ID;
        this.itemStackIndex = itemStackIndex;
    }

    public StackLocation(String type, int itemStackIndex) {
        this.place = "Storage";
        this.type = type;
        this.itemStackIndex = itemStackIndex;
    }

    public UUID getShelfIndex() {
        return shelfId;
    }

    public int getItemStackIndex() {
        return itemStackIndex;
    }

    public void setShelfIndex(UUID shelfIndex) {
        this.shelfId = shelfIndex;
    }

    public void setItemStackIndex(int itemStackIndex) {
        this.itemStackIndex = itemStackIndex;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
