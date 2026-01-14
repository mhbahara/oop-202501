package com.upb.agripos.service;

import com.upb.agripos.model.Cart;
import com.upb.agripos.model.Product;
import com.upb.agripos.exception.InsufficientStockException;
import com.upb.agripos.exception.ValidationException;

/**
 * Service untuk Cart - mengelola keranjang belanja
 * Bab 7: Collections dalam action
 */
public class CartService {
    private final Cart cart;

    public CartService() {
        this.cart = new Cart();
    }

    public void addToCart(Product product, int quantity) 
            throws ValidationException, InsufficientStockException {
        
        if (quantity <= 0) {
            throw new ValidationException("Jumlah harus lebih dari 0");
        }
        
        if (quantity > product.getStock()) {
            throw new InsufficientStockException(
                product.getName(), quantity, product.getStock()
            );
        }

        cart.addItem(product, quantity);
    }

    public void removeFromCart(String productCode) {
        cart.removeItem(productCode);
    }

    public void updateQuantity(String productCode, int newQuantity) {
        cart.updateQuantity(productCode, newQuantity);
    }

    public void clearCart() {
        cart.clear();
    }

    public Cart getCart() {
        return cart;
    }
}