package Repositories;

import Domain.StackLocation;
import db.HibernateUtil;

public class StackLocationRepository {
    public void add(StackLocation contact) {
        HibernateUtil.getSession().save(contact);
    }

    public void remove(StackLocation contact) {
        HibernateUtil.getSession().remove(contact);
    }

    public void update(StackLocation contact) {
        HibernateUtil.getSession().update(contact);
    }

    public StackLocation get(String name) {
        // Get contact from database
        return HibernateUtil.getSession().get(StackLocation.class, name);
    }
}
