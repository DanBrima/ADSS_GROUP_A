package Repositories;

import db.HibernateUtil;

import java.util.function.Supplier;

public class SupplierRepository {
    public static void add(Supplier supplier) {
        HibernateUtil.getSession().save(supplier);
    }

    public static void remove(Supplier supplier) {
        HibernateUtil.getSession().remove(supplier);
    }

    public static void update(Supplier supplier) {
        HibernateUtil.getSession().update(supplier);
    }

    public static Supplier get(String name) {
        // Get contact from database
        return HibernateUtil.getSession().get(Supplier.class, name) ;
    }
}
