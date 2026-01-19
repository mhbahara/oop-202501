package com.upb.agripos;

import com.upb.agripos.controller.ProductController;
import com.upb.agripos.dao.ProductDAO;
import com.upb.agripos.dao.ProductDAOImpl;
import com.upb.agripos.service.ProductService;
import com.upb.agripos.view.ProductTableView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppJavaFX extends Application {

    @Override
    public void start(Stage stage) {

        // ===== Inisialisasi Layer Backend =====
        ProductDAO productDAO = new ProductDAOImpl();
        ProductService productService = new ProductService(productDAO);

        // ===== Inisialisasi View =====
        ProductTableView view = new ProductTableView();

        // ===== Inisialisasi Controller =====
        new ProductController(productService, view);

        // ===== Scene & Stage =====
        Scene scene = new Scene(view, 700, 450);
        stage.setTitle("Agri-POS | Manajemen Produk");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
