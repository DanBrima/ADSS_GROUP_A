package External;

import Domain.Discounts.DiscountsHistory;
import Domain.Discounts.ItemDiscount;
import Domain.Items.Item;
import Domain.Storage;
import Domain.Store;
import Presentation.*;

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
        //TODO: maybe there is some default values to generate here (like history or something)
        DiscountsHistory discountsHistory = new DiscountsHistory();
        discountsHistory.discountList.add(new ItemDiscount(10, new Date(), new Date(),
                new Item("test", new BigDecimal(10), "more test", new BigDecimal(100), 10, null)));

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
                case Constants.DEAL_HISTORY_INDEX: {
                    //TODO: create this screen
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
                case Constants.DISPLAY_DEFECTIVE_ITEMS_INDEX: {
                    DefectiveItemsScreen defectiveItemsScreen = new DefectiveItemsScreen(this.out, this.in, this.storageRef);
                    defectiveItemsScreen.handleMsg();
                    break;
                }
                case Constants.TURN_OFF_INDEX: {
                    this.turnOff();
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
