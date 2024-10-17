package Repositories;

import Domain.Contract;
import db.HibernateUtil;

public class ContractRepository {
    public static void add(Contract contract) {
        HibernateUtil.getSession().save(contract);
    }

    public static void remove(Contract contract) {
        HibernateUtil.getSession().remove(contract);
    }

    public static void update(Contract contract) {
        HibernateUtil.getSession().update(contract);
    }

    public static Contract get(String id) {
        // Get contact from database
        return HibernateUtil.getSession().get(Contract.class, id) ;
    }
}
