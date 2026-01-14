package com.upb.agripos.dao;

import com.upb.agripos.model.Product;
import com.upb.agripos.exception.DatabaseException;
import com.upb.agripos.util.DatabaseConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Bab 11: JDBC Implementation of ProductDAO
 */
public class JdbcProductDAO implements ProductDAO {

    @Override
    public List<Product> findAll() throws DatabaseException {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products ORDER BY id";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                products.add(mapResultSetToProduct(rs));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error fetching all products", e);
        }

        return products;
    }

    @Override
    public Product findByCode(String code) throws DatabaseException {
        String sql = "SELECT * FROM products WHERE code = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, code);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToProduct(rs);
            }
            return null;

        } catch (SQLException e) {
            throw new DatabaseException("Error finding product by code: " + code, e);
        }
    }

    @Override
    public void insert(Product product) throws DatabaseException {
        String sql = "INSERT INTO products (code, name, price, stock) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, product.getCode());
            pstmt.setString(2, product.getName());
            pstmt.setBigDecimal(3, product.getPrice());
            pstmt.setInt(4, product.getStock());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            if (e.getSQLState().equals("23505")) { // Unique violation
                throw new DatabaseException("Kode produk sudah ada: " + product.getCode());
            }
            throw new DatabaseException("Error inserting product", e);
        }
    }

    @Override
    public void update(Product product) throws DatabaseException {
        String sql = "UPDATE products SET name = ?, price = ?, stock = ?, updated_at = CURRENT_TIMESTAMP WHERE code = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, product.getName());
            pstmt.setBigDecimal(2, product.getPrice());
            pstmt.setInt(3, product.getStock());
            pstmt.setString(4, product.getCode());

            int affected = pstmt.executeUpdate();
            if (affected == 0) {
                throw new DatabaseException("Produk tidak ditemukan: " + product.getCode());
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error updating product", e);
        }
    }

    @Override
    public void delete(String code) throws DatabaseException {
        String sql = "DELETE FROM products WHERE code = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, code);
            int affected = pstmt.executeUpdate();

            if (affected == 0) {
                throw new DatabaseException("Produk tidak ditemukan: " + code);
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error deleting product", e);
        }
    }

    private Product mapResultSetToProduct(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setId(rs.getInt("id"));
        product.setCode(rs.getString("code"));
        product.setName(rs.getString("name"));
        product.setPrice(rs.getBigDecimal("price"));
        product.setStock(rs.getInt("stock"));
        product.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        product.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
        return product;
    }
}