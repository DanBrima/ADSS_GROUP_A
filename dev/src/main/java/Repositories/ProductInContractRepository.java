package Repositories;

import Domain.ProductInContract;
import db.HibernateUtil;

import java.util.UUID;

public class ProductInContractRepository {
    public static void add(ProductInContract productInContract) {
        HibernateUtil.getSession().save(productInContract);
    }

    public static void remove(ProductInContract productInContract) {
        HibernateUtil.getSession().remove(productInContract);
    }

    public static void update(ProductInContract productInContract) {
        HibernateUtil.getSession().update(productInContract);
    }

    public static ProductInContract get(UUID supplierCatalogID) {
        // Get contact from database
        return HibernateUtil.getSession().get(ProductInContract.class, supplierCatalogID) ;
    }
}
