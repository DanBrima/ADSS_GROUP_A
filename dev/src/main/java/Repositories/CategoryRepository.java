package Repositories;

import Domain.Category;
import Domain.Discount;
import Domain.ProductInStore;
import db.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    public void addDiscount(Category category, Discount discount) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            category.getDiscounts().add(discount);
            session.update(category);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void setParent(Category category, Category parent) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            category.setParent(parent);
            session.update(category);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public Category get(String name) {
        return HibernateUtil.getSession().get(Category.class, name) ;
    }

    public List<Category> getAll() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        List<Category> categories = new ArrayList<>();

        try {
            transaction = session.beginTransaction();
            categories = session.createQuery("from Category", Category.class).list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return categories;
    }
}
