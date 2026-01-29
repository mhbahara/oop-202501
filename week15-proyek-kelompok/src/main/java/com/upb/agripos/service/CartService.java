package com.upb.agripos.service;

import com.upb.agripos.dao.ProductDAO;
import com.upb.agripos.dao.ProductDAOImpl;
import com.upb.agripos.exception.OutOfStockException;
import com.upb.agripos.exception.ValidationException;
import com.upb.agripos.model.CartItem;
import com.upb.agripos.model.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CartService {
    private List<CartItem> cartItems;
    private ProductDAO productDAO;
    
    public CartService() {
        this.cartItems = new ArrayList<>();
        this.productDAO = new ProductDAOImpl();
    }
    
    public void addItem(String productKode, int quantity) 
            throws ValidationException, OutOfStockException, Exception {
        if (quantity <= 0) {
            throw new ValidationException("Quantity harus lebih dari 0");
        }
        
        Product product = productDAO.findByKode(productKode);
        if (product == null) {
            throw new ValidationException("Produk tidak ditemukan: " + productKode);
        }
        
        // Check if item already in cart
        Optional<CartItem> existingItem = cartItems.stream()
            .filter(item -> item.getProduct().getKode().equals(productKode))
            .findFirst();
        
        if (existingItem.isPresent()) {
            int newQuantity = existingItem.get().getQuantity() + quantity;
            if (newQuantity > product.getStok()) {
                throw new OutOfStockException(product.getNama(), 
                    product.getStok(), newQuantity);
            }
            existingItem.get().setQuantity(newQuantity);
        } else {
            if (quantity > product.getStok()) {
                throw new OutOfStockException(product.getNama(), 
                    product.getStok(), quantity);
            }
            cartItems.add(new CartItem(product, quantity));
        }
    }
    
    public void updateItemQuantity(String productKode, int newQuantity) 
            throws ValidationException, OutOfStockException, Exception {
        if (newQuantity <= 0) {
            throw new ValidationException("Quantity harus lebih dari 0");
        }
        
        CartItem item = cartItems.stream()
            .filter(ci -> ci.getProduct().getKode().equals(productKode))
            .findFirst()
            .orElseThrow(() -> new ValidationException("Item tidak ada di keranjang"));
        
        Product product = productDAO.findByKode(productKode);
        if (newQuantity > product.getStok()) {
            throw new OutOfStockException(product.getNama(), 
                product.getStok(), newQuantity);
        }
        
        item.setQuantity(newQuantity);
    }
    
    public void removeItem(String productKode) {
        cartItems.removeIf(item -> item.getProduct().getKode().equals(productKode));
    }
    
    public List<CartItem> getItems() {
        return new ArrayList<>(cartItems);
    }
    
    public double getTotalAmount() {
        return cartItems.stream()
            .mapToDouble(CartItem::getSubtotal)
            .sum();
    }
    
    public int getTotalItems() {
        return cartItems.size();
    }
    
    public void clearCart() {
        cartItems.clear();
    }
    
    public boolean isEmpty() {
        return cartItems.isEmpty();
    }
}
