package Repositories;

import Domain.Contact;
import db.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ContactRepository {
    public void add(Contact contact) {
        HibernateUtil.getSession().save(contact);
    }

    public void remove(Contact contact) {
        HibernateUtil.getSession().remove(contact);
    }

    public void update(Contact contact) {
        HibernateUtil.getSession().update(contact);
    }

    public Contact get(String name) {
        // Get contact from database
        return HibernateUtil.getSession().get(Contact.class, name) ;
    }
}
