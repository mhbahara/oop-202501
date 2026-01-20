package com.upb.agripos.dao;

import com.upb.agripos.model.Product;
import com.upb.agripos.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    private Connection getConnection() {
        return DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public void save(Product product) throws Exception {
        String sql = "INSERT INTO products (kode, nama, kategori, harga, stok) VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, product.getKode());
            stmt.setString(2, product.getNama());
            stmt.setString(3, product.getKategori());
            stmt.setDouble(4, product.getHarga());
            stmt.setInt(5, product.getStok());
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        product.setId(generatedKeys.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new Exception("Failed to save product: " + e.getMessage(), e);
        }
    }

    @Override
    public void update(Product product) throws Exception {
        String sql = "UPDATE products SET nama = ?, kategori = ?, harga = ?, stok = ? WHERE kode = ?";
        
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, product.getNama());
            stmt.setString(2, product.getKategori());
            stmt.setDouble(3, product.getHarga());
            stmt.setInt(4, product.getStok());
            stmt.setString(5, product.getKode());
            
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new Exception("Product not found with kode: " + product.getKode());
            }
        } catch (SQLException e) {
            throw new Exception("Failed to update product: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(String kode) throws Exception {
        String sql = "DELETE FROM products WHERE kode = ?";
        
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, kode);
            
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new Exception("Product not found with kode: " + kode);
            }
        } catch (SQLException e) {
            throw new Exception("Failed to delete product: " + e.getMessage(), e);
        }
    }

    @Override
    public Product findByKode(String kode) throws Exception {
        String sql = "SELECT * FROM products WHERE kode = ?";
        
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, kode);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToProduct(rs);
                }
            }
        } catch (SQLException e) {
            throw new Exception("Failed to find product: " + e.getMessage(), e);
        }
        
        return null;
    }

    @Override
    public Product findById(Integer id) throws Exception {
        String sql = "SELECT * FROM products WHERE id = ?";
        
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setInt(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToProduct(rs);
                }
            }
        } catch (SQLException e) {
            throw new Exception("Failed to find product: " + e.getMessage(), e);
        }
        
        return null;
    }

    @Override
    public List<Product> findAll() throws Exception {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products ORDER BY kode";
        
        try (Statement stmt = getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                products.add(mapResultSetToProduct(rs));
            }
        } catch (SQLException e) {
            throw new Exception("Failed to retrieve products: " + e.getMessage(), e);
        }
        
        return products;
    }

    @Override
    public void updateStock(String kode, int newStock) throws Exception {
        String sql = "UPDATE products SET stok = ? WHERE kode = ?";
        
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setInt(1, newStock);
            stmt.setString(2, kode);
            
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new Exception("Product not found with kode: " + kode);
            }
        } catch (SQLException e) {
            throw new Exception("Failed to update stock: " + e.getMessage(), e);
        }
    }

    private Product mapResultSetToProduct(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setId(rs.getInt("id"));
        product.setKode(rs.getString("kode"));
        product.setNama(rs.getString("nama"));
        product.setKategori(rs.getString("kategori"));
        product.setHarga(rs.getDouble("harga"));
        product.setStok(rs.getInt("stok"));
        product.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        product.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
        return product;
    }
}