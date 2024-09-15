package Presentation;

import External.Constants;

import java.io.PrintStream;
import java.util.Scanner;

public class RemoveItemScreen extends Screen {
    public RemoveItemScreen(PrintStream out, Scanner in) {
        super(out, in);
    }

    @Override
    public int handleMsg() {
        this.out.print(Constants.REMOVE_ITEM_STORE_INDEX + ". ");
        this.out.println(Constants.REMOVE_ITEM_STORE);

        this.out.print(Constants.REMOVE_ITEM_STORAGE_INDEX + ". ");
        this.out.println(Constants.REMOVE_ITEM_STORAGE);

        this.out.print("\n" + Constants.USER_INPUT);
        int userInput = Integer.parseInt(this.in.nextLine());
        return userInput;
    }
}
