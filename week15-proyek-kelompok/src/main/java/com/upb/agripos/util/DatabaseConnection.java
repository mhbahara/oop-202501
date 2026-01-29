// ========== DatabaseConnection.java (Singleton Pattern) ==========
package com.upb.agripos.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection;
    
    // Database configuration - SESUAIKAN dengan setup Anda
    // IMPORTANT: Ganti port dan password sesuai PostgreSQL Anda
    // Cek di pgAdmin: port berapa yang dipakai
    private static final String URL = "jdbc:postgresql://localhost:1212/agripos"; // Default port 1212
    private static final String USER = "postgres";
    private static final String PASSWORD = "230805"; // Ganti dengan password pgAdmin Anda
    
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