package Repositories;

import Domain.Contact;
import db.HibernateUtil;

import java.util.List;

public class ContactRepository {
    public void add(Contact contact) {
        HibernateUtil.getSession().save(contact);
    }

    public void remove(Contact contact) {
        HibernateUtil.getSession().remove(contact);
    }

    public void update(Contact contact) {
        // Update contact in database
    }

    public Contact get(Class<Contact> contactClass, String name) {
        // Get contact from database
        return HibernateUtil.getSession().get(Contact.class, name) ;
    }

    public List<Contact> getAll() {
        // Get all contacts from database
        return null;
    }
}
