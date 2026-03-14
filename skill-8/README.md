# skill-8 — Product Search Module with Spring Data JPA

A Spring Boot application demonstrating product search using **Spring Data JPA** — including derived query methods and JPQL queries.

---

## Prerequisites

- Java 17+
- Maven 3.8+
- Spring Boot 3.2.0
- Spring Data JPA
- H2 In-Memory Database

---

## Project Structure

```
skill-8/
├── src/
│   └── main/
│       ├── java/com/example/skill8/
│       │   ├── Skill8Application.java          ← Main class
│       │   ├── entity/
│       │   │   └── Product.java                ← JPA Entity
│       │   ├── repository/
│       │   │   └── ProductRepository.java      ← Derived + JPQL queries
│       │   └── controller/
│       │       └── ProductController.java      ← REST endpoints
│       └── resources/
│           ├── application.properties          ← App config + H2 setup
│           └── data.sql                        ← Sample product records
└── pom.xml
```

---

## How to Run

```bash
# Clone / unzip and navigate to project root
cd skill-8

# Build and run
mvn spring-boot:run
```

Server starts at: `http://localhost:8080`

H2 Console: `http://localhost:8080/h2-console`  
JDBC URL: `jdbc:h2:mem:productdb` | Username: `sa` | Password: *(blank)*

---

## REST API Endpoints

| Method | Endpoint | Description | Query Type |
|--------|----------|-------------|------------|
| GET | `/products` | All products | JPA findAll |
| GET | `/products/category/{category}` | Products by category | Derived Query |
| GET | `/products/filter?min=&max=` | Products in price range | Derived Query |
| GET | `/products/sorted` | Products sorted by price | JPQL |
| GET | `/products/expensive/{price}` | Products above price | JPQL |
| POST | `/products` | Add new product | JPA save |

---

## Postman Test Examples

### 1. Get all products by category
```
GET http://localhost:8080/products/category/Electronics
GET http://localhost:8080/products/category/Footwear
GET http://localhost:8080/products/category/Clothing
GET http://localhost:8080/products/category/Kitchen
```

### 2. Filter by price range
```
GET http://localhost:8080/products/filter?min=1000&max=10000
GET http://localhost:8080/products/filter?min=500&max=3000
GET http://localhost:8080/products/filter?min=50000&max=100000
```

### 3. Sort all products by price (ascending)
```
GET http://localhost:8080/products/sorted
```

### 4. Fetch products above a price value
```
GET http://localhost:8080/products/expensive/5000
GET http://localhost:8080/products/expensive/50000
```

### 5. Add a new product
```
POST http://localhost:8080/products
Content-Type: application/json

{
  "name": "Sony WH-1000XM5",
  "category": "Electronics",
  "price": 29990.00
}
```

---

## Sample Data (Pre-loaded)

| Category | Products |
|----------|----------|
| Electronics | Samsung Galaxy S24, iPhone 15, OnePlus Nord 3, Boat Rockerz 450, Dell Laptop |
| Footwear | Nike Air Max, Adidas Ultraboost, Puma RS-X3, Bata Loafers, Red Tape Formals |
| Clothing | Levi's Jeans, Allen Solly Shirt, H&M Tee, Zara Jacket, Van Heusen Trousers |
| Kitchen | Prestige Cooker, Philips Air Fryer, Bosch Mixer, Milton Casserole, Hawkins Pan |

---

## Spring Data JPA Concepts Used

### Derived Query Methods
Spring automatically generates SQL from the method name:

```java
List<Product> findByCategory(String category);
// → SELECT * FROM product WHERE category = ?

List<Product> findByPriceBetween(double min, double max);
// → SELECT * FROM product WHERE price BETWEEN ? AND ?
```

### JPQL Queries with @Query
Custom JPQL queries for more complex operations:

```java
@Query("SELECT p FROM Product p ORDER BY p.price ASC")
List<Product> findAllSortedByPrice();

@Query("SELECT p FROM Product p WHERE p.price > :price ORDER BY p.price ASC")
List<Product> findProductsAbovePrice(@Param("price") double price);

@Query("SELECT p FROM Product p WHERE p.category = :category")
List<Product> findProductsByCategory(@Param("category") String category);
```

---

## GitHub Push (Task 7)

```bash
git init
git add .
git commit -m "Initial commit: skill-8 Product Search Module with Spring Data JPA"
git branch -M main
git remote add origin https://github.com/<your-username>/skill-8.git
git push -u origin main
```
