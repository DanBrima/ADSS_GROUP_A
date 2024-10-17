package Repositories;

import Domain.ProductInContract;
import db.HibernateUtil;

import java.util.UUID;

public class ProductInContractRepository {
    public void add(ProductInContract productInContract) {
        HibernateUtil.getSession().save(productInContract);
    }

    public void remove(ProductInContract productInContract) {
        HibernateUtil.getSession().remove(productInContract);
    }

    public void update(ProductInContract productInContract) {
        HibernateUtil.getSession().update(productInContract);
    }

    public ProductInContract get(UUID supplierCatalogID) {
        // Get contact from database
        return HibernateUtil.getSession().get(ProductInContract.class, supplierCatalogID) ;
    }
}
