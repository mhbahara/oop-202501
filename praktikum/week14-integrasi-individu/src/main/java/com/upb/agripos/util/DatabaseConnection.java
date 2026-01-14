package com.upb.agripos.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Bab 10: Singleton Pattern untuk Database Connection
 * Memastikan hanya ada satu konfigurasi koneksi
 */
public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:1212/agripos_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "230805";

    private static DatabaseConnection instance;

    // Private constructor untuk Singleton
    private DatabaseConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("PostgreSQL Driver loaded successfully");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("PostgreSQL Driver not found", e);
        }
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnection.class) {
                if (instance == null) {
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }

    public static Connection getConnection() throws SQLException {
        getInstance(); // Ensure driver is loaded
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Test connection
    public static boolean testConnection() {
        try (Connection conn = getConnection()) {
            return conn != null && !conn.isClosed();
        } catch (SQLException e) {
            System.err.println("Connection test failed: " + e.getMessage());
            return false;
        }
    }
}