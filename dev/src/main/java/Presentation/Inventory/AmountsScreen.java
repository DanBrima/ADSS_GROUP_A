package Presentation.Inventory;

import External.InventoryConstants;
import Presentation.Screen;

import java.io.PrintStream;
import java.util.Scanner;

public class AmountsScreen extends Screen {
    public AmountsScreen(PrintStream out, Scanner in) {
        super(out, in);
    }

    @Override
    public int handleMsg() {
        this.out.print(InventoryConstants.DISPLAY_STORE_ITEMS_INDEX + ". ");
        this.out.println(InventoryConstants.DISPLAY_STORE_ITEMS);

        this.out.print(InventoryConstants.DISPLAY_STORAGE_ITEMS_INDEX + ". ");
        this.out.println(InventoryConstants.DISPLAY_STORAGE_ITEMS);

        this.out.print("\n" + InventoryConstants.USER_INPUT);
        int userInput = Integer.parseInt(this.in.nextLine());
        return userInput;
    }
}
