package Presentation.Stores;

import External.InventoryConstants;
import External.StoreConstants;
import External.SuppliersConstants;
import Presentation.Screen;

import java.io.PrintStream;
import java.util.Scanner;

public class StoreScreen extends Screen {

    public StoreScreen(PrintStream out, Scanner in) {
        super(out, in);
    }

    @Override
    public int handleMsg() {
        this.out.print(StoreConstants.DISPLAY_INVENTORY_INDEX + ". ");
        this.out.println(StoreConstants.DISPLAY_INVENTORY);

        this.out.print(StoreConstants.DISPLAY_ORDERS_INDEX + ". ");
        this.out.println(StoreConstants.DISPLAY_ORDERS);

        this.out.print(StoreConstants.RETURN_TO_MAIN_MENU_INDEX + ". ");
        this.out.println(StoreConstants.RETURN_TO_MAIN_MENU);
        
        this.out.print("\n" + SuppliersConstants.USER_INPUT);
        return Integer.parseInt(this.in.nextLine());
    }
}
