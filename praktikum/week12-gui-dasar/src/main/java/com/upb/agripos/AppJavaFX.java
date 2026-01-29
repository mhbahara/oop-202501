package com.upb.agripos;

import com.upb.agripos.config.DatabaseConfig;
import com.upb.agripos.controller.ProductController;
import com.upb.agripos.dao.ProductDAO;
import com.upb.agripos.service.ProductService;
import com.upb.agripos.view.ProductFormView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;

/**
 * AppJavaFX - Main Application
 * Menerapkan Dependency Injection untuk memenuhi SOLID (DIP)
 */
public class AppJavaFX extends Application {
    
    private Connection connection;

    @Override
    public void start(Stage primaryStage) {
        try {
            // Setup Database Connection
            connection = DatabaseConfig.getConnection();
            System.out.println("Database connected successfully!");

            // Dependency Injection (Bottom-Up)
            // DAO → Service → Controller → View
            ProductDAO productDAO = new ProductDAO(connection);
            ProductService productService = new ProductService(productDAO);
            ProductController productController = new ProductController(productService);
            
            // Create View
            ProductFormView view = new ProductFormView(productController);

            // Setup Scene and Stage
            Scene scene = new Scene(view, 600, 600);
            primaryStage.setTitle("Agri-POS - GUI Dasar JavaFX");
            primaryStage.setScene(scene);
            primaryStage.setOnCloseRequest(event -> cleanup());
            primaryStage.show();

        } catch (Exception e) {
            System.err.println("Error starting application: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Cleanup saat aplikasi ditutup
     */
    private void cleanup() {
        DatabaseConfig.closeConnection(connection);
        System.out.println("Application closed. Database connection closed.");
    }

    public static void main(String[] args) {
        launch(args);
    }
}