package Domain;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table
public class StackLocation {
    @Id
    private Long id;
    @Column
    private String place; // "Storage" or "Store"
    @Column
    private UUID shelfId; // Shelf index (if the product is on a shelf)
    @Column
    private String type; // "inventory" or "defect" (if the product is in the storage)
    @Column
    private int itemStackIndex;

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

    public StackLocation(String place, String type, UUID shelfId, int itemStackIndex) {
        this.place = place;
        this.type = type;
        this.shelfId = shelfId;
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

    public String getPlace() {
        return place;
    }

    public String getType() {
        return type;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
