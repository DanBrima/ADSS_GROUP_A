package Domain.Items;

import java.util.UUID;

public class ItemInstance {

    private final UUID uuid;
    private final Item itemType;

    //TODO: add location


    public ItemInstance(Item itemType) {
        this.uuid = UUID.randomUUID();
        this.itemType = itemType;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Item getItemType() {
        return itemType;
    }
}
