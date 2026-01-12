package com.upb.agripos.controller;

import com.upb.agripos.model.Product;
import com.upb.agripos.service.ProductService;
import javafx.scene.control.Alert;
import java.sql.SQLException;
import java.util.List;

/**
 * Controller untuk mengelola interaksi View dan Service
 * Sesuai Sequence Diagram: View → Controller → Service → DAO → DB
 * Week 13 - GUI Lanjutan JavaFX
 */
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Load semua produk untuk TableView
     * Sesuai UC-02 Lihat Daftar Produk
     * Dipanggil oleh View saat init dan reload
     */
    public List<Product> load() {
        try {
            return productService.findAll();
        } catch (SQLException e) {
            showError("Error Loading Data", "Gagal memuat data produk: " + e.getMessage());
            return null;
        }
    }

    /**
     * Tambah produk baru
     * Dipanggil oleh event handler tombol "Tambah Produk"
     */
    public boolean addProduct(String code, String name, String priceStr, String stockStr) {
        try {
            // Parse input
            double price = Double.parseDouble(priceStr);
            int stock = Integer.parseInt(stockStr);
            
            // Buat objek Product
            Product product = new Product(code, name, price, stock);
            
            // Panggil service
            productService.addProduct(product);
            
            showInfo("Sukses", "Produk berhasil ditambahkan");
            return true;
            
        } catch (NumberFormatException e) {
            showError("Input Error", "Harga dan stok harus berupa angka");
            return false;
        } catch (IllegalArgumentException e) {
            showError("Validasi Error", e.getMessage());
            return false;
        } catch (SQLException e) {
            showError("Database Error", "Gagal menambah produk: " + e.getMessage());
            return false;
        }
    }

    /**
     * Hapus produk
     * Sesuai UC-03 Hapus Produk dan Sequence Diagram SD-02
     * Dipanggil oleh event handler tombol "Hapus Produk"
     */
    public boolean delete(String code) {
        try {
            productService.delete(code);
            showInfo("Sukses", "Produk berhasil dihapus");
            return true;
            
        } catch (IllegalArgumentException e) {
            showError("Validasi Error", e.getMessage());
            return false;
        } catch (SQLException e) {
            showError("Database Error", "Gagal menghapus produk: " + e.getMessage());
            return false;
        }
    }

    /**
     * Update produk
     */
    public boolean update(Product product) {
        try {
            productService.update(product);
            showInfo("Sukses", "Produk berhasil diupdate");
            return true;
            
        } catch (IllegalArgumentException e) {
            showError("Validasi Error", e.getMessage());
            return false;
        } catch (SQLException e) {
            showError("Database Error", "Gagal mengupdate produk: " + e.getMessage());
            return false;
        }
    }

    /**
     * Tampilkan pesan error
     */
    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Tampilkan pesan info
     */
    private void showInfo(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}