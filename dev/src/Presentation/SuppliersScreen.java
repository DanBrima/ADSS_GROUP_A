package Presentation;

import External.Constants;

import java.io.PrintStream;
import java.util.Scanner;
import Domain.Store;


public class SuppliersScreen extends Screen {
    private Store storeRef;

    public SuppliersScreen(PrintStream out, Scanner in, Store storeRef) {
        super(out, in);
        this.storeRef = storeRef;
    }

    @Override
    public int handleMsg() {

        this.out.println(Constants.SUPPLIER_CARD);

        String LEFT_ALIGN_FORMAT = "| %-6s | %-11s |%n";

        this.out.format("+--------+-------------+%n");
        this.out.format("| Number |   Supplier  |%n");
        this.out.format("+--------+-------------+%n");
        for (int supIndex = 0; supIndex < this.storeRef.getSuppliers().size(); supIndex++) {
            Domain.Supplier supplier = this.storeRef.getSuppliers().get(supIndex);
            this.out.format(LEFT_ALIGN_FORMAT, supIndex, supplier.activeAccount());
        }
        this.out.format("+--------+-------------+%n");

        this.out.print("\n" + Constants.USER_INPUT);
        int userInput = this.in.nextInt();
        return userInput;
    }
}
