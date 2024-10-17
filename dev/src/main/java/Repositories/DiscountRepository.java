package Repositories;

import Domain.Discount;
import db.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DiscountRepository {
    public void add(Discount discount) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(discount);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public Discount get(String name) {
        // Get discount from database
        return HibernateUtil.getSession().get(Discount.class, name) ;
    }
}
