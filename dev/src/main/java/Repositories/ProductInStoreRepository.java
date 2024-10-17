package Repositories;

import Domain.Discount;
import Domain.Product;
import Domain.ProductInStore;
import db.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProductInStoreRepository {
    public static void add(ProductInStore product) {
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

    public static void addDiscount(ProductInStore product, Discount discount) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            ProductInStore productFromDB = getProduct(product.getBarcode());
            productFromDB.getDiscounts().add(discount);
            session.update(productFromDB);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static ProductInStore getProduct(UUID barcode) {
        Session session = HibernateUtil.getSession();
        ProductInStore productFromDB = null;
        try {
            productFromDB = session.createQuery(
                            "SELECT p FROM ProductInStore p LEFT JOIN FETCH p.discounts WHERE p.id = :productId",
                            ProductInStore.class)
                    .setParameter("productId", barcode)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return productFromDB;
    }

    public static List<ProductInStore> getAll() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        List<ProductInStore> products = new ArrayList<ProductInStore>();
        List<ProductInStore> productsFromDB = new ArrayList<ProductInStore>();

        try {
            transaction = session.beginTransaction();
            products = session.createQuery("from ProductInStore ", ProductInStore.class).list();

            for (ProductInStore product : products) {
                productsFromDB.add(session.createQuery(
                                "SELECT p FROM ProductInStore p LEFT JOIN FETCH p.discounts WHERE p.id = :productId",
                                ProductInStore.class)
                        .setParameter("productId", product.getBarcode())
                        .uniqueResult());
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            return productsFromDB;
        }
    }
}
