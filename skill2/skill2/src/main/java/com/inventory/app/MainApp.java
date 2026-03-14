package com.inventory.app;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.inventory.entity.Product;
import com.inventory.util.HibernateUtil;

public class MainApp {

    public static void main(String[] args) {

        insert();
        fetch(1);
        update(1);
        delete(2);

        HibernateUtil.getSessionFactory().close();
    }

    static void insert() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.save(new Product("Laptop", "Gaming Laptop", 70000, 5));
        session.save(new Product("Phone", "Android Phone", 30000, 10));

        tx.commit();
        session.close();
    }

    static void fetch(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Product p = session.get(Product.class, id);
        if (p != null) {
            System.out.println("Product Name: " + p.getName());
        }

        session.close();
    }

    static void update(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Product p = session.get(Product.class, id);
        if (p != null) {
            p.setPrice(75000);
            p.setQuantity(8);
        }

        tx.commit();
        session.close();
    }

    static void delete(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Product p = session.get(Product.class, id);
        if (p != null) {
            session.delete(p);
        }

        tx.commit();
        session.close();
    }
}