package Repositories;

import Domain.ProductInOrder;
import db.HibernateUtil;

public class ProductInOrderRepository {
   public static void add(ProductInOrder productInOrder) {
       HibernateUtil.getSession().save(productInOrder);
   }

    public static void remove(ProductInOrder productInOrder) {
         HibernateUtil.getSession().remove(productInOrder);
    }

    public static void update(ProductInOrder productInOrder) {
         HibernateUtil.getSession().update(productInOrder);
    }

    public static ProductInOrder get(String id) {
         // Get contact from database
         return HibernateUtil.getSession().get(ProductInOrder.class, id) ;
    }
}
