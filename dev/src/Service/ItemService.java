package Service;

import Domain.Items.Item;
import Domain.Items.ItemStack;
import Domain.Shelf;
import Domain.Storage;
import Domain.Store;

import java.util.ArrayList;

public class ItemService {
    private Storage storageRef;
    private Store storeRef;

    public ItemService(Storage storageRef, Store storeRef) {
        this.storageRef = storageRef;
        this.storeRef = storeRef;
    }

    public ArrayList<ItemStack> getAllUniqueItemsFromStorage() {
        return ItemStackService.combineUniqueItems(this.storageRef.getInventory(), this.storageRef.getDefectiveItems());
    }

    public ArrayList<ItemStack> getAllUniqueItemsFromStore() {
        return ItemStackService.getAllUniqueItemsFromStore(this.storeRef.getShelves());
    }

    public ArrayList<ItemStack> getAllUniqueItems() {
        return ItemStackService.combineUniqueItems(getAllUniqueItemsFromStorage(), getAllUniqueItemsFromStore());
    }

    public ArrayList<ItemStack> getAllUniqueItemsWithoutDefective() {
        return ItemStackService.combineUniqueItems(getAllUniqueItemsFromStore(), this.storageRef.getInventory());
    }

    public void reportDefectItem (ItemStack itemStack, int amount) {
        itemStack.setItemCount(itemStack.getItemCount() - amount);

        if (itemStack.getItemCount() <= 0) {
            for(Shelf shelf : this.storeRef.getShelves()) {
                if (shelf.getShelfId().equals(itemStack.getStackLocation().getShelfIndex())) {
                    shelf.getItemsOnShelf().remove(itemStack.getStackLocation().getItemStackIndex());
                }
            }
        }

        this.storageRef.addDefectiveItemStack(new ItemStack(itemStack.getItemType(), amount));
    }

    public void addNewSupplyToStorage (Item item, int amount) {
        this.storageRef.addItemStack(new ItemStack(item, amount));
    }
}
