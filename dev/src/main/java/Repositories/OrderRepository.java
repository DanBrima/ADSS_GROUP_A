package Repositories;

import Domain.Order;
import db.HibernateUtil;

public class OrderRepository {
    public static void add(Order order) {
        HibernateUtil.getSession().save(order);
    }

    public static void remove(Order order) {
        HibernateUtil.getSession().remove(order);
    }

    public static void update(Order order) {
        HibernateUtil.getSession().update(order);
    }

    public static Order get(String id) {
        // Get contact from database
        return HibernateUtil.getSession().get(Order.class, id) ;
    }
}
