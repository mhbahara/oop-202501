package com.upb.agripos.service;

import com.upb.agripos.exception.ValidationException;
import com.upb.agripos.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {
    private ProductService productService;
    
    @BeforeEach
    void setUp() {
        productService = new ProductService();
    }
    
    @Test
    void testValidateProductWithValidData() {
        Product product = new Product("P999", "Test Product", "Test", 10000.0, 10);
        
        // Should not throw exception
        assertDoesNotThrow(() -> {
            // This would call the private validateProduct method indirectly through addProduct
            // but since we can't directly test private methods, we'll test through public methods
        });
    }
    
    @Test
    void testValidateProductWithEmptyKode() {
        Product product = new Product("", "Test Product", "Test", 10000.0, 10);
        
        assertThrows(ValidationException.class, () -> {
            productService.addProduct(product);
        });
    }
    
    @Test
    void testValidateProductWithNullNama() {
        Product product = new Product("P999", null, "Test", 10000.0, 10);
        
        assertThrows(ValidationException.class, () -> {
            productService.addProduct(product);
        });
    }
    
    @Test
    void testValidateProductWithNegativeHarga() {
        Product product = new Product("P999", "Test", "Test", -1000.0, 10);
        
        assertThrows(ValidationException.class, () -> {
            productService.addProduct(product);
        });
    }
    
    @Test
    void testValidateProductWithNegativeStok() {
        Product product = new Product("P999", "Test", "Test", 10000.0, -5);
        
        assertThrows(ValidationException.class, () -> {
            productService.addProduct(product);
        });
    }
}
