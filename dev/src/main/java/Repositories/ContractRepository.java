package Repositories;

import Domain.Contract;
import db.HibernateUtil;

public class ContractRepository {
    public void add(Contract contract) {
        HibernateUtil.getSession().save(contract);
    }

    public void remove(Contract contract) {
        HibernateUtil.getSession().remove(contract);
    }

    public void update(Contract contract) {
        HibernateUtil.getSession().update(contract);
    }

    public Contract get(String id) {
        // Get contact from database
        return HibernateUtil.getSession().get(Contract.class, id) ;
    }
}
