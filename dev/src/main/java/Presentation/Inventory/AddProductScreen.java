package Presentation.Inventory;

import Domain.*;
import External.InventoryConstants;
import Presentation.Screen;

import java.io.PrintStream;
import java.util.Scanner;

public class AddProductScreen extends Screen {
    private Store storeRef;

    public AddProductScreen(PrintStream out, Scanner in, Store storeRef) {
        super(out, in);
        this.storeRef = storeRef;
    }

    @Override
    public int handleMsg() {
        this.out.println(InventoryConstants.CREATE_NEW_ITEM);
        this.out.print(InventoryConstants.PRODUCT_NAME + " ");
        String name = this.in.nextLine();
        //TODO: wait for DB connection so we can choose the exist manufacture
        Product product = new Product(name, new Manufacturer("CHANGE THIS PART!!"));

        this.out.print(InventoryConstants.PRODUCT_REQUIRED_AMOUNT + " ");
        int reqAmount = Integer.parseInt(this.in.nextLine());
        this.out.println(InventoryConstants.PRODUCT_PRICE + " ");
        int price = Integer.parseInt(this.in.nextLine());

        //TODO: wait for DB connection so we can choose the exist category
        ProductInStore productInStore = new ProductInStore(product, reqAmount, new Category("CHANGE THIS PART!!"), price);
        this.storeRef.addItem(productInStore);
        return InventoryConstants.USER_NO_INPUT;
    }
}
