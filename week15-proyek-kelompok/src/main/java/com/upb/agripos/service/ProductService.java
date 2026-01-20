package com.upb.agripos.service;

import com.upb.agripos.dao.ProductDAO;
import com.upb.agripos.dao.ProductDAOImpl;
import com.upb.agripos.exception.ValidationException;
import com.upb.agripos.model.Product;
import java.util.List;

public class ProductService {
    private ProductDAO productDAO;
    
    public ProductService() {
        this.productDAO = new ProductDAOImpl();
    }
    
    public void addProduct(Product product) throws ValidationException, Exception {
        validateProduct(product);
        
        // Check if kode already exists
        Product existing = productDAO.findByKode(product.getKode());
        if (existing != null) {
            throw new ValidationException("Kode produk sudah ada: " + product.getKode());
        }
        
        productDAO.save(product);
    }
    
    public void updateProduct(Product product) throws ValidationException, Exception {
        validateProduct(product);
        productDAO.update(product);
    }
    
    public void deleteProduct(String kode) throws Exception {
        Product product = productDAO.findByKode(kode);
        if (product == null) {
            throw new ValidationException("Produk tidak ditemukan: " + kode);
        }
        productDAO.delete(kode);
    }
    
    public Product getProductByKode(String kode) throws Exception {
        return productDAO.findByKode(kode);
    }
    
    public Product getProductById(Integer id) throws Exception {
        return productDAO.findById(id);
    }
    
    public List<Product> getAllProducts() throws Exception {
        return productDAO.findAll();
    }
    
    public void updateStock(String kode, int newStock) throws Exception {
        if (newStock < 0) {
            throw new ValidationException("Stok tidak boleh negatif");
        }
        productDAO.updateStock(kode, newStock);
    }
    
    private void validateProduct(Product product) throws ValidationException {
        if (product.getKode() == null || product.getKode().trim().isEmpty()) {
            throw new ValidationException("Kode produk harus diisi");
        }
        if (product.getNama() == null || product.getNama().trim().isEmpty()) {
            throw new ValidationException("Nama produk harus diisi");
        }
        if (product.getKategori() == null || product.getKategori().trim().isEmpty()) {
            throw new ValidationException("Kategori produk harus diisi");
        }
        if (product.getHarga() == null || product.getHarga() <= 0) {
            throw new ValidationException("Harga produk harus lebih dari 0");
        }
        if (product.getStok() == null || product.getStok() < 0) {
            throw new ValidationException("Stok produk tidak boleh negatif");
        }
    }
}