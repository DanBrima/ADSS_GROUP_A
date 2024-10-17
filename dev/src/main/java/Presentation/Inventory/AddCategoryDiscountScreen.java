package Presentation.Inventory;

import External.InventoryConstants;
import Presentation.Screen;
import Service.DiscountService;

import java.io.PrintStream;
import java.util.Scanner;

public class AddCategoryDiscountScreen extends Screen {
    public AddCategoryDiscountScreen(PrintStream out, Scanner in) {
        super(out, in);
    }

    @Override
    public int handleMsg() {
        this.out.print(InventoryConstants.CATEGORY + " ");
        String category = this.in.nextLine();
        this.out.print(InventoryConstants.PERCENTAGE + " ");
        int percentage = Integer.parseInt(this.in.nextLine());
        DiscountService.addCategoryDiscount(category, percentage);
        this.out.println(InventoryConstants.SUCCESS);
        return InventoryConstants.USER_NO_INPUT;
    }
}
