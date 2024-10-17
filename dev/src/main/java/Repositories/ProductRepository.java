package Repositories;

import Domain.Product;
import db.HibernateUtil;

public class ProductRepository {
    public static void add(Product product) {
        HibernateUtil.getSession().save(product);
    }

    public static void remove(Product product) {
        HibernateUtil.getSession().remove(product);
    }

    public static void update(Product product) {
        HibernateUtil.getSession().update(product);
    }

    public static Product get(String name) {
        // Get contact from database
        return HibernateUtil.getSession().get(Product.class, name);
    }
}
