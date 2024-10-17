package Repositories;

import Domain.Contact;
import Domain.Discount;
import Domain.Manufacturer;
import db.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ManufacturerRepository {
    public void add(Manufacturer manufacturer) {
        HibernateUtil.getSession().save(manufacturer);
    }

    public void remove(Manufacturer manufacturer) {
        HibernateUtil.getSession().remove(manufacturer);
    }

    public void update(Manufacturer manufacturer) {
        HibernateUtil.getSession().update(manufacturer);
    }

    public Manufacturer get(String name) {
        // Get contact from database
        return HibernateUtil.getSession().get(Manufacturer.class, name) ;
    }
}
