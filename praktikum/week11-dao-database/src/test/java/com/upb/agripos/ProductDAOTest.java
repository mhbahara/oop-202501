package com.upb.agripos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.upb.agripos.dao.ProductDAO;
import com.upb.agripos.dao.ProductDAOImpl;
import com.upb.agripos.model.Product;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductDAOTest {
    
    // Database Connection Configuration
    private static final String DB_URL = "jdbc:postgresql://localhost:1212/agripos";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "230805";
    
    private static Connection conn;
    private ProductDAO dao;

    @BeforeAll
    static void setupDatabase() throws SQLException {
        System.out.println("Setting up database connection...");
        conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        System.out.println("✓ Database connection established");
    }

    @BeforeEach
    void setup() {
        dao = new ProductDAOImpl(conn);
    }

    @Test
    @Order(1)
    @DisplayName("Test 1: Insert Product")
    void testInsert() throws Exception {
        System.out.println("\n--- Test Insert ---");
        
        Product product = new Product("TEST01", "Test Product", 10000, 5);
        dao.insert(product);
        
        Product found = dao.findByCode("TEST01");
        Assertions.assertNotNull(found, "Product should be found after insert");
        Assertions.assertEquals("Test Product", found.getName());
        Assertions.assertEquals(10000, found.getPrice());
        Assertions.assertEquals(5, found.getStock());
        
        System.out.println("✓ Insert test passed");
    }

    @Test
    @Order(2)
    @DisplayName("Test 2: Find By Code")
    void testFindByCode() throws Exception {
        System.out.println("\n--- Test Find By Code ---");
        
        Product found = dao.findByCode("TEST01");
        
        Assertions.assertNotNull(found, "Product TEST01 should exist");
        Assertions.assertEquals("TEST01", found.getCode());
        Assertions.assertEquals("Test Product", found.getName());
        
        System.out.println("✓ Find by code test passed");
    }

    @Test
    @Order(3)
    @DisplayName("Test 3: Find All Products")
    void testFindAll() throws Exception {
        System.out.println("\n--- Test Find All ---");
        
        List<Product> products = dao.findAll();
        
        Assertions.assertNotNull(products, "Product list should not be null");
        Assertions.assertTrue(products.size() > 0, "Should have at least one product");
        
        System.out.println("✓ Find all test passed - Found " + products.size() + " products");
    }

    @Test
    @Order(4)
    @DisplayName("Test 4: Update Product")
    void testUpdate() throws Exception {
        System.out.println("\n--- Test Update ---");
        
        Product updatedProduct = new Product("TEST01", "Updated Test Product", 15000, 10);
        dao.update(updatedProduct);
        
        Product found = dao.findByCode("TEST01");
        
        Assertions.assertNotNull(found, "Product should still exist after update");
        Assertions.assertEquals("Updated Test Product", found.getName());
        Assertions.assertEquals(15000, found.getPrice());
        Assertions.assertEquals(10, found.getStock());
        
        System.out.println("✓ Update test passed");
    }

    @Test
    @Order(5)
    @DisplayName("Test 5: Delete Product")
    void testDelete() throws Exception {
        System.out.println("\n--- Test Delete ---");
        
        dao.delete("TEST01");
        
        Product found = dao.findByCode("TEST01");
        
        Assertions.assertNull(found, "Product should not exist after deletion");
        
        System.out.println("✓ Delete test passed");
    }

    @Test
    @Order(6)
    @DisplayName("Test 6: Find Non-Existent Product")
    void testFindNonExistent() throws Exception {
        System.out.println("\n--- Test Find Non-Existent ---");
        
        Product notFound = dao.findByCode("NOTEXIST");
        
        Assertions.assertNull(notFound, "Non-existent product should return null");
        
        System.out.println("✓ Find non-existent test passed");
    }

    @Test
    @Order(7)
    @DisplayName("Test 7: Multiple Inserts")
    void testMultipleInserts() throws Exception {
        System.out.println("\n--- Test Multiple Inserts ---");
        
        dao.insert(new Product("BATCH01", "Batch Product 1", 5000, 20));
        dao.insert(new Product("BATCH02", "Batch Product 2", 7500, 30));
        dao.insert(new Product("BATCH03", "Batch Product 3", 10000, 40));
        
        List<Product> all = dao.findAll();
        long batchCount = all.stream()
            .filter(p -> p.getCode().startsWith("BATCH"))
            .count();
        
        Assertions.assertEquals(3, batchCount, "Should have 3 batch products");
        
        // Cleanup
        dao.delete("BATCH01");
        dao.delete("BATCH02");
        dao.delete("BATCH03");
        
        System.out.println("✓ Multiple inserts test passed");
    }

    @AfterAll
    static void closeConnection() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
            System.out.println("\n✓ Database connection closed");
        }
    }
}