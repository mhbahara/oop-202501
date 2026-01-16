package com.upb.agripos.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/agripos_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";
    
    private static Connection connection = null;
    
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            } catch (ClassNotFoundException e) {
                throw new SQLException("MySQL Driver tidak ditemukan", e);
            }
        }
        return connection;
    }
    
    public static void initializeDatabase() throws SQLException {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            
            // Buat tabel users
            String createUsersTable = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "username VARCHAR(50) UNIQUE NOT NULL," +
                    "password VARCHAR(100) NOT NULL," +
                    "full_name VARCHAR(100) NOT NULL," +
                    "role ENUM('ADMIN', 'KASIR') NOT NULL," +
                    "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                    ")";
            stmt.executeUpdate(createUsersTable);
            
            // Insert default users jika belum ada
            String checkAdmin = "SELECT COUNT(*) FROM users WHERE username = 'admin'";
            if (!stmt.executeQuery(checkAdmin).next() || stmt.getResultSet().getInt(1) == 0) {
                String insertAdmin = "INSERT INTO users (username, password, full_name, role) " +
                        "VALUES ('admin', 'admin123', 'Administrator', 'ADMIN')";
                stmt.executeUpdate(insertAdmin);
            }
            
            String checkKasir = "SELECT COUNT(*) FROM users WHERE username = 'kasir'";
            if (!stmt.executeQuery(checkKasir).next() || stmt.getResultSet().getInt(1) == 0) {
                String insertKasir = "INSERT INTO users (username, password, full_name, role) " +
                        "VALUES ('kasir', 'kasir123', 'Kasir 1', 'KASIR')";
                stmt.executeUpdate(insertKasir);
            }
            
            System.out.println("Database sudah diinisialisasi");
        }
    }
    
    public static void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
