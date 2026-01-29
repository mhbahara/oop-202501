package com.upb.agripos;

import com.upb.agripos.controller.PosController;
import com.upb.agripos.dao.JdbcProductDAO;
import com.upb.agripos.dao.ProductDAO;
import com.upb.agripos.service.CartService;
import com.upb.agripos.service.ProductService;
import com.upb.agripos.util.DatabaseConnection;
import com.upb.agripos.view.PosView;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 * Main Application Class
 * Bab 1: Identitas praktikum
 * Bab 6: Dependency Injection - semua dependency di-wire di sini
 */
public class AppJavaFX extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Bab 1: Identitas
        System.out.println("=".repeat(50));
        System.out.println("Hello World, I am [Rafi Kurrniawan]-[240202878]");
        System.out.println("Agri-POS - Point of Sale Pertanian");
        System.out.println("=".repeat(50));

        // Test database connection
        if (!DatabaseConnection.testConnection()) {
            showErrorAndExit("Tidak dapat terhubung ke database!\n" +
                    "Pastikan PostgreSQL berjalan dan database 'agripos_db' sudah dibuat.");
            return;
        }

        System.out.println("✓ Database connection successful");

        // Dependency Injection (DIP dari SOLID)
        ProductDAO productDAO = new JdbcProductDAO();
        ProductService productService = new ProductService(productDAO);
        CartService cartService = new CartService();
        PosController controller = new PosController(productService, cartService);

        // Launch GUI
        PosView view = new PosView(controller, primaryStage);
        view.show();

        System.out.println("✓ Application started successfully\n");
    }

    private void showErrorAndExit(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Startup Failed");
        alert.setContentText(message);
        alert.showAndWait();
        System.exit(1);
    }

    public static void main(String[] args) {
        launch(args);
    }
}