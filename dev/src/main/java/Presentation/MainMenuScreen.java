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

        this.out.println("1. Add Store");
        this.out.println("2. Display Stores");
        this.out.println("3. Add Supplier");
        this.out.println("4. Display Suppliers");
        this.out.println("5. Exit");

        this.out.print("\n" + "Please choose an option: ");
        return Integer.parseInt(this.in.nextLine());
    }
}
