// ============================================================
// FILE 6: src/test/java/com/upb/agripos/ProductTest.java
// ============================================================
package com.upb.agripos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.upb.agripos.config.DatabaseConnection;
import com.upb.agripos.model.Product;

public class ProductTest {
    
    private Product product;

    @BeforeEach
    public void setUp() {
        product = new Product("P01", "Benih Jagung");
    }

    @Test
    @DisplayName("Test Product Name")
    public void testProductName() {
        assertEquals("Benih Jagung", product.getName());
    }

    @Test
    @DisplayName("Test Product Code")
    public void testProductCode() {
        assertEquals("P01", product.getCode());
    }

    @Test
    @DisplayName("Test Product Not Null")
    public void testProductNotNull() {
        assertNotNull(product);
        assertNotNull(product.getCode());
        assertNotNull(product.getName());
    }

    @Test
    @DisplayName("Test Singleton Pattern - Same Instance")
    public void testSingletonInstance() {
        DatabaseConnection db1 = DatabaseConnection.getInstance();
        DatabaseConnection db2 = DatabaseConnection.getInstance();
        
        // Kedua instance harus sama (referensi objek sama)
        assertSame(db1, db2, "DatabaseConnection harus menghasilkan instance yang sama");
    }

    @Test
    @DisplayName("Test Singleton Pattern - Not Null")
    public void testSingletonNotNull() {
        DatabaseConnection db = DatabaseConnection.getInstance();
        assertNotNull(db, "DatabaseConnection instance tidak boleh null");
    }
}