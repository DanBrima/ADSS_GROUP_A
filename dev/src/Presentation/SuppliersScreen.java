package Presentation;

import External.Constants;

import java.io.PrintStream;
import java.util.Scanner;
import Domain.Controller;


public class SuppliersScreen extends Screen {
    private Controller controllerRef;

    public SuppliersScreen(PrintStream out, Scanner in, Controller controllerRef) {
        super(out, in);
        this.controllerRef = controllerRef;
    }

    @Override
    public int handleMsg() {

        this.out.println(Constants.SUPPLIER_CARD);

        String LEFT_ALIGN_FORMAT = "| %-6s | %-11s |%n";

        this.out.format("+--------+-------------+%n");
        this.out.format("| Number |   Supplier  |%n");
        this.out.format("+--------+-------------+%n");
        for (int supIndex = 0; supIndex < this.controllerRef.getSuppliers().size(); supIndex++) {
            Domain.Supplier supplier = this.controllerRef.getSuppliers().get(supIndex);
            this.out.format(LEFT_ALIGN_FORMAT, supIndex, supplier.activeAccount());
        }
        this.out.format("+--------+-------------+%n");

        this.out.print("\n" + Constants.USER_INPUT);
        int userInput = this.in.nextInt();
        return userInput;
    }
}
