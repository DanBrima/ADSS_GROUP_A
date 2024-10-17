package Repositories;

import Domain.Discount;
import Domain.ProductInStore;
import db.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProductInStoreRepository {
    public void add(ProductInStore product) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(product);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void addDiscount(ProductInStore product, Discount discount) {
        Session session = HibernateUtil.getSession();
        try {
            ProductInStore productFromDB = getProduct(product.getBarcode());
            productFromDB.getDiscounts().add(discount);
            session.saveOrUpdate(product);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public ProductInStore getProduct(UUID barcode) {
        Session session = HibernateUtil.getSession();
        ProductInStore productFromDB = null;
        try {
            productFromDB = session.createQuery(
                            "SELECT p FROM ProductInStore p LEFT JOIN FETCH p.discounts WHERE p.id = :productId",
                            ProductInStore.class)
                    .setParameter("productId", barcode)
                    .uniqueResult();;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return productFromDB;
    }

    public List<ProductInStore> getAll() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        List<ProductInStore> products = new ArrayList<ProductInStore>();
        try {
            transaction = session.beginTransaction();
            products = session.createQuery("from ProductInStore ", ProductInStore.class).list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            return products;
        }
    }
}
