package com.upb.agripos.service;

import com.upb.agripos.dao.ProductDAO;
import com.upb.agripos.model.Product;
import java.sql.SQLException;
import java.util.List;

/**
 * Service layer untuk logika bisnis produk
 * Menerapkan DIP: View tidak langsung akses DAO
 * Week 13 - GUI Lanjutan JavaFX
 */
public class ProductService {
    private ProductDAO productDAO;

    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    /**
     * Mengambil semua produk
     * Dipanggil oleh Controller untuk mengisi TableView
     * Sesuai UC-02 Lihat Daftar Produk
     */
    public List<Product> findAll() throws SQLException {
        return productDAO.findAll();
    }

    /**
     * Menambah produk baru
     * Validasi dilakukan di layer ini
     */
    public void addProduct(Product product) throws SQLException {
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
        
        // Cek apakah kode sudah ada
        Product existing = productDAO.findByCode(product.getCode());
        if (existing != null) {
            throw new IllegalArgumentException("Kode produk sudah digunakan");
        }
        
        productDAO.insert(product);
    }

    /**
     * Menghapus produk berdasarkan kode
     * Sesuai UC-03 Hapus Produk
     */
    public void delete(String code) throws SQLException {
        if (code == null || code.trim().isEmpty()) {
            throw new IllegalArgumentException("Kode produk tidak boleh kosong");
        }
        
        // Cek apakah produk ada
        Product product = productDAO.findByCode(code);
        if (product == null) {
            throw new IllegalArgumentException("Produk tidak ditemukan");
        }
        
        productDAO.delete(code);
    }

    /**
     * Update produk
     */
    public void update(Product product) throws SQLException {
        // Validasi
        if (product.getCode() == null || product.getCode().trim().isEmpty()) {
            throw new IllegalArgumentException("Kode produk tidak boleh kosong");
        }
        
        // Cek apakah produk ada
        Product existing = productDAO.findByCode(product.getCode());
        if (existing == null) {
            throw new IllegalArgumentException("Produk tidak ditemukan");
        }
        
        productDAO.update(product);
    }

    /**
     * Cari produk berdasarkan kode
     */
    public Product findByCode(String code) throws SQLException {
        return productDAO.findByCode(code);
    }

    /**
     * Hitung total produk
     */
    public int count() throws SQLException {
        return productDAO.count();
    }
}