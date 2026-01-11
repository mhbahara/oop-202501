package com.upb.agripos.config;

public class DatabaseConnection {

    private static DatabaseConnection instance;

    // constructor private
    private DatabaseConnection() {
        System.out.println("Koneksi database dibuat.");
    }

    // global access point
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
}
