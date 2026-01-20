package com.upb.agripos.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
<<<<<<< HEAD

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection;
    
    // Database configuration - SESUAIKAN dengan setup Anda
    private static final String URL = "jdbc:postgresql://localhost:1212/agripos";
    private static final String USER = "postgres";
    private static final String PASSWORD = "230805";
    
    private DatabaseConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database connection established successfully");
        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL JDBC Driver not found");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Failed to connect to database");
            e.printStackTrace();
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
    
    public Connection getConnection() {
        try {
            // Check if connection is still valid
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (SQLException e) {
            System.err.println("Failed to get database connection");
            e.printStackTrace();
        }
        return connection;
    }
    
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed");
            } catch (SQLException e) {
                System.err.println("Failed to close database connection");
                e.printStackTrace();
            }
        }
    }
    
    // Test connection
    public boolean testConnection() {
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }
}
=======
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
>>>>>>> c6a2d84756afe6ec286e66b35e0c193c781e75d5
