import Domain.Storage;
import Domain.Store;
import External.CashierDesk;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Store store = new Store();
        Storage storage = new Storage();

        CashierDesk cashierDesk = new CashierDesk(System.out, new Scanner(System.in), storage, store);
        cashierDesk.turnOn();
    }
}