package Presentation;

import java.io.PrintStream;
import java.util.Scanner;

public class MainMenuScreen extends Screen {

    public MainMenuScreen(PrintStream out, Scanner in) {
        super(out, in);
    }

    @Override
    public int handleMsg() {
        this.out.println("Main Menu");

        this.out.println("1. To create new store");
        this.out.println("2. Suppliers");
        this.out.println("3. Inventory");
        this.out.println("4. Exit");

        this.out.print("\n" + "Please choose an option: ");
        int userInput = Integer.parseInt(this.in.nextLine());
        return userInput;
    }
}
