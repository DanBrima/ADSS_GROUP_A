package Repositories;

import Domain.ItemStack;
import db.HibernateUtil;

public class ItemStackRepository {
    public void add(ItemStack itemStack) {
        HibernateUtil.getSession().save(itemStack);
    }

    public void remove(ItemStack itemStack) {
        HibernateUtil.getSession().remove(itemStack);
    }

    public void update(ItemStack itemStack) {
        HibernateUtil.getSession().update(itemStack);
    }

    public ItemStack get(String name) {
        // Get contact from database
        return HibernateUtil.getSession().get(ItemStack.class, name);
    }
}
