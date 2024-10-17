package Repositories;

import Domain.ItemStack;
import db.HibernateUtil;

public class ItemStackRepository {
    public static void add(ItemStack itemStack) {
        HibernateUtil.getSession().save(itemStack);
    }

    public static void remove(ItemStack itemStack) {
        HibernateUtil.getSession().remove(itemStack);
    }

    public static void update(ItemStack itemStack) {
        HibernateUtil.getSession().update(itemStack);
    }

    public static ItemStack get(String name) {
        // Get contact from database
        return HibernateUtil.getSession().get(ItemStack.class, name);
    }
}
