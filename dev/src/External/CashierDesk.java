package External;

import Domain.Categories.Category;
import Domain.Discounts.CategoryDiscount;
import Domain.Discounts.DiscountsHistory;
import Domain.Discounts.ItemDiscount;
import Domain.Items.Item;
import Domain.Storage;
import Domain.Store;
import Presentation.*;
import Service.ItemService;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Scanner;

public class CashierDesk {
    private final PrintStream out;
    private final Scanner in;
    private Boolean isActivated = false;
    private Storage storageRef;
    private Store storeRef;


    public CashierDesk(PrintStream out, Scanner in, Storage storageRef, Store storeRef) {
        this.out = out;
        this.in = in;
        this.storageRef = storageRef;
        this.storeRef = storeRef;
    }

    public void turnOn() throws Exception {
        this.isActivated = true;
        DiscountsHistory discountsHistory = new DiscountsHistory();
        discountsHistory.discountList.add(new ItemDiscount(10, new Date(), new Date(),
                new Item("Watermelon", new BigDecimal(10), "more test", new BigDecimal(100), 10, null)));
        discountsHistory.discountList.add(new CategoryDiscount(10, new Date(), new Date(),
                new Category("Cloth")));

        while (this.isActivated) {
            // Activate Menu
            DefaultMenuScreen defaultMenuScreen = new DefaultMenuScreen(this.out, this.in);
            int userInput = defaultMenuScreen.handleMsg();
            switch (userInput) {
                case Constants.CURRENT_ITEMS_INDEX: {
                    AmountsScreen amountsScreen = new AmountsScreen(this.out, this.in);
                    userInput = amountsScreen.handleMsg();

                    switch (userInput) {
                        case Constants.DISPLAY_STORE_ITEMS_INDEX: {
                            StoreAmountScreen storeAmountScreen = new StoreAmountScreen(this.out, this.in, this.storeRef);
                            storeAmountScreen.handleMsg();
                            break;
                        }
                        case Constants.DISPLAY_STORAGE_ITEMS_INDEX: {
                            StorageAmountScreen storageAmountScreen = new StorageAmountScreen(this.out, this.in, this.storageRef);
                            storageAmountScreen.handleMsg();
                            break;
                        }
                        default: {
                            this.out.println(Constants.INVALID_INPUT);
                            break;
                        }
                    }
                    break;
                }
                case Constants.MISSING_ITEMS_RECORD_INDEX: {
                    MissingItemsScreen missingItemsScreen = new MissingItemsScreen(this.out, this.in, this.storeRef, this.storageRef);
                    missingItemsScreen.handleMsg();
                    break;
                }
                case Constants.DISCOUNTS_HISTORY_INDEX: {
                    DiscountsHistoryScreen discountsHistoryScreen = new DiscountsHistoryScreen(this.out, this.in, discountsHistory);
                    discountsHistoryScreen.handleMsg();
                    break;
                }
                case Constants.REMOVE_ITEMS_INDEX: {
                    RemoveItemScreen removeItemScreen = new RemoveItemScreen(this.out, this.in);
                    userInput = removeItemScreen.handleMsg();
                    switch (userInput) {
                        case Constants.REMOVE_ITEM_STORE_INDEX: {
                            RemoveItemsStoreScreen removeItemsStoreScreen = new RemoveItemsStoreScreen(this.out, this.in, this.storeRef);
                            removeItemsStoreScreen.handleMsg();
                            break;
                        }
                        case Constants.REMOVE_ITEM_STORAGE_INDEX: {
                            RemoveItemsStorageScreen removeItemsStorageScreen = new RemoveItemsStorageScreen(this.out, this.in, this.storageRef);
                            removeItemsStorageScreen.handleMsg();
                            break;
                        }
                        default: {
                            this.out.println(Constants.INVALID_INPUT);
                            break;
                        }
                    }
                    break;
                }
                case Constants.DISPLAY_DEFECTIVE_ITEMS_INDEX: {
                    DefectiveItemsScreen defectiveItemsScreen = new DefectiveItemsScreen(this.out, this.in, this.storageRef);
                    defectiveItemsScreen.handleMsg();
                    break;
                }
                case Constants.TURN_OFF_INDEX: {
                    this.turnOff();
                    break;
                }
                case Constants.TRANSFER_ITEMS_INDEX: {
                    TransferItemsScreen transferItemsScreen = new TransferItemsScreen(this.out, this.in, this.storeRef, this.storageRef);
                    transferItemsScreen.handleMsg();
                    break;
                }
                case Constants.ITEM_PRICE_HISTORY_INDEX: {
                    ItemPriceHistoryScreen itemPriceHistoryScreen = new ItemPriceHistoryScreen(this.out, this.in, this.storeRef, this.storageRef);
                    itemPriceHistoryScreen.handleMsg();
                    break;
                }
                case Constants.CREATE_NEW_DISCOUNT_INDEX: {
                    AddDiscountScreen addDiscountScreen = new AddDiscountScreen(this.out, this.in);
                    userInput = addDiscountScreen.handleMsg();
                    switch (userInput) {
                        case Constants.DISCOUNT_TYPE_CATEGORY_INDEX: {
                            AddCategoryDiscountScreen addCategoryDiscountScreen = new AddCategoryDiscountScreen(this.out, this.in, discountsHistory);
                            addCategoryDiscountScreen.handleMsg();
                            break;
                        }
                        case Constants.DISCOUNT_TYPE_ITEM_INDEX: {
                            AddItemDiscountScreen addItemDiscountScreen = new AddItemDiscountScreen(this.out, this.in, discountsHistory);
                            addItemDiscountScreen.handleMsg();
                            break;
                        }
                        default: {
                            this.out.println(Constants.INVALID_INPUT);
                            break;
                        }
                    }
                    break;
                }
                default: {
                    this.out.println(Constants.INVALID_INPUT);
                    break;
                }
            }
        }
    }

    private void turnOff() {
        this.out.println("\n" + Constants.GOODBYE_MSG);
        this.isActivated = false;
    }
}
