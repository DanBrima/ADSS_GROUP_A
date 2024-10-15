package Presentation.Suppliers;
import External.SuppliersConstants;
import Presentation.Screen;

import java.io.PrintStream;
import java.util.Scanner;

public class DefaultMenuScreenSupp extends Screen {

    public DefaultMenuScreenSupp(PrintStream out, Scanner in) {
        super(out, in);
    }

    @Override
    public int handleMsg() {
        this.out.println(SuppliersConstants.DEFAULT_MSG);

        this.out.print(SuppliersConstants.DISPLAY_SUPPLIERS_INDEX + ". ");
        this.out.println(SuppliersConstants.DISPLAY_SUPPLIERS);

        this.out.print(SuppliersConstants.ADD_SUPPLIER_INDEX + ". ");
        this.out.println(SuppliersConstants.ADD_SUPPLIER);

        this.out.print(SuppliersConstants.RETURN_TO_MAIN_MENU_SUPPLIERS_INDEX + ". ");
        this.out.println(SuppliersConstants.RETURN_TO_MAIN_MENU_SUPPLIERS);

        this.out.print("\n" + SuppliersConstants.USER_INPUT);
        int userInput = Integer.parseInt(this.in.nextLine());
        return userInput;
    }
}
