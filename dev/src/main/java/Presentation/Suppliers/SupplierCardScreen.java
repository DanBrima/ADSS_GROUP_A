package Presentation.Suppliers;

import Domain.*;
import External.SuppliersConstants;
import Presentation.Screen;

import java.io.PrintStream;
import java.util.Scanner;

public class SupplierCardScreen extends Screen {
    private Supplier supplier;

    public SupplierCardScreen(PrintStream out, Scanner in, Supplier supplier) {
        super(out, in);
        this.supplier = supplier;
    }

    @Override
    public int handleMsg() {

        String LEFT_ALIGN_FORMAT1 = "| %-20s | %-26s |%n";

        this.out.println();
        this.out.format("+---------------------------------------------------+%n");
        this.out.format(LEFT_ALIGN_FORMAT1, "Active account", supplier.activeAccount());
        this.out.format(LEFT_ALIGN_FORMAT1, "Needs pickup", supplier.needsPickup());
        this.out.format(LEFT_ALIGN_FORMAT1, "Bank account", supplier.bankAccount());
        this.out.format(LEFT_ALIGN_FORMAT1, "Payment option", supplier.paymentOption());
        this.out.format(LEFT_ALIGN_FORMAT1, "Contacts", supplier.contacts());
        this.out.format("+---------------------------------------------------+%n");
        if (supplier instanceof InPlaceSupplier) {
            this.out.format(LEFT_ALIGN_FORMAT1, "In Place Order ", ((InPlaceSupplier) supplier).getDeliveryDays() + " days");
        } else if (supplier instanceof FixedDaysSupplier) {
            this.out.format(LEFT_ALIGN_FORMAT1, "Fixed days delivery: ", ((FixedDaysSupplier) supplier).getArrivalDays());
        }
        this.out.format("+---------------------------------------------------+%n");

        this.out.println(SuppliersConstants.DISPLAY_CONTRACTS);

        this.out.print(SuppliersConstants.ADD_CONTRACT_INDEX + ". ");
        this.out.println(SuppliersConstants.ADD_CONTRACT);

        this.out.print(SuppliersConstants.RETURN_INDEX + ". ");
        this.out.println(SuppliersConstants.RETURN);

        String LEFT_ALIGN_FORMAT2 = "%s. display supplier contract number %<s %n";
        int conIndex = 0;
        for (conIndex = 0; conIndex < this.supplier.contracts().size(); conIndex++) {
            Contract contract = this.supplier.contracts().get(conIndex);
            this.out.format(LEFT_ALIGN_FORMAT2, conIndex);
        }

        this.out.print("\n" + SuppliersConstants.USER_INPUT);
        return Integer.parseInt(this.in.nextLine());
    }
}
