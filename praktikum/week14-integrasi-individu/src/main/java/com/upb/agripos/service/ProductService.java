package com.upb.agripos.service;

import com.upb.agripos.dao.ProductDAO;
import com.upb.agripos.model.Product;
import com.upb.agripos.exception.*;

import java.util.List;

/**
 * Service Layer untuk Product
 * Bab 6: SOLID Principles
 * - Single Responsibility: hanya handle business logic Product
 * - Dependency Inversion: bergantung pada interface ProductDAO
 */
public class ProductService {
    private final ProductDAO productDAO;

    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public List<Product> getAllProducts() throws DatabaseException {
        return productDAO.findAll();
    }

    public Product getProductByCode(String code) throws DatabaseException {
        return productDAO.findByCode(code);
    }

    public void addProduct(Product product) throws ValidationException, DatabaseException {
        // Validasi business logic
        validateProduct(product);
        
        // Insert ke database (DAO akan handle duplikasi)
        productDAO.insert(product);
    }

    public void updateProduct(Product product) throws ValidationException, DatabaseException {
        validateProduct(product);
        productDAO.update(product);
    }

    /**
     * Update hanya stok produk (untuk checkout)
     */
    public void updateProductStock(String code, int newStock) 
            throws ValidationException, DatabaseException {
        
        if (code == null || code.trim().isEmpty()) {
            throw new ValidationException("Kode produk tidak boleh kosong");
        }
        
        if (newStock < 0) {
            throw new ValidationException("Stok tidak boleh negatif");
        }
        
        // Ambil produk dari database
        Product product = productDAO.findByCode(code);
        if (product == null) {
            throw new ValidationException("Produk tidak ditemukan: " + code);
        }
        
        // Update stok
        product.setStock(newStock);
        productDAO.update(product);
    }

    public void deleteProduct(String code) throws ValidationException, DatabaseException {
        if (code == null || code.trim().isEmpty()) {
            throw new ValidationException("Kode produk tidak boleh kosong");
        }
        
        Product product = productDAO.findByCode(code);
        if (product == null) {
            throw new ValidationException("Produk tidak ditemukan: " + code);
        }
        
        productDAO.delete(code);
    }

    // Business validation
    private void validateProduct(Product product) throws ValidationException {
        if (product == null) {
            throw new ValidationException("Produk tidak boleh null");
        }
        
        if (product.getCode() == null || product.getCode().trim().isEmpty()) {
            throw new ValidationException("Kode produk tidak boleh kosong");
        }
        
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new ValidationException("Nama produk tidak boleh kosong");
        }
        
        if (product.getPrice() == null || product.getPrice().doubleValue() <= 0) {
            throw new ValidationException("Harga harus lebih dari 0");
        }
        
        if (product.getStock() < 0) {
            throw new ValidationException("Stok tidak boleh negatif");
        }
    }
}