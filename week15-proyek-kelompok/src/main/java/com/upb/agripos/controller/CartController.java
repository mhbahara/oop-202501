package com.upb.agripos.controller;

import com.upb.agripos.exception.OutOfStockException;
import com.upb.agripos.exception.ValidationException;
import com.upb.agripos.model.CartItem;
import com.upb.agripos.service.CartService;
import java.util.List;

public class CartController {
    private CartService cartService;
    
    public CartController() {
        this.cartService = new CartService();
    }
    
    public void addToCart(String productKode, int quantity) 
            throws ValidationException, OutOfStockException, Exception {
        cartService.addItem(productKode, quantity);
    }
    
    public void updateQuantity(String productKode, int newQuantity) 
            throws ValidationException, OutOfStockException, Exception {
        cartService.updateItemQuantity(productKode, newQuantity);
    }
    
    public void removeFromCart(String productKode) {
        cartService.removeItem(productKode);
    }
    
    public List<CartItem> getCartItems() {
        return cartService.getItems();
    }
    
    public double getTotalAmount() {
        return cartService.getTotalAmount();
    }
    
    public int getTotalItems() {
        return cartService.getTotalItems();
    }
    
    public void clearCart() {
        cartService.clearCart();
    }
    
    public boolean isCartEmpty() {
        return cartService.isEmpty();
    }
    
    public CartService getCartService() {
        return cartService;
    }
}