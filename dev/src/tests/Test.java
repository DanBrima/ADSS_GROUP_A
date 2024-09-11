package tests;

import domain.*;

import java.util.List;

public class Test {
    private static Contact dan;
    private static Contact ban;
    private static Manufacturer tnuva;
    private static Manufacturer osem;
    private static Product milk;
    private static Product shoko;
    private static Product cheese;
    private static Product bisli;
    private static Product bamba;
    private static Product waffle;
    private static Supplier biko;
    private static Supplier shufersal;
    private static Contract bikoDiary;
    private static Contract bikoOsem;
    private static Contract shufersalDiary;

    private static void initializeObjects() {
        dan = new Contact("Dan", "0585979676");
        ban = new Contact("Ban", "0586979676");
        tnuva = new Manufacturer("Tnuva");
        milk = new Product("Milk", tnuva);
        shoko = new Product("Shoko", tnuva);
        cheese = new Product("Cheese", tnuva);
        osem = new Manufacturer("Osem");
        bisli = new Product("Bisli", osem);
        bamba = new Product("Bamba", osem);
        waffle = new Product("Waffle", osem);
        biko = new InPlaceSupplier(true, "biko", 123456,
                PaymentOption.CASH, List.of(dan, ban), 5);
        shufersal = new FixedDaysSupplier(false, "shufersal", 123456,
                PaymentOption.CREDIT_CARD, List.of(ban), List.of(WeekDay.MONDAY, WeekDay.TUESDAY));
        bikoDiary = new Contract(10, biko,
                List.of(new ProductInContract(milk, 5, 25),
                        new ProductInContract(shoko, 10, 20),
                        new ProductInContract(cheese, 30, 30)));
        bikoOsem = new Contract(10, biko,
                List.of(new ProductInContract(bisli, 5, 25),
                        new ProductInContract(bamba, 10, 20),
                        new ProductInContract(waffle, 30, 30)));
        shufersalDiary = new Contract(15, shufersal,
                List.of(new ProductInContract(milk, 10, 10),
                        new ProductInContract(cheese, 40, 10)));
        biko.addContract(bikoDiary);
        biko.addContract(bikoOsem);
        shufersal.addContract(shufersalDiary);
    }

    private static void test_SameProductMultipleSuppliers() {
        initializeObjects();
    }
}
