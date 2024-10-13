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

        this.out.println("1. Suppliers");
        this.out.println("2. Inventory");
        this.out.println("3. Exit");

        this.out.print("\n" + "Please choose an option: ");
        int userInput = this.in.nextInt();
        return userInput;
    }
}
