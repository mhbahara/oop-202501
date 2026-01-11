package com.upb.agripos.dao;

import com.upb.agripos.model.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ProductDAO - Data Access Object untuk Product
 * Menggunakan PostgreSQL (dari Pertemuan 11)
 */
public class ProductDAO {
    private Connection connection;

    public ProductDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * Insert produk baru ke database
     */
    public boolean insert(Product product) {
        String sql = "INSERT INTO products (code, name, price, stock) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, product.getCode());
            stmt.setString(2, product.getName());
            stmt.setDouble(3, product.getPrice());
            stmt.setInt(4, product.getStock());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error insert product: " + e.getMessage());
            return false;
        }
    }

    /**
     * Ambil semua produk dari database
     */
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT code, name, price, stock FROM products";
        
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Product p = new Product(
                    rs.getString("code"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getInt("stock")
                );
                products.add(p);
            }
        } catch (SQLException e) {
            System.err.println("Error findAll products: " + e.getMessage());
        }
        
        return products;
    }

    /**
     * Cari produk berdasarkan kode
     */
    public Product findByCode(String code) {
        String sql = "SELECT code, name, price, stock FROM products WHERE code = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, code);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new Product(
                    rs.getString("code"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getInt("stock")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error findByCode: " + e.getMessage());
        }
        return null;
    }

    /**
     * Update produk
     */
    public boolean update(Product product) {
        String sql = "UPDATE products SET name = ?, price = ?, stock = ? WHERE code = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());
            stmt.setInt(3, product.getStock());
            stmt.setString(4, product.getCode());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error update product: " + e.getMessage());
            return false;
        }
    }

    /**
     * Delete produk berdasarkan kode
     */
    public boolean delete(String code) {
        String sql = "DELETE FROM products WHERE code = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, code);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error delete product: " + e.getMessage());
            return false;
        }
    }
}