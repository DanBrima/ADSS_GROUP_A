package Presentation.Suppliers;

import External.SuppliersConstants;

import java.io.PrintStream;
import java.util.Scanner;
import Domain.Controller;
import Presentation.Screen;


public class SuppliersScreen extends Screen {
    private Controller controllerRef;

    public SuppliersScreen(PrintStream out, Scanner in, Controller controllerRef) {
        super(out, in);
        this.controllerRef = controllerRef;
    }

    @Override
    public int handleMsg() {

        this.out.println(SuppliersConstants.SUPPLIER_CARD);

        String LEFT_ALIGN_FORMAT = "| %-6s | %-11s |%n";

        this.out.format("+--------+-------------+%n");
        this.out.format("| Number |   Supplier  |%n");
        this.out.format("+--------+-------------+%n");
        for (int supIndex = 0; supIndex < this.controllerRef.getSuppliers().size(); supIndex++) {
            Domain.Supplier supplier = this.controllerRef.getSuppliers().get(supIndex);
            this.out.format(LEFT_ALIGN_FORMAT, supIndex, supplier.activeAccount());
        }
        this.out.format("+--------+-------------+%n");

        this.out.print("\n" + SuppliersConstants.USER_INPUT);
        return Integer.parseInt(this.in.nextLine());
    }
}
