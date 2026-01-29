package com.upb.agripos.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DatabaseConfig - Konfigurasi koneksi PostgreSQL
 */
public class DatabaseConfig {
    private static final String URL = "jdbc:postgresql://localhost:1212/agripos_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "230805";

    /**
     * Get database connection
     */
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("PostgreSQL JDBC Driver tidak ditemukan", e);
        }
    }

    /**
     * Close connection
     */
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}