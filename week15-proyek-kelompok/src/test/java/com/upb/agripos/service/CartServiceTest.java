package com.upb.agripos.service;

import com.upb.agripos.exception.ValidationException;
import com.upb.agripos.model.CartItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class CartServiceTest {
    private CartService cartService;
    
    @BeforeEach
    void setUp() {
        cartService = new CartService();
    }
    
    @Test
    void testInitialCartIsEmpty() {
        assertTrue(cartService.isEmpty());
        assertEquals(0, cartService.getTotalItems());
        assertEquals(0, cartService.getTotalAmount(), 0.01);
    }
    
    @Test
    void testClearCart() {
        cartService.clearCart();
        assertTrue(cartService.isEmpty());
        assertEquals(0, cartService.getTotalItems());
    }
    
    @Test
    void testGetItemsReturnsNewList() {
        List<CartItem> items1 = cartService.getItems();
        List<CartItem> items2 = cartService.getItems();
        
        // Should return different instances (defensive copy)
        assertNotSame(items1, items2);
    }
    
    // Note: Testing addItem, updateQuantity, removeItem requires database access
    // so these are integration tests rather than pure unit tests
    // They would be tested in integration test suite
}