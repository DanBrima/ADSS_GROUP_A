package Repositories;

import db.HibernateUtil;

import java.util.function.Supplier;

public class SupplierRepository {
    public void add(Supplier supplier) {
        HibernateUtil.getSession().save(supplier);
    }

    public void remove(Supplier supplier) {
        HibernateUtil.getSession().remove(supplier);
    }

    public void update(Supplier supplier) {
        HibernateUtil.getSession().update(supplier);
    }

    public Supplier get(String name) {
        // Get contact from database
        return HibernateUtil.getSession().get(Supplier.class, name) ;
    }
}
