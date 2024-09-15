import Domain.*;
import External.CashierDesk;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Store store = new Store();

        Contact ban = new Contact("Ban", "0586979676");
        Contact dan = new Contact("Dan", "0585979676");

        Supplier biko = new InPlaceSupplier(true, "biko", 123456,
                PaymentOption.CASH, List.of(ban), 5);
        Supplier shufersal = new FixedDaysSupplier(false, "shufersal", 123456,
                PaymentOption.CREDIT_CARD, List.of(dan), List.of(WeekDay.MONDAY, WeekDay.TUESDAY));

        Manufacturer osem = new Manufacturer("Osem");
        Product bisli = new Product("Bisli", 10, osem);
        Product bamba = new Product("Bamba", 15, osem);
        Product waffle = new Product("Waffle", 20, osem);

        Contract bikoOsem = new Contract(10, biko,
                List.of(new ProductInContract(bisli, 15, 25),
                        new ProductInContract(bamba, 15, 20),
                        new ProductInContract(waffle, 35, 30)));
        Contract shufersalOsem = new Contract(10, biko,
                List.of(new ProductInContract(bisli, 5, 25),
                        new ProductInContract(bamba, 10, 20),
                        new ProductInContract(waffle, 30, 30)));

        // If we add the supplier in the contract creation, we need to add the contract in the supplier during contract build.
        biko.addContract(bikoOsem);
        shufersal.addContract(shufersalOsem);

        store.addSupplier(biko);
        store.addSupplier(shufersal);

        CashierDesk cashierDesk = new CashierDesk(System.out, new Scanner(System.in), store);
        cashierDesk.turnOn();
    }
}
