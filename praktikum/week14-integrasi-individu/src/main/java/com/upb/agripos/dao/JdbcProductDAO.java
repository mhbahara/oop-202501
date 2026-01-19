package com.upb.agripos.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.upb.agripos.model.Product;

public class JdbcProductDAO implements ProductDAO {

    private static JdbcProductDAO instance;
    private Connection connection;

    private JdbcProductDAO() {
        try {
            connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/agripos",
                "postgres",
                "postgres"
            );
        } catch (SQLException e) {
            throw new RuntimeException("Koneksi database gagal", e);
        }
    }

    public static JdbcProductDAO getInstance() {
        if (instance == null) {
            instance = new JdbcProductDAO();
        }
        return instance;
    }

    // ================== SELECT ==================
    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();

        String sql = "SELECT code, name, price, stock FROM products";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Product product = new Product(
                        rs.getString("code"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("stock")
                );
                products.add(product);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Gagal mengambil data produk", e);
        }

        return products;
    }

    // ================== INSERT ==================
    @Override
    public void insert(Product product) {
        String sql =
            "INSERT INTO products (code, name, price, stock) VALUES (?,?,?,?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, product.getCode());
            ps.setString(2, product.getName());
            ps.setDouble(3, product.getPrice());
            ps.setInt(4, product.getStock());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Gagal insert produk", e);
        }
    }

    // ================== DELETE ==================
    @Override
    public void delete(String code) {
        String sql = "DELETE FROM products WHERE code = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, code);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Gagal delete produk", e);
        }
    }
}
