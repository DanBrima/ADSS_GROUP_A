package Presentation;

        import External.Constants;
        import java.io.PrintStream;
        import java.util.Scanner;
        import Domain.Controller;

public class StoresScreen extends Screen {
    private Controller controllerRef;

    public StoresScreen(PrintStream out, Scanner in, Controller controllerRef) {
        super(out, in);
        this.controllerRef = controllerRef;
    }

    @Override
    public int handleMsg() {
        if (this.controllerRef.getStores().size() == 0) {
            this.out.println("No store found in the system");
            return Constants.USER_NO_INPUT;
        }

        this.out.println(Constants.DISPLAY_STORE_ORDERS);

        String LEFT_ALIGN_FORMAT = "| %-6s | %-11s |%n";

        this.out.format("+--------+-------------+%n");
        this.out.format("| Number |    Store    |%n");
        this.out.format("+--------+-------------+%n");
        for (int storeIndex = 0; storeIndex < this.controllerRef.getStores().size(); storeIndex++) {
            Domain.Store store = this.controllerRef.getStores().get(storeIndex);
            this.out.format(LEFT_ALIGN_FORMAT, storeIndex, store.getName());
        }
        this.out.format("+--------+-------------+%n");

        this.out.print("\n" + Constants.USER_INPUT);
        int userInput = this.in.nextInt();
        return userInput;
    }
}
