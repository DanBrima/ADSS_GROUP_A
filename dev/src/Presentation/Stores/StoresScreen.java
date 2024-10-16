package Presentation.Stores;

import External.StoreConstants;
import External.SuppliersConstants;
import java.io.PrintStream;
import java.util.Scanner;
import Domain.Controller;
import Presentation.Screen;

public class StoresScreen extends Screen {
    private Controller controllerRef;

    public StoresScreen(PrintStream out, Scanner in, Controller controllerRef) {
        super(out, in);
        this.controllerRef = controllerRef;
    }

    @Override
    public int handleMsg() {
        if (this.controllerRef.getStores().size() == 0) {
            this.out.println("No store found in the system");
            return SuppliersConstants.USER_NO_INPUT;
        }

        this.out.println(StoreConstants.DISPLAY_STORE);

        String LEFT_ALIGN_FORMAT = "| %-6s | %-11s |%n";

        this.out.format("+--------+-------------+%n");
        this.out.format("| Number |    Store    |%n");
        this.out.format("+--------+-------------+%n");
        for (int storeIndex = 0; storeIndex < this.controllerRef.getStores().size(); storeIndex++) {
            Domain.Store store = this.controllerRef.getStores().get(storeIndex);
            this.out.format(LEFT_ALIGN_FORMAT, storeIndex, store.getName());
        }
        this.out.format("+--------+-------------+%n");

        this.out.print("\n" + SuppliersConstants.USER_INPUT);
        return Integer.parseInt(this.in.nextLine());
    }
}
