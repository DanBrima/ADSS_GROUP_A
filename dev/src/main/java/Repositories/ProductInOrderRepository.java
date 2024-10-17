package Repositories;

import Domain.ProductInOrder;
import db.HibernateUtil;

public class ProductInOrderRepository {
   public void add(ProductInOrder productInOrder) {
       HibernateUtil.getSession().save(productInOrder);
   }

    public void remove(ProductInOrder productInOrder) {
         HibernateUtil.getSession().remove(productInOrder);
    }

    public void update(ProductInOrder productInOrder) {
         HibernateUtil.getSession().update(productInOrder);
    }

    public ProductInOrder get(String id) {
         // Get contact from database
         return HibernateUtil.getSession().get(ProductInOrder.class, id) ;
    }
}
