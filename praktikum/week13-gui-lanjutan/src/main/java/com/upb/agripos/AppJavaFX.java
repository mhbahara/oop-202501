package com.upb.agripos;

import com.upb.agripos.controller.ProductController;
import com.upb.agripos.dao.ProductDAO;
import com.upb.agripos.service.ProductService;
import com.upb.agripos.view.ProductTableView;
import javafx.application.Application;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Main Application untuk Agri-POS
 * Week 13 - GUI Lanjutan JavaFX
 * 
 * Integrasi lengkap:
 * - Week 11: DAO + JDBC
 * - Week 12: GUI JavaFX Dasar
 * - Week 13: TableView + Lambda Expression
 */
public class AppJavaFX extends Application {
    
    // Konfigurasi database PostgreSQL
    private static final String DB_URL = "jdbc:postgresql://localhost:1212/agripos_db";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "230805";
    
    private Connection connection;

    @Override
    public void start(Stage primaryStage) {
        try {
            // Inisialisasi koneksi database
            initDatabase();
            
            // Buat DAO
            ProductDAO productDAO = new ProductDAO(connection);
            productDAO.createTable(); // Buat tabel jika belum ada
            
            // Buat Service (sesuai DIP - Dependency Inversion Principle)
            ProductService productService = new ProductService(productDAO);
            
            // Buat Controller
            ProductController productController = new ProductController(productService);
            
            // Buat View (TableView)
            ProductTableView view = new ProductTableView(productController);
            
            // Tampilkan GUI
            view.show(primaryStage);
            
            System.out.println("✓ Aplikasi Agri-POS berhasil dijalankan");
            System.out.println("✓ Database terhubung: " + DB_URL);
            
        } catch (Exception e) {
            System.err.println("✗ Error saat menjalankan aplikasi:");
            e.printStackTrace();
        }
    }

    /**
     * Inisialisasi koneksi database
     */
    private void initDatabase() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("✓ Koneksi database berhasil");
        } catch (ClassNotFoundException e) {
            throw new SQLException("PostgreSQL JDBC Driver tidak ditemukan", e);
        }
    }

    @Override
    public void stop() {
        // Tutup koneksi database saat aplikasi ditutup
        if (connection != null) {
            try {
                connection.close();
                System.out.println("✓ Koneksi database ditutup");
            } catch (SQLException e) {
                System.err.println("✗ Error saat menutup koneksi: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}