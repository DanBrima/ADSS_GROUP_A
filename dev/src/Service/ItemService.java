package Service;

import Domain.Items.ItemStack;
import Domain.Storage;
import Domain.Store;

public class ItemService {
    private Storage storageRef;
    private Store storeRef;

    public ItemService(Storage storageRef, Store storeRef) {
        this.storageRef = storageRef;
        this.storeRef = storeRef;
    }

    public void reportDefectItem (ItemStack itemStack, int amount) {
        itemStack.setItemCount(itemStack.getItemCount() - amount);

        if (itemStack.getItemCount() <= 0) {
            this.storeRef.getShelves().get(itemStack.getStackLocation().getShelfIndex()).getItemsOnShelf()
                    .remove(itemStack.getStackLocation().getItemStackIndex());
        }

        this.storageRef.addDefectiveItemStack(new ItemStack(itemStack.getItemType(), amount));
    }
}
