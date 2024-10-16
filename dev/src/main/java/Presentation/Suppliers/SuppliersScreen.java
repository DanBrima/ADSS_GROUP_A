package Presentation.Suppliers;

import External.SuppliersConstants;

import java.io.PrintStream;
import java.util.Scanner;
import Domain.Controller;
import Presentation.Screen;


public class SuppliersScreen extends Screen {
    public SuppliersScreen(PrintStream out, Scanner in) {
        super(out, in);
    }

    @Override
    public int handleMsg() {

        this.out.println(SuppliersConstants.SUPPLIER_CARD);

        String LEFT_ALIGN_FORMAT = "| %-6s | %-11s |%n";

        this.out.format("+--------+-------------+%n");
        this.out.format("| Number |   Supplier  |%n");
        this.out.format("+--------+-------------+%n");
        for (int supIndex = 0; supIndex < Controller.controllerInstance().getSuppliers().size(); supIndex++) {
            Domain.Supplier supplier = Controller.controllerInstance().getSuppliers().get(supIndex);
            this.out.format(LEFT_ALIGN_FORMAT, supIndex, supplier.activeAccount());
        }
        this.out.format("+--------+-------------+%n");

        this.out.print("\n" + SuppliersConstants.USER_INPUT);
        int userInput = Integer.parseInt(this.in.nextLine());
        return userInput;
    }
}
