package Tests;

import Domain.*;

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
        milk = new Product("Milk", 10, tnuva);
        shoko = new Product("Shoko", 10, tnuva);
        cheese = new Product("Cheese", 10, tnuva);
        osem = new Manufacturer("Osem");
        bisli = new Product("Bisli", 10, osem);
        bamba = new Product("Bamba", 10, osem);
        waffle = new Product("Waffle", 10, osem);
        biko = new InPlaceSupplier(true, "biko", 123456,
                PaymentOption.CASH, List.of(dan, ban), 5);
        shufersal = new FixedDaysSupplier(false, "shufersal", 123456,
                PaymentOption.CREDIT_CARD, List.of(ban), List.of(WeekDay.MONDAY, WeekDay.TUESDAY));
    }

    // Dan create this as private and not public ? Why ?
    // We can also do the test functions boolean instead of void
    public void test_SameProductMultipleSuppliers() {
        initializeObjects();
        bikoDiary = new Contract(100, biko,
                List.of(new ProductInContract(milk, 5, 25),
                        new ProductInContract(shoko, 10, 20)));
        bikoOsem = new Contract(100, biko,
                List.of(new ProductInContract(bisli, 5, 25),
                        new ProductInContract(bamba, 10, 20),
                        new ProductInContract(waffle, 30, 30)));
        shufersalDiary = new Contract(15, shufersal,
                List.of(new ProductInContract(milk, 10, 10),
                        new ProductInContract(shoko, 15, 10)));
        biko.addContract(bikoDiary);
        shufersal.addContract(shufersalDiary);
    }

    public void test_SameSupplierMultipleContracts() {
        initializeObjects();
        bikoDiary = new Contract(100, biko,
                List.of(new ProductInContract(milk, 5, 25),
                        new ProductInContract(shoko, 10, 20)));
        bikoOsem = new Contract(100, biko,
                List.of(new ProductInContract(bisli, 5, 25),
                        new ProductInContract(bamba, 10, 20),
                        new ProductInContract(waffle, 30, 30)));
        biko.addContract(bikoDiary);
        biko.addContract(bikoOsem);

        assert biko.contracts().size() == 2;
    }

    public void test_ContractWithDiscount() {
        initializeObjects();
        bikoOsem = new Contract(10, biko,
                List.of(new ProductInContract(bisli, 5, 25),
                        new ProductInContract(bamba, 10, 20),
                        new ProductInContract(waffle, 30, 30)));
        assert biko.contracts().get(0).isDiscount();
    }

    public void test_EmptyContract() {
        initializeObjects();
        List list = List.of();
        int size = biko.getAllProductsInContracts().size();
        bikoOsem = new Contract(10, biko, List.of());
        biko.addContract(bikoOsem);
        assert biko.getAllProductsInContracts().size() == size;
    }

    public void test_SameSupplierProduct() {
        initializeObjects();
        Contract bikoOsem1 = new Contract(100, biko,
                List.of(new ProductInContract(bisli, 5, 25),
                        new ProductInContract(bamba, 10, 20),
                        new ProductInContract(waffle, 30, 30)));
        Contract bikoOsem2 = new Contract(100, biko,
                List.of(new ProductInContract(bisli, 5, 25),
                        new ProductInContract(bamba, 10, 20),
                        new ProductInContract(waffle, 30, 30)));
        biko.addContract(bikoOsem1);
        biko.addContract(bikoOsem2);
        // Needs to be 6 items (even if the products are the same)
        assert biko.getAllProductsInContracts().size() == 6;
    }

    public void test_SameNameSuppliers() {
        initializeObjects();
        Supplier shufersal2 = new FixedDaysSupplier(false, "shufersal", 123456,
                PaymentOption.CREDIT_CARD, List.of(ban), List.of(WeekDay.MONDAY, WeekDay.TUESDAY));
        assert shufersal2.activeAccount() == shufersal.activeAccount();
    }

    // Always with discount
    public void test_ZeroWholesaleThreshold() {
        initializeObjects();
        bikoOsem = new Contract(0, biko,
                List.of(new ProductInContract(bisli, 5, 25),
                        new ProductInContract(bamba, 10, 20),
                        new ProductInContract(waffle, 30, 30)));
        biko.addContract(bikoOsem);
        assert biko.contracts().get(0).isDiscount();
    }

    public void test_NoDeliveryDays() {
        initializeObjects();
        shufersal = new FixedDaysSupplier(false, "shufersal", 123456,
                PaymentOption.CREDIT_CARD, List.of(ban), List.of());
        assert ((FixedDaysSupplier) shufersal).getArrivalDays().size() == 0;
    }

    public void test_immediateDeliveryInPlace() {
        initializeObjects();
        shufersal = new InPlaceSupplier(true, "shufersal", 123456,
                PaymentOption.CREDIT_CARD, List.of(ban), 0);
        assert ((InPlaceSupplier) shufersal).getDeliveryDays() == 0;
    }

    public void test_AddSupplierToStore() {
        initializeObjects();
        Store store = new Store();
        store.addSupplier(shufersal);
        store.addSupplier(biko);
        assert store.getSuppliers().size() == 2;
    }
}
