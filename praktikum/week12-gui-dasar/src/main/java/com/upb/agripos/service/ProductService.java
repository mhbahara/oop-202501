package com.upb.agripos.service;

import com.upb.agripos.dao.ProductDAO;
import com.upb.agripos.model.Product;
import java.util.List;

/**
 * ProductService - Business Logic Layer
 * Menerapkan DIP: View/Controller tidak langsung akses DAO
 */
public class ProductService {
    private ProductDAO productDAO;

    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    /**
     * Tambah produk baru
     * Validasi dilakukan di sini
     */
    public boolean insert(Product product) throws IllegalArgumentException {
        // Validasi input
        if (product.getCode() == null || product.getCode().trim().isEmpty()) {
            throw new IllegalArgumentException("Kode produk tidak boleh kosong");
        }
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Nama produk tidak boleh kosong");
        }
        if (product.getPrice() <= 0) {
            throw new IllegalArgumentException("Harga harus lebih dari 0");
        }
        if (product.getStock() < 0) {
            throw new IllegalArgumentException("Stok tidak boleh negatif");
        }

        // Cek duplikasi kode
        Product existing = productDAO.findByCode(product.getCode());
        if (existing != null) {
            throw new IllegalArgumentException("Kode produk sudah ada: " + product.getCode());
        }

        return productDAO.insert(product);
    }

    /**
     * Ambil semua produk
     */
    public List<Product> getAllProducts() {
        return productDAO.findAll();
    }

    /**
     * Cari produk berdasarkan kode
     */
    public Product getProductByCode(String code) {
        return productDAO.findByCode(code);
    }

    /**
     * Update produk
     */
    public boolean update(Product product) throws IllegalArgumentException {
        // Validasi
        if (product.getCode() == null || product.getCode().trim().isEmpty()) {
            throw new IllegalArgumentException("Kode produk tidak boleh kosong");
        }
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Nama produk tidak boleh kosong");
        }
        if (product.getPrice() <= 0) {
            throw new IllegalArgumentException("Harga harus lebih dari 0");
        }
        if (product.getStock() < 0) {
            throw new IllegalArgumentException("Stok tidak boleh negatif");
        }

        return productDAO.update(product);
    }

    /**
     * Hapus produk
     */
    public boolean delete(String code) {
        return productDAO.delete(code);
    }
}