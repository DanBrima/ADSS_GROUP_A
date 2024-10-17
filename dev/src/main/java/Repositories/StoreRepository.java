package Repositories;

import Domain.Category;
import Domain.ProductInStore;
import Domain.Store;
import db.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

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

    public static List<Store> getAllStores() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        List<Store> stores = new ArrayList<>();

        try {
            transaction = session.beginTransaction();
            stores = session.createQuery("from Store", Store.class).list();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return stores;
    }

}
