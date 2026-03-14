package com.example.skill8.controller;

import com.example.skill8.entity.Product;
import com.example.skill8.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for Product Search Operations.
 *
 * Available Endpoints:
 *  GET /products                          - Fetch all products
 *  GET /products/category/{category}      - Products by category (derived query)
 *  GET /products/filter?min=&max=         - Products in price range (derived query)
 *  GET /products/sorted                   - All products sorted by price (JPQL)
 *  GET /products/expensive/{price}        - Products above a price value (JPQL)
 *  POST /products                         - Add a new product
 */
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    // ─────────────────────────────────────────────────────────────────
    //  GET /products
    //  Returns all products in the database
    // ─────────────────────────────────────────────────────────────────
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products);
    }

    // ─────────────────────────────────────────────────────────────────
    //  GET /products/category/{category}
    //  Uses Derived Query: findByCategory(String category)
    //  Example: GET /products/category/Electronics
    // ─────────────────────────────────────────────────────────────────
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getByCategory(@PathVariable String category) {
        List<Product> products = productRepository.findByCategory(category);
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products);
    }

    // ─────────────────────────────────────────────────────────────────
    //  GET /products/filter?min=1000&max=10000
    //  Uses Derived Query: findByPriceBetween(double min, double max)
    //  Example: GET /products/filter?min=1000&max=10000
    // ─────────────────────────────────────────────────────────────────
    @GetMapping("/filter")
    public ResponseEntity<List<Product>> getByPriceRange(
            @RequestParam double min,
            @RequestParam double max) {
        List<Product> products = productRepository.findByPriceBetween(min, max);
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products);
    }

    // ─────────────────────────────────────────────────────────────────
    //  GET /products/sorted
    //  Uses JPQL @Query: findAllSortedByPrice()
    //  Returns all products sorted by price (ascending)
    // ─────────────────────────────────────────────────────────────────
    @GetMapping("/sorted")
    public ResponseEntity<List<Product>> getSortedByPrice() {
        List<Product> products = productRepository.findAllSortedByPrice();
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products);
    }

    // ─────────────────────────────────────────────────────────────────
    //  GET /products/expensive/{price}
    //  Uses JPQL @Query: findProductsAbovePrice(double price)
    //  Example: GET /products/expensive/5000
    // ─────────────────────────────────────────────────────────────────
    @GetMapping("/expensive/{price}")
    public ResponseEntity<List<Product>> getExpensiveProducts(@PathVariable double price) {
        List<Product> products = productRepository.findProductsAbovePrice(price);
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products);
    }

    // ─────────────────────────────────────────────────────────────────
    //  POST /products
    //  Add a new product to the database
    //  Body (JSON): { "name": "...", "category": "...", "price": ... }
    // ─────────────────────────────────────────────────────────────────
    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product saved = productRepository.save(product);
        return ResponseEntity.ok(saved);
    }
}
