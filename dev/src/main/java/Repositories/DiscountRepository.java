package Repositories;

import Domain.Discount;
import db.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DiscountRepository {
    public void add(Discount discount) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(discount);

        session.getTransaction().commit();
        session.close();
    }

    public Discount get(String name) {
        // Get discount from database
        return HibernateUtil.getSession().get(Discount.class, name) ;
    }

    public List<Discount> getAll() {
        // Get all discounts from database
        return null;
    }
}
