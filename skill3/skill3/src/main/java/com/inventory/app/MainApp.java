package com.inventory.app;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.inventory.entity.Product;
import com.inventory.util.HibernateUtil;

public class MainApp {

    public static void main(String[] args) {

        insertProducts();

        sortByPriceAsc();
        sortByPriceDesc();
        sortByQuantityDesc();

        paginationFirst3();
        paginationNext3();

        aggregateQueries();

        groupByDescription();
        filterByPriceRange();

        likeQueries();

        HibernateUtil.getSessionFactory().close();
    }

    // 2️⃣ Insert 5–8 Products
    static void insertProducts() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.save(new Product("Mobile", "Electronics", 17000, 5));
        session.save(new Product("Laptop", "Electronics", 65000, 8));
        session.save(new Product("Mouse", "Accessories", 600, 20));
        session.save(new Product("Keyboard", "Accessories", 1500, 15));
        session.save(new Product("Table", "Furniture", 7000, 2));
        session.save(new Product("Chair", "Furniture", 3500, 6));
        session.save(new Product("Pen", "Stationery", 20, 100));

        tx.commit();
        session.close();
    }

    // 3️⃣ a) Sort by Price Ascending
    static void sortByPriceAsc() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Product> q = session.createQuery(
                "FROM Product p ORDER BY p.price ASC", Product.class);
        q.list().forEach(p ->
                System.out.println(p.getName() + " " + p.getPrice()));
        session.close();
    }

    // 3️⃣ b) Sort by Price Descending
    static void sortByPriceDesc() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Product> q = session.createQuery(
                "FROM Product p ORDER BY p.price DESC", Product.class);
        q.list().forEach(p ->
                System.out.println(p.getName() + " " + p.getPrice()));
        session.close();
    }

    // 4️⃣ Sort by Quantity (Highest First)
    static void sortByQuantityDesc() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Product> q = session.createQuery(
                "FROM Product p ORDER BY p.quantity DESC", Product.class);
        q.list().forEach(p ->
                System.out.println(p.getName() + " " + p.getQuantity()));
        session.close();
    }

    // 5️⃣ a) First 3 Products (Pagination)
    static void paginationFirst3() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Product> q = session.createQuery("FROM Product", Product.class);
        q.setFirstResult(0);
        q.setMaxResults(3);
        q.list().forEach(p -> System.out.println(p.getName()));
        session.close();
    }

    // 5️⃣ b) Next 3 Products (Pagination)
    static void paginationNext3() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Product> q = session.createQuery("FROM Product", Product.class);
        q.setFirstResult(3);
        q.setMaxResults(3);
        q.list().forEach(p -> System.out.println(p.getName()));
        session.close();
    }

    // 6️⃣ Aggregate Queries
    static void aggregateQueries() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Long total = session.createQuery(
                "SELECT COUNT(p) FROM Product p", Long.class)
                .uniqueResult();
        System.out.println("Total Products: " + total);

        Long available = session.createQuery(
                "SELECT COUNT(p) FROM Product p WHERE p.quantity > 0", Long.class)
                .uniqueResult();
        System.out.println("Available Products: " + available);

        Query<Object[]> q = session.createQuery(
                "SELECT p.description, COUNT(p) FROM Product p GROUP BY p.description",
                Object[].class);
        for (Object[] row : q.list()) {
            System.out.println(row[0] + " : " + row[1]);
        }

        Object[] minMax = session.createQuery(
                "SELECT MIN(p.price), MAX(p.price) FROM Product p",
                Object[].class).uniqueResult();
        System.out.println("Min Price: " + minMax[0] +
                " Max Price: " + minMax[1]);

        session.close();
    }

    // 7️⃣ GROUP BY Description
    static void groupByDescription() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Object[]> q = session.createQuery(
                "SELECT p.description, COUNT(p) FROM Product p GROUP BY p.description",
                Object[].class);
        q.list().forEach(r ->
                System.out.println(r[0] + " -> " + r[1]));
        session.close();
    }

    // 8️⃣ WHERE Price Range
    static void filterByPriceRange() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Product> q = session.createQuery(
                "FROM Product p WHERE p.price BETWEEN :min AND :max",
                Product.class);
        q.setParameter("min", 1000.0);
        q.setParameter("max", 20000.0);
        q.list().forEach(p ->
                System.out.println(p.getName()));
        session.close();
    }

    // 9️⃣ LIKE Queries (FIXED & CLEAN)
    static void likeQueries() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Query<Product> q1 = session.createQuery(
                "FROM Product p WHERE p.name LIKE 'M%'", Product.class);
        q1.list().forEach(p ->
                System.out.println("Starts M: " + p.getName()));

        Query<Product> q2 = session.createQuery(
                "FROM Product p WHERE p.name LIKE '%r'", Product.class);
        q2.list().forEach(p ->
                System.out.println("Ends r: " + p.getName()));

        Query<Product> q3 = session.createQuery(
                "FROM Product p WHERE p.name LIKE '%top%'", Product.class);
        q3.list().forEach(p ->
                System.out.println("Contains: " + p.getName()));

        Query<Product> q4 = session.createQuery(
                "FROM Product p WHERE length(p.name) = 5", Product.class);
        q4.list().forEach(p ->
                System.out.println("Length 5: " + p.getName()));

        session.close();
    }
}
