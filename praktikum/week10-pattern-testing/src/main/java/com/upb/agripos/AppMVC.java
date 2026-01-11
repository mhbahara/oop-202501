// ============================================================
// FILE 5: src/main/java/com/upb/agripos/AppMVC.java
// ============================================================
package com.upb.agripos;

import com.upb.agripos.model.Product;
import com.upb.agripos.view.ConsoleView;
import com.upb.agripos.controller.ProductController;
import com.upb.agripos.config.DatabaseConnection;

public class AppMVC {
    public static void main(String[] args) {
        // GANTI dengan Nama dan NIM Anda
        System.out.println("Hello, I am [Rafi Kurniawan]-[240202878] (Week10)");
        System.out.println("===========================================\n");

        // Demonstrasi Singleton Pattern
        System.out.println("=== SINGLETON PATTERN DEMO ===");
        DatabaseConnection db1 = DatabaseConnection.getInstance();
        DatabaseConnection db2 = DatabaseConnection.getInstance();
        
        System.out.println("db1 == db2? " + (db1 == db2)); // Should be true
        db1.connect();
        System.out.println();

        // Demonstrasi MVC Pattern
        System.out.println("=== MVC PATTERN DEMO ===");
        Product product = new Product("P01", "Pupuk Organik");
        ConsoleView view = new ConsoleView();
        ProductController controller = new ProductController(product, view);
        
        controller.showProduct();
        
        // Contoh produk lain
        Product product2 = new Product("P02", "Benih Jagung Hibrida");
        ProductController controller2 = new ProductController(product2, view);
        controller2.showProduct();
        
        System.out.println();
        db1.disconnect();
    }
}