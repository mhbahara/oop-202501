// ============================================================
// FILE 4: src/main/java/com/upb/agripos/config/DatabaseConnection.java
// ============================================================
package com.upb.agripos.config;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    
    // Private constructor untuk mencegah instantiasi dari luar
    private DatabaseConnection() {
        System.out.println("Database Connection Created!");
    }

    // Static method untuk mendapatkan instance tunggal
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public void connect() {
        System.out.println("Connected to database...");
    }

    public void disconnect() {
        System.out.println("Disconnected from database...");
    }
}
