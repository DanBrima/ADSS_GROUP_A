import Domain.*;
import External.CashierDesk;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static Controller setUpController() {

        Controller controller = new Controller();

        Contact ban = new Contact("Ban", "0586979676");
        Contact dan = new Contact("Dan the orange", "0585979676");

        Supplier biko = new InPlaceSupplier(true, "biko", 123456,
                PaymentOption.CASH, List.of(ban), 5);
        Supplier shufersal = new FixedDaysSupplier(false, "shufersal", 123456,
                PaymentOption.CREDIT_CARD, List.of(dan), List.of(WeekDay.MONDAY, WeekDay.TUESDAY));

        Manufacturer osem = new Manufacturer("Osem");
        Product bisli = new Product("Bisli", osem);
        Product bamba = new Product("Bamba",  osem);
        Product waffle = new Product("Waffle",  osem);

        Contract bikoOsem = new Contract(10, biko,
                List.of(new ProductInContract(bisli, 15, 25),
                        new ProductInContract(bamba, 15, 20),
                        new ProductInContract(waffle, 35, 30)));
        Contract shufersalOsem = new Contract(10, biko,
                List.of(new ProductInContract(bisli, 5,25),
                        new ProductInContract(bamba, 10, 20),
                        new ProductInContract(waffle, 30, 30)));

        biko.addContract(bikoOsem);
        shufersal.addContract(shufersalOsem);

        controller.addSupplier(biko);
        controller.addSupplier(shufersal);

        return controller;
    }
    public static void main(String[] args) throws Exception {
        CashierDesk cashierDesk = new CashierDesk(System.out, new Scanner(System.in), setUpController());
        cashierDesk.turnOn();
    }
}
