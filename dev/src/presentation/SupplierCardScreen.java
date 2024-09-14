package Presentation;

import Domain.*;
import External.Constants;

import java.io.PrintStream;
import java.util.Scanner;

public class SupplierCardScreen extends Screen {
    private Supplier supplier;

    public SupplierCardScreen(PrintStream out, Scanner in, Store storeRef, int supplier) {
        super(out, in);
        this.supplier = storeRef.getSuppliers().get(supplier);
    }

    public SupplierCardScreen(PrintStream out, Scanner in, Supplier supplier) {
        super(out, in);
        this.supplier = supplier;
    }

    @Override
    public int handleMsg() {
        // TODO print supplier according to given ID

        String LEFT_ALIGN_FORMAT1 = "| %-16s | %-16s |%n";

        this.out.println();
        this.out.format("+-------------------------------------+%n");
        this.out.format(LEFT_ALIGN_FORMAT1, "Active account",supplier.activeAccount());
        this.out.format(LEFT_ALIGN_FORMAT1, "Needs pickup",supplier.needsPickup());
        this.out.format(LEFT_ALIGN_FORMAT1, "Bank account",supplier.bankAccount());
        this.out.format(LEFT_ALIGN_FORMAT1, "Payment option",supplier.paymentOption());
        this.out.format(LEFT_ALIGN_FORMAT1, "Contacts",supplier.contacts());
        this.out.format("+-------------------------------------+%n");
        if (supplier instanceof InPlaceSupplier){
            this.out.format(LEFT_ALIGN_FORMAT1, "In Place Order ", ((InPlaceSupplier) supplier).getDeliveryDays() + " days");
        }
        else if (supplier instanceof FixedDaysSupplier){
            this.out.format(LEFT_ALIGN_FORMAT1, "Fixed days delivery: ", ((FixedDaysSupplier) supplier).getArrivalDays());
        }
        this.out.format("+-------------------------------------+%n");

        this.out.println(Constants.DISPLAY_CONTRACTS);

        String LEFT_ALIGN_FORMAT2 = "%s. display contract %<s %n";
        int conIndex = 0;
        for (conIndex = 0; conIndex < this.supplier.contracts().size(); conIndex++) {
            Contract contract = this.supplier.contracts().get(conIndex);
            // TODO replace contract "products" contract name...
            this.out.format(LEFT_ALIGN_FORMAT2, conIndex);
        }

//        Day 1 requirements do not include data manipulations
        this.out.print(conIndex + ". ");
        this.out.println(Constants.ADD_CONTRACT);

        this.out.format((conIndex+1) + ". return");

        this.out.print("\n" + Constants.USER_INPUT);
        int userInput = this.in.nextInt();
        return userInput;
    }
}
