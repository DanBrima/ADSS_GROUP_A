import External.CashierDesk;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CashierDesk cashierDesk = new CashierDesk(System.out, new Scanner(System.in));
        cashierDesk.turnOn();
    }
}