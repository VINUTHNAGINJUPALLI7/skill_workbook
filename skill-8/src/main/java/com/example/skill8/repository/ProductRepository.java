package com.example.skill8.repository;

import com.example.skill8.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // ─────────────────────────────────────────────────────────────────
    // Derived Query Methods (Spring Data JPA auto-generates the SQL)
    // ─────────────────────────────────────────────────────────────────

    /**
     * Derived Query: Find all products by category (case-sensitive match).
     * Equivalent SQL: SELECT * FROM product WHERE category = ?
     */
    List<Product> findByCategory(String category);

    /**
     * Derived Query: Find all products whose price is between min and max (inclusive).
     * Equivalent SQL: SELECT * FROM product WHERE price BETWEEN ? AND ?
     */
    List<Product> findByPriceBetween(double min, double max);

    // ─────────────────────────────────────────────────────────────────
    // JPQL Queries using @Query annotation
    // ─────────────────────────────────────────────────────────────────

    /**
     * JPQL: Fetch all products sorted by price in ascending order.
     */
    @Query("SELECT p FROM Product p ORDER BY p.price ASC")
    List<Product> findAllSortedByPrice();

    /**
     * JPQL: Fetch all products whose price is greater than the given value.
     *
     * @param price the minimum price threshold (exclusive)
     */
    @Query("SELECT p FROM Product p WHERE p.price > :price ORDER BY p.price ASC")
    List<Product> findProductsAbovePrice(@Param("price") double price);

    /**
     * JPQL: Fetch all products that belong to the specified category.
     *
     * @param category the category name to filter by
     */
    @Query("SELECT p FROM Product p WHERE p.category = :category")
    List<Product> findProductsByCategory(@Param("category") String category);
}
