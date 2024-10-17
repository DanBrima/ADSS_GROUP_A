package Repositories;

import Domain.Category;
import Domain.Discount;
import db.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CategoryRepository {
    public void add(Category category) {

        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(category);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void setParent(Category category) {
    }

    public void addDiscount(Category category, Discount discount) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        category.getDiscounts().add(discount);
        session.saveOrUpdate(category);
        session.getTransaction().commit();
        session.close();
    }

    public Category get(String name) {
        // Get category from database
        return HibernateUtil.getSession().get(Category.class, name) ;
    }

    public List<Category> getAll() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        List<Category> categories = session.createQuery("from Category", Category.class).list();
        session.getTransaction().commit();
        session.close();

        return categories;
    }
}
