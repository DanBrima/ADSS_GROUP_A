
import Domain.*;
import External.CashierDesk;
import Repositories.CategoryRepository;
import Repositories.ContactRepository;
import Repositories.DiscountRepository;
import Repositories.ProductInStoreRepository;
import db.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Main {

    public static Controller setUpController() {
        Controller controller = new Controller();

        Contact ban = new Contact("Ban", "0586979676");
        Contact dan = new Contact("Dan the orange", "0585979676");

        InPlaceSupplier biko = new InPlaceSupplier(true, "biko", 123456,
                PaymentOption.CASH, List.of(ban), 5);
        FixedDaysSupplier shufersal = new FixedDaysSupplier(false, "shufersal", 123456,
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
        // Get a session
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Begin transaction
            Transaction transaction = session.beginTransaction();

            try {
                ContactRepository a = new ContactRepository();
//                a.add(new Contact("Ban", "0586979676"));
                Contact retrievedUser = a.get("Ban");
                System.out.println("Retrieved user: " + retrievedUser);

                System.out.println(UUID.randomUUID());

                Controller.setControllerInstance(setUpController());
                CashierDesk cashierDesk = new CashierDesk(System.out, new Scanner(System.in));
                cashierDesk.turnOn();

            } catch (Exception e) {
                // If there's an exception, rollback the transaction
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the session factory
            HibernateUtil.shutdown();
        }
    }
}
