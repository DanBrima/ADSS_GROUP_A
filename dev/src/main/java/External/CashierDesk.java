/*
WHAT I NEED TO DO IN THIS PAGE IS INCLUDE OUR "SCREENS" INCLUDING SUPPLIERS AND CONTRACTS.
*/

package External;

import Domain.*;
import Presentation.*;
import Presentation.Inventory.*;
import Presentation.Stores.*;
import Presentation.Suppliers.*;
import Presentation.Suppliers.DefaultMenuScreenSupp;

import Presentation.Inventory.DefaultMenuScreen;
import java.io.PrintStream;
import java.util.Scanner;

public class CashierDesk {
    private final PrintStream out;
    private final Scanner in;
    private final IO io;
    private Boolean isActivated = false;
    private Store chosenStore = null;

    public CashierDesk(PrintStream out, Scanner in) {
        this.out = out;
        this.in = in;
        this.io = new ConsoleIO();
    }

    public void turnOn() throws Exception {
        this.isActivated = true;

        while (this.isActivated) {
            //SWITCH case to select stores or suppliers
            MainMenuScreen mainMenuScreen = new MainMenuScreen(this.out, this.in);
            int userInput = mainMenuScreen.handleMsg();
            switch (userInput) {
                case 1: {
                    Controller.controllerInstance().addStore(io);
                    break;
                }
                case 2: {
                    displayStores();
                    break;
                }
                case 3: {
                    Controller.controllerInstance().addSupplier(io);
                    break;
                }
                case 4: {
                    displaySuppliers();
                    break;
                }
                case 5: {
                    this.turnOff();
                    break;
                }
                default: {
                    this.out.println(InventoryConstants.INVALID_INPUT);
                    break;
                }
            }
        }
    }


    private boolean displayStores() {
        StoresScreen storesScreen = new StoresScreen(this.out, this.in);
        int userInput = storesScreen.handleMsg();

        if (userInput >= Controller.controllerInstance().getStores().size() || userInput < 0) {
            this.out.println(SuppliersConstants.INVALID_INPUT);
            return false;
        }
        this.chosenStore = Controller.controllerInstance().getStores().get(userInput);

        StoreScreen storeScreen = new StoreScreen(this.out, this.in);
        userInput = storeScreen.handleMsg();
        switch (userInput) {
            case StoreConstants.DISPLAY_INVENTORY_INDEX:
                displayInventory();
                break;
            case StoreConstants.DISPLAY_ORDERS_INDEX:;
                displayOrders();
                break;
        }
        return true;
    }

    private void turnOff() {
        this.out.println("\n" + SuppliersConstants.GOODBYE_MSG);
        this.isActivated = false;
    }

    private void displayOrders() {
        OrdersScreen ordersScreen = new OrdersScreen(this.out, this.in, this.chosenStore);
        int userInput = ordersScreen.handleMsg();
        if (userInput >= SuppliersConstants.ADD_ORDER_INDEX + 2 || userInput < 0) {
            this.out.println(SuppliersConstants.INVALID_INPUT);
        } else if (userInput == SuppliersConstants.ADD_ORDER_INDEX) {
            this.chosenStore.addOrder(io, Controller.controllerInstance().getSuppliers());
        } else if (userInput == SuppliersConstants.ADD_ORDER_INDEX + 1) {
            displayStores();
        }
    }


