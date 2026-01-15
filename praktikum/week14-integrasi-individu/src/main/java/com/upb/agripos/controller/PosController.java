package com.upb.agripos.controller;

import java.util.List;

import com.upb.agripos.dao.JdbcProductDAO;
import com.upb.agripos.model.Product;
import com.upb.agripos.service.CartService;
import com.upb.agripos.service.ProductService;

public class PosController {

    private ProductService productService; 
    private CartService cartService;

    public PosController() {
        this.productService = new ProductService(JdbcProductDAO.getInstance());
        this.cartService = new CartService();
    }

    // ========================
    // Getter untuk PosView
    // ========================
    public ProductService getProductService() {
        return productService;
    }

    public CartService getCartService() {
        return cartService;
    }

    // ========================
    // Method lama (opsional)
    // ========================
    public List<Product> loadProducts() {
        return productService.getAllProducts();
    }

    public void addProduct(Product product) {
        productService.addProduct(product);
    }

    public void deleteProduct(String code) {
        productService.deleteProduct(code);
    }

    public void addToCart(Product product) {
        cartService.addToCart(product, 1);
    }

    public double getCartTotal() {
        return cartService.getTotal();
    }
}
