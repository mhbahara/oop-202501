package com.upb.agripos.controller;

import com.upb.agripos.model.Product;
import com.upb.agripos.service.ProductService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * ProductController - Menghubungkan View dengan Service
 * Sesuai dengan Sequence Diagram Bab 6
 */
public class ProductController {
    private ProductService productService;
    private ObservableList<Product> productList;

    public ProductController(ProductService productService) {
        this.productService = productService;
        this.productList = FXCollections.observableArrayList();
        loadProducts();
    }

    /**
     * Tambah produk baru
     * Dipanggil oleh event handler tombol Tambah
     */
    public String addProduct(String code, String name, String priceStr, String stockStr) {
        try {
            // Parse input
            double price = Double.parseDouble(priceStr);
            int stock = Integer.parseInt(stockStr);

            // Buat objek Product
            Product product = new Product(code, name, price, stock);

            // Panggil service (yang akan memanggil DAO)
            boolean success = productService.insert(product);

            if (success) {
                productList.add(product);
                return "Produk berhasil ditambahkan!";
            } else {
                return "Gagal menambahkan produk ke database";
            }
        } catch (NumberFormatException e) {
            return "Error: Harga dan Stok harus berupa angka";
        } catch (IllegalArgumentException e) {
            return "Error: " + e.getMessage();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    /**
     * Load semua produk dari database
     */
    public void loadProducts() {
        productList.clear();
        productList.addAll(productService.getAllProducts());
    }

    /**
     * Get observable list untuk binding ke UI
     */
    public ObservableList<Product> getProductList() {
        return productList;
    }

    /**
     * Refresh data dari database
     */
    public void refreshProducts() {
        loadProducts();
    }
}