    private void displayInventory() {
        // Activate Menu
        DefaultMenuScreen defaultMenuScreen = new DefaultMenuScreen(this.out, this.in);
        int userInput = defaultMenuScreen.handleMsg();
        switch (userInput) {
            case InventoryConstants.CURRENT_ITEMS_INDEX: {
                AmountsScreen amountsScreen = new AmountsScreen(this.out, this.in);
                userInput = amountsScreen.handleMsg();

                switch (userInput) {
                    case InventoryConstants.DISPLAY_STORE_ITEMS_INDEX: {
                        StoreAmountScreen storeAmountScreen = new StoreAmountScreen(this.out, this.in, this.chosenStore);
                        storeAmountScreen.handleMsg();
                        break;
                    }
                    case InventoryConstants.DISPLAY_STORAGE_ITEMS_INDEX: {
                        StorageAmountScreen storageAmountScreen = new StorageAmountScreen(this.out, this.in, this.chosenStore.getStorage());
                        storageAmountScreen.handleMsg();
                        break;
                    }
                    default: {
                        this.out.println(InventoryConstants.INVALID_INPUT);
                        break;
                    }
                }
                break;
            }
            case InventoryConstants.MISSING_ITEMS_RECORD_INDEX: {
                MissingItemsScreen missingItemsScreen = new MissingItemsScreen(this.out, this.in, this.chosenStore, this.chosenStore.getStorage());
                missingItemsScreen.handleMsg();
                break;
            }
            case InventoryConstants.DISCOUNTS_HISTORY_INDEX: {
//                DiscountsHistoryScreen discountsHistoryScreen = new DiscountsHistoryScreen(this.out, this.in, discountsHistory);
//                discountsHistoryScreen.handleMsg();
                this.out.println(InventoryConstants.NOT_IMPLEMENTED);
                break;
            }
            case InventoryConstants.REMOVE_ITEMS_INDEX: {
                RemoveItemScreen removeItemScreen = new RemoveItemScreen(this.out, this.in);
                userInput = removeItemScreen.handleMsg();
                switch (userInput) {
                    case InventoryConstants.REMOVE_ITEM_STORE_INDEX: {
                        RemoveItemsStoreScreen removeItemsStoreScreen = new RemoveItemsStoreScreen(this.out, this.in, this.chosenStore);
                        removeItemsStoreScreen.handleMsg();
                        break;
                    }
                    case InventoryConstants.REMOVE_ITEM_STORAGE_INDEX: {
                        RemoveItemsStorageScreen removeItemsStorageScreen = new RemoveItemsStorageScreen(this.out, this.in, this.chosenStore.getStorage());
                        removeItemsStorageScreen.handleMsg();
                        break;
                    }
                    default: {
                        this.out.println(InventoryConstants.INVALID_INPUT);
                        break;
                    }
                }
                break;
            }
            case InventoryConstants.DISPLAY_DEFECTIVE_ITEMS_INDEX: {
                DefectiveItemsScreen defectiveItemsScreen = new DefectiveItemsScreen(this.out, this.in, this.chosenStore.getStorage());
                defectiveItemsScreen.handleMsg();
                break;
            }
            case InventoryConstants.TRANSFER_ITEMS_INDEX: {
                TransferItemsScreen transferItemsScreen = new TransferItemsScreen(this.out, this.in, this.chosenStore, this.chosenStore.getStorage());
                transferItemsScreen.handleMsg();
                break;
            }
            case InventoryConstants.ITEM_PRICE_HISTORY_INDEX: {
                ItemPriceHistoryScreen itemPriceHistoryScreen = new ItemPriceHistoryScreen(this.out, this.in, this.chosenStore, this.chosenStore.getStorage());
                itemPriceHistoryScreen.handleMsg();
                break;
            }
            case InventoryConstants.CREATE_NEW_DISCOUNT_INDEX: {
                System.out.println(InventoryConstants.NOT_IMPLEMENTED);
                AddDiscountScreen addDiscountScreen = new AddDiscountScreen(this.out, this.in);
                userInput = addDiscountScreen.handleMsg();
                switch (userInput) {
                    case InventoryConstants.DISCOUNT_TYPE_CATEGORY_INDEX: {
                        AddCategoryDiscountScreen addCategoryDiscountScreen = new AddCategoryDiscountScreen(this.out, this.in);
                        addCategoryDiscountScreen.handleMsg();
                        break;
                    }
//                    case InventoryConstants.DISCOUNT_TYPE_ITEM_INDEX: {
//                        AddItemDiscountScreen addItemDiscountScreen = new AddItemDiscountScreen(this.out, this.in, discountsHistory);
//                        addItemDiscountScreen.handleMsg();
//                        break;
//                    }
                    default: {
                        this.out.println(InventoryConstants.INVALID_INPUT);
                        break;
                    }
                }
                break;
            }
            case InventoryConstants.CREATE_NEW_PRODUCT_INDEX: {
                AddProductScreen addProductScreen = new AddProductScreen(this.out, this.in, this.chosenStore);
                addProductScreen.handleMsg();
                break;
            }
            case InventoryConstants.RETURN_TO_MAIN_MENU_INVENTORY_INDEX: {
                return;
            }
            default: {
                this.out.println(InventoryConstants.INVALID_INPUT);
                break;
            }
        }
    }

    private void displaySuppliers() {
        DefaultMenuScreenSupp defaultMenuScreenSupp = new DefaultMenuScreenSupp(this.out, this.in);
        int userInput = defaultMenuScreenSupp.handleMsg();
        switch (userInput) {
            case SuppliersConstants.DISPLAY_SUPPLIERS_INDEX: {
                SuppliersScreen suppliersScreen = new SuppliersScreen(this.out, this.in);
                userInput = suppliersScreen.handleMsg();

                // Validate input
                if (userInput >= Controller.controllerInstance().getSuppliers().size() || userInput < 0) {
                    this.out.println(SuppliersConstants.INVALID_INPUT);
                    break;
                }
                Supplier supplier = Controller.controllerInstance().getSuppliers().get(userInput);

                SupplierCardScreen supplierCardScreen = new SupplierCardScreen(this.out, this.in, supplier);
                userInput = supplierCardScreen.handleMsg();

                int size = supplier.contracts().size();
                if (userInput >= size + 2 || userInput < 0) {
                    this.out.println(SuppliersConstants.INVALID_INPUT);
                } else if (userInput == size) {
                    supplier.addContract(Contract.getContractFromIO(io, supplier));
                } else if (userInput == size + 1) {
                    break;
                }
                ContractScreen contractScreen = new ContractScreen(this.out, this.in, supplier, userInput);
                contractScreen.handleMsg();
                break;
            }

            case SuppliersConstants.ADD_SUPPLIER_INDEX: {
                Controller.controllerInstance().addSupplier(io);
                break;
            }

            case SuppliersConstants.RETURN_TO_MAIN_MENU_SUPPLIERS_INDEX: {
                return;
            }
            default: {
                this.out.println(SuppliersConstants.INVALID_INPUT);
                break;
            }
        }

    }
}
