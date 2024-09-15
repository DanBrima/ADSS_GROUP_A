package Service;

import Domain.Items.Item;
import Domain.Items.ItemStack;
import Domain.Shelf;
import Domain.Storage;
import Domain.Store;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;

public class ItemService {

    public static boolean removeItemFromStore(Store storeRef, String type, int amount) {
        for (Shelf shelf : storeRef.getShelves()) {
            for (ItemStack itemStack : shelf.getItemsOnShelf()) {
                if (itemStack.getItemType().getName().equals(type)) {
                    if (itemStack.getItemCount() >= amount) {
                        itemStack.setItemCount(itemStack.getItemCount() - amount);
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public static boolean removeItemFromStorage(Storage storageRef, String type, int amount) {
        for (ItemStack itemStack : storageRef.getInventory()) {
            if (itemStack.getItemType().getName().equals(type)) {
                if (itemStack.getItemCount() >= amount) {
                    itemStack.setItemCount(itemStack.getItemCount() - amount);
                    return true;
                }
            }
        }

        return false;
    }

    public static ArrayList<ItemStack> getAllUniqueItemsFromStorage(Storage storageRef) {
        return ItemStackService.combineUniqueItems(storageRef.getInventory(), storageRef.getDefectiveItems());
    }

    public static ArrayList<ItemStack> getAllUniqueItemsFromStore(Store storeRef) {
        return ItemStackService.getAllUniqueItemsFromStore(storeRef.getShelves());
    }

    public static ArrayList<ItemStack> getAllUniqueItems(Storage storageRef, Store storeRef) {
        return ItemStackService.combineUniqueItems(getAllUniqueItemsFromStorage(storageRef), getAllUniqueItemsFromStore(storeRef));
    }

    public static ArrayList<ItemStack> getAllUniqueItemsWithoutDefective(Storage storageRef, Store storeRef) {
        return ItemStackService.combineUniqueItems(getAllUniqueItemsFromStore(storeRef), storageRef.getInventory());
    }

    public static void reportDefectItem (Storage storageRef, Store storeRef, ItemStack itemStack, int amount) {
        itemStack.setItemCount(itemStack.getItemCount() - amount);

        if (itemStack.getItemCount() <= 0) {
            for(Shelf shelf : storeRef.getShelves()) {
                if (shelf.getShelfId().equals(itemStack.getStackLocation().getShelfIndex())) {
                    shelf.getItemsOnShelf().remove(itemStack.getStackLocation().getItemStackIndex());
                }
            }
        }

        storageRef.addDefectiveItemStack(new ItemStack(itemStack.getItemType(), amount));
    }

    public static void addNewSupplyToStorage (Storage storageRef, Item item, int amount) {
        storageRef.addItemStack(new ItemStack(item, amount));
    }

    public static void updateSupplierCost (Storage storageRef, UUID itemId, BigDecimal newSupplierCost){
        for(ItemStack itemStack: storageRef.getInventory()){
            if(itemStack.getItemType().getBARCODE().equals(itemId)){
                itemStack.getItemType().setSupplierCost(newSupplierCost);
                break;
            }
        }

    }
}
