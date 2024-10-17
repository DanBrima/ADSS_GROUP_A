package Repositories;

import Domain.Shelf;
import db.HibernateUtil;

public class ShelfRepository {
    public void add(Shelf shelf) {
        HibernateUtil.getSession().save(shelf);
    }

    public void remove(Shelf shelf) {
        HibernateUtil.getSession().remove(shelf);
    }

    public void update(Shelf shelf) {
        HibernateUtil.getSession().update(shelf);
    }

    public Shelf get(String shelf) {
        // Get contact from database
        return HibernateUtil.getSession().get(Shelf.class, shelf);
    }
}
