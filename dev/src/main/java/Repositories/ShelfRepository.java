package Repositories;

import Domain.Shelf;
import db.HibernateUtil;

public class ShelfRepository {
    public static void add(Shelf shelf) {
        HibernateUtil.getSession().save(shelf);
    }

    public static void remove(Shelf shelf) {
        HibernateUtil.getSession().remove(shelf);
    }

    public static void update(Shelf shelf) {
        HibernateUtil.getSession().update(shelf);
    }

    public static Shelf get(String shelf) {
        // Get contact from database
        return HibernateUtil.getSession().get(Shelf.class, shelf);
    }
}
