/*
WHAT I NEED TO DO IN THIS PAGE IS INCLUDE OUR "SCREENS" INCLUDING SUPPLIERS AND CONTRACTS.
*/

package External;

import Domain.Contract;
import Domain.Controller;
import Domain.Store;
import Domain.Supplier;
import Presentation.*;

import java.io.PrintStream;
import java.util.Scanner;

public class CashierDesk {
    private final PrintStream out;
    private final Scanner in;
    private final IO io;

    private Boolean isActivated = false;
    private Controller controllerRef;

    public CashierDesk(PrintStream out, Scanner in, Controller controllerRef) {
        this.out = out;
        this.in = in;
        this.controllerRef = controllerRef;
        this.io = new ConsoleIO();
    }

    public void turnOn() throws Exception {
        this.isActivated = true;

        while (this.isActivated) {
            // Activate Menu
            DefaultMenuScreen defaultMenuScreen = new DefaultMenuScreen(this.out, this.in);
            int userInput = defaultMenuScreen.handleMsg();
            switch (userInput) {
                case Constants.DISPLAY_SUPPLIERS_INDEX: {
                    SuppliersScreen suppliersScreen = new SuppliersScreen(this.out, this.in, controllerRef);
                    userInput = suppliersScreen.handleMsg();

                    // Validate input
                    if (userInput >= controllerRef.getSuppliers().size() || userInput < 0) {
                        this.out.println(Constants.INVALID_INPUT);
                        break;
                    }
                    Supplier supplier = controllerRef.getSuppliers().get(userInput);

                    SupplierCardScreen supplierCardScreen = new SupplierCardScreen(this.out, this.in, supplier);
                    userInput = supplierCardScreen.handleMsg();

                    int size = supplier.contracts().size();
                    if (userInput >= size + 2 || userInput < 0) {
                        this.out.println(Constants.INVALID_INPUT);
                    } else if (userInput == size) {
                        supplier.addContract(Contract.getContractFromIO(io, supplier));
                    } else if (userInput == size + 1) {
                        break;
                    }
                    ContractScreen contractScreen = new ContractScreen(this.out, this.in, supplier, userInput);
                    contractScreen.handleMsg();
                    break;
                }

                case Constants.ADD_SUPPLIER_INDEX: {
                    controllerRef.addSupplier(io);
                    break;
                }

                case Constants.DISPLAY_STORES_INDEX: {
                    StoresScreen storesScreen = new StoresScreen(this.out, this.in, controllerRef);
                    userInput = storesScreen.handleMsg();
                    if (userInput >= controllerRef.getStores().size() || userInput < 0) {
                        this.out.println(Constants.INVALID_INPUT);
                        break;
                    }
                    Store store = controllerRef.getStores().get(userInput);

                    OrdersScreen ordersScreen = new OrdersScreen(this.out, this.in, store);
                    userInput = ordersScreen.handleMsg();
                    if (userInput >= Constants.ADD_ORDER_INDEX+2 || userInput < 0) {
                        this.out.println(Constants.INVALID_INPUT);
                    } else if (userInput == Constants.ADD_ORDER_INDEX){
                        // Choose supplier for order
//                        SuppliersScreen suppliersScreen = new SuppliersScreen(this.out, this.in, controllerRef);
//                        userInput = suppliersScreen.handleMsg();
//                        if (userInput >= controllerRef.getSuppliers().size() || userInput < 0) {
//                            this.out.println(Constants.INVALID_INPUT);
//                            break;
//                        }
                        store.addOrder(io,controllerRef.getSuppliers());
                        //.get(userInput)
                    } else if (userInput == Constants.ADD_ORDER_INDEX + 1){
                        userInput = Constants.DISPLAY_STORES_INDEX;
                    }
                    break;
                }

                case Constants.ADD_STORE_INDEX: {
                    controllerRef.addStore(io);
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
