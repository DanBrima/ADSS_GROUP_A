package Repositories;

import Domain.Storage;
import db.HibernateUtil;

public class StorageRepository {

    public static void add(Storage storage) {
        HibernateUtil.getSession().save(storage);
    }

    public static void remove(Storage storage) {
        HibernateUtil.getSession().remove(storage);
    }

    public static void update(Storage storage) {
        HibernateUtil.getSession().update(storage);
    }

    public Storage get(String storage) {
        // Get contact from database
        return HibernateUtil.getSession().get(Storage.class, storage);
    }
}

