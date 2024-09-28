package Presentation;
import External.Constants;
import java.io.PrintStream;
import java.util.Scanner;

public class DefaultMenuScreen extends Screen {

    public DefaultMenuScreen(PrintStream out, Scanner in) {
        super(out, in);
    }

    @Override
    public int handleMsg() {
        this.out.println(Constants.DEFAULT_MSG);

        this.out.print(Constants.DISPLAY_SUPPLIERS_INDEX + ". ");
        this.out.println(Constants.DISPLAY_SUPPLIERS);

        this.out.print(Constants.ADD_SUPPLIER_INDEX + ". ");
        this.out.println(Constants.ADD_SUPPLIER);

        this.out.print(Constants.DISPLAY_STORES_INDEX + ". ");
        this.out.println(Constants.DISPLAY_STORES);

        this.out.print(Constants.ADD_STORE_INDEX + ". ");
        this.out.println(Constants.ADD_STORE);

        this.out.print(Constants.TURN_OFF_INDEX + ". ");
        this.out.println(Constants.TURN_OFF);

        this.out.print("\n" + Constants.USER_INPUT);
        int userInput = this.in.nextInt();
        return userInput;
    }
}
