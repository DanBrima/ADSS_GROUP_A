package Repositories;

import Domain.Product;
import db.HibernateUtil;

public class ProductRepository {
    public void add(Product product) {
        HibernateUtil.getSession().save(product);
    }

    public void remove(Product product) {
        HibernateUtil.getSession().remove(product);
    }

    public void update(Product product) {
        HibernateUtil.getSession().update(product);
    }

    public Product get(String name) {
        // Get contact from database
        return HibernateUtil.getSession().get(Product.class, name);
    }
}
