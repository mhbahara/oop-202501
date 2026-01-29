package com.upb.agripos.controller;

import com.upb.agripos.model.Product;
import com.upb.agripos.service.ProductService;
import com.upb.agripos.service.CartService;
import com.upb.agripos.exception.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * Controller - menghubungkan View dengan Service
 * Bab 6: SOLID - Dependency Inversion Principle (DIP)
 * Controller tidak langsung akses DAO, harus lewat Service
 */
public class PosController {
    private final ProductService productService;
    private final CartService cartService;

    public PosController(ProductService productService, CartService cartService) {
        this.productService = productService;
        this.cartService = cartService;
    }

    // ===== Product Operations =====
    
    public List<Product> loadProducts() throws DatabaseException {
        return productService.getAllProducts();
    }

    public void addProduct(String code, String name, double price, int stock) 
            throws ValidationException, DatabaseException {
        
        Product product = new Product();
        product.setCode(code);
        product.setName(name);
        product.setPrice(BigDecimal.valueOf(price));
        product.setStock(stock);
        
        productService.addProduct(product);
    }

    public void deleteProduct(String code) throws ValidationException, DatabaseException {
        productService.deleteProduct(code);
    }

    /**
     * Update stok produk
     * Dipanggil saat checkout untuk mengurangi stok
     */
    public void updateProductStock(String code, int newStock) 
            throws ValidationException, DatabaseException {
        productService.updateProductStock(code, newStock);
    }

    // ===== Cart Operations =====
    
    public void addToCart(Product product, int quantity) 
            throws ValidationException, InsufficientStockException {
        cartService.addToCart(product, quantity);
    }

    public void removeFromCart(String productCode) {
        cartService.removeFromCart(productCode);
    }

    public void clearCart() {
        cartService.clearCart();
    }

    public BigDecimal getCartTotal() {
        return cartService.getCart().calculateTotal();
    }

    public int getCartItemCount() {
        return cartService.getCart().getTotalItems();
    }

    public CartService getCartService() {
        return cartService;
    }
}