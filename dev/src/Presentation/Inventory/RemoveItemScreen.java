package Presentation.Inventory;

import External.InventoryConstants;
import Presentation.Screen;

import java.io.PrintStream;
import java.util.Scanner;

public class RemoveItemScreen extends Screen {
    public RemoveItemScreen(PrintStream out, Scanner in) {
        super(out, in);
    }

    @Override
    public int handleMsg() {
        this.out.print(InventoryConstants.REMOVE_ITEM_STORE_INDEX + ". ");
        this.out.println(InventoryConstants.REMOVE_ITEM_STORE);

        this.out.print(InventoryConstants.REMOVE_ITEM_STORAGE_INDEX + ". ");
        this.out.println(InventoryConstants.REMOVE_ITEM_STORAGE);

        this.out.print("\n" + InventoryConstants.USER_INPUT);
        int userInput = Integer.parseInt(this.in.nextLine());
        return userInput;
    }
}
