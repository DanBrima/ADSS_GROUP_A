package Repositories;

import Domain.StackLocation;
import db.HibernateUtil;

public class StackLocationRepository {
    public static void add(StackLocation contact) {
        HibernateUtil.getSession().save(contact);
    }

    public static void remove(StackLocation contact) {
        HibernateUtil.getSession().remove(contact);
    }

    public static void update(StackLocation contact) {
        HibernateUtil.getSession().update(contact);
    }

    public static StackLocation get(String name) {
        // Get contact from database
        return HibernateUtil.getSession().get(StackLocation.class, name);
    }
}
