package Repositories;

import Domain.Order;
import db.HibernateUtil;

public class OrderRepository {
    public void add(Order order) {
        HibernateUtil.getSession().save(order);
    }

    public void remove(Order order) {
        HibernateUtil.getSession().remove(order);
    }

    public void update(Order order) {
        HibernateUtil.getSession().update(order);
    }

    public Order get(String id) {
        // Get contact from database
        return HibernateUtil.getSession().get(Order.class, id) ;
    }
}
