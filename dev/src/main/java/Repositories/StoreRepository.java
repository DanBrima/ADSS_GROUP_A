package Repositories;

import Domain.ProductInStore;
import Domain.Store;
import db.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class StoreRepository {
    public static void add(Store store) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(store);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
