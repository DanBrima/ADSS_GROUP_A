/*
WHAT I NEED TO DO IN THIS PAGE IS INCLUDE OUR "SCREENS" INCLUDING SUPPLIERS AND CONTRACTS.
*/

package External;

import Domain.Store;
import Domain.Supplier;
import Presentation.*;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Scanner;

public class CashierDesk {
    private final PrintStream out;
    private final Scanner in;

    private Boolean isActivated = false;
    private Store storeRef;

    public CashierDesk(PrintStream out, Scanner in, Store storeRef) {
        this.out = out;
        this.in = in;
        this.storeRef = storeRef;
    }

    public void turnOn() throws Exception {
        this.isActivated = true;

        while (this.isActivated) {
            // Activate Menu
            DefaultMenuScreen defaultMenuScreen = new DefaultMenuScreen(this.out, this.in);
            int userInput = defaultMenuScreen.handleMsg();
            switch (userInput) {
                case Constants.DISPLAY_SUPPLIERS_INDEX: {
                    SuppliersScreen suppliersScreen = new SuppliersScreen(this.out, this.in, storeRef);
                    userInput = suppliersScreen.handleMsg();

                    // Validate input
                    if(userInput >= storeRef.getSuppliers().size() || userInput < 0){
                        this.out.println(Constants.INVALID_INPUT);
                        break;
                    }
                    Supplier supplier = storeRef.getSuppliers().get(userInput);

                    // TODO display the supplier card with the related supplier ? then switch for contract add
                    SupplierCardScreen supplierCardScreen = new SupplierCardScreen(this.out, this.in, supplier);
                    userInput = supplierCardScreen.handleMsg();

                    // Validate input
                    if(userInput >= supplier.contracts().size() || userInput < 0){
                        this.out.println(Constants.INVALID_INPUT);
                        break;
                    }
                    ContractScreen contractScreen = new ContractScreen(this.out, this.in, supplier, userInput);
                    contractScreen.handleMsg();
                    break;
                }
//  Day 1 requirement do not include data manipulations
//                case Constants.ADD_SUPPLIER_INDEX: {
//                    break;
//                }
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
