package com.upb.agripos;

import com.upb.agripos.model.Cart;
import com.upb.agripos.model.Product;
import com.upb.agripos.service.CartService;
import com.upb.agripos.exception.InsufficientStockException;
import com.upb.agripos.exception.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Bab 10: Unit Testing dengan JUnit
 * Test untuk CartService (non-UI logic)
 */
class CartServiceTest {

    private CartService cartService;
    private Product product1;
    private Product product2;

    @BeforeEach
    void setUp() {
        cartService = new CartService();
        
        product1 = new Product("P001", "Beras Premium 5kg", 
                new BigDecimal("75000.00"), 50);
        
        product2 = new Product("P002", "Pupuk Organik 10kg", 
                new BigDecimal("45000.00"), 100);
    }

    @Test
    void testAddToCart_Success() throws ValidationException, InsufficientStockException {
        cartService.addToCart(product1, 5);
        
        Cart cart = cartService.getCart();
        assertEquals(1, cart.getItems().size(), "Cart should have 1 item");
        assertEquals(5, cart.getTotalItems(), "Total items should be 5");
    }

    @Test
    void testAddToCart_InvalidQuantity() {
        Exception exception = assertThrows(ValidationException.class, () -> {
            cartService.addToCart(product1, 0);
        });
        
        assertTrue(exception.getMessage().contains("lebih dari 0"));
    }

    @Test
    void testAddToCart_InsufficientStock() {
        Exception exception = assertThrows(InsufficientStockException.class, () -> {
            cartService.addToCart(product1, 100); // Stock only 50
        });
        
        assertTrue(exception.getMessage().contains("Stok tidak cukup"));
    }

    @Test
    void testCalculateTotal() throws ValidationException, InsufficientStockException {
        cartService.addToCart(product1, 2); // 2 x 75000 = 150000
        cartService.addToCart(product2, 3); // 3 x 45000 = 135000
        
        BigDecimal expectedTotal = new BigDecimal("285000.00");
        BigDecimal actualTotal = cartService.getCart().calculateTotal();
        
        assertEquals(0, expectedTotal.compareTo(actualTotal), 
                "Total should be 285000");
    }

    @Test
    void testRemoveFromCart() throws ValidationException, InsufficientStockException {
        cartService.addToCart(product1, 5);
        cartService.addToCart(product2, 3);
        
        assertEquals(2, cartService.getCart().getItems().size());
        
        cartService.removeFromCart("P001");
        
        assertEquals(1, cartService.getCart().getItems().size());
        assertEquals(3, cartService.getCart().getTotalItems());
    }

    @Test
    void testClearCart() throws ValidationException, InsufficientStockException {
        cartService.addToCart(product1, 5);
        cartService.addToCart(product2, 3);
        
        assertFalse(cartService.getCart().isEmpty());
        
        cartService.clearCart();
        
        assertTrue(cartService.getCart().isEmpty());
        assertEquals(BigDecimal.ZERO, cartService.getCart().calculateTotal());
    }

    @Test
    void testAddDuplicateProduct() throws ValidationException, InsufficientStockException {
        cartService.addToCart(product1, 5);
        cartService.addToCart(product1, 3); // Add same product again
        
        Cart cart = cartService.getCart();
        assertEquals(1, cart.getItems().size(), "Should still have 1 unique product");
        assertEquals(8, cart.getTotalItems(), "Total quantity should be 8");
    }
}