package Presentation;

import Domain.ProductInOrder;
import Domain.Store;
import Domain.Order;
import External.Constants;

import java.io.PrintStream;
import java.util.Scanner;

public class OrdersScreen extends Screen {
    private Store storeRef;

    public OrdersScreen(PrintStream out, Scanner in, Store storeRef) {
        super(out, in);
        this.storeRef = storeRef;
    }

    @Override
    public int handleMsg() {

        this.out.println(Constants.STORE_ORDERS);
        if (this.storeRef.getOrders().size() == 0) {
            this.out.println("Store has no orders");
        }
        else {
            String LEFT_ALIGN_FORMAT = "| %-6s | %-8s | %-11s | %-6s %n";
            this.out.format("+--------+-----------+-------------+--------+%n");
            this.out.format("| Number |  Supplier |   Product   | Amount |%n");
            this.out.format("+--------+-----------+-------------+--------+%n");
            for (int orderIndex = 0; orderIndex < this.storeRef.getOrders().size(); orderIndex++) {
                Order order = storeRef.getOrders().get(orderIndex);
                for (ProductInOrder product : order.getProducts()) {
                    this.out.format(LEFT_ALIGN_FORMAT, orderIndex, product.supplier.activeAccount(), product.name(), product.amount);
                }
                this.out.format("+----------- order price is %s ------------+%n", order.getPrice());
            }
            this.out.format("+--------+-----------+-------------+--------+%n");
        }
        this.out.print(Constants.ADD_ORDER_INDEX + ". ");
        this.out.println(Constants.ADD_ORDER);
        this.out.format((Constants.ADD_ORDER_INDEX + 1) + ". return");
        this.out.print("\n" + Constants.USER_INPUT);
        int userInput = this.in.nextInt();
        return userInput;
    }
}
