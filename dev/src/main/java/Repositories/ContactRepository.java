package Repositories;

import Domain.Contact;
import db.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ContactRepository {
    public void add(Contact contact) {

        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(contact);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void remove(Contact contact) {
        HibernateUtil.getSession().remove(contact);
    }

    public void update(Contact contact) {
        // Update contact in database
    }

    public Contact get(String name) {
        // Get contact from database
        return HibernateUtil.getSession().get(Contact.class, name) ;
    }

    public List<Contact> getAll() {
        // Get all contacts from database
        return null;
    }
}
