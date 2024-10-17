package Repositories;

import Domain.Contact;
import db.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ContactRepository {
    public static void add(Contact contact) {
        HibernateUtil.getSession().save(contact);
    }

    public static void remove(Contact contact) {
        HibernateUtil.getSession().remove(contact);
    }

    public static void update(Contact contact) {
        HibernateUtil.getSession().update(contact);
    }

    public static Contact get(String name) {
        // Get contact from database
        return HibernateUtil.getSession().get(Contact.class, name) ;
    }
}
