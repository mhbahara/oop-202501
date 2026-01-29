package com.upb.agripos;

import java.sql.Connection;
import java.sql.DriverManager;
import com.upb.agripos.dao.ProductDAO;
import com.upb.agripos.dao.ProductDAOImpl;
import com.upb.agripos.model.Product;
import java.util.List;

public class MainDAOTest {
    
    // Database Connection Configuration
    private static final String DB_URL = "jdbc:postgresql://localhost:1212/agripos";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "230805";
    
    public static void main(String[] args) {
        Connection conn = null;
        
        try {
            // Establish database connection
            System.out.println("=================================================");
            System.out.println("   AGRIPOS - DAO DATABASE TEST");
            System.out.println("=================================================");
            System.out.println("Connecting to database...");
            
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("✓ Database connection successful!");
            System.out.println();
            
            // Create DAO instance
            ProductDAO dao = new ProductDAOImpl(conn);
            
            // ===== TEST 1: INSERT =====
            System.out.println("--- TEST 1: INSERT ---");
            Product p1 = new Product("P01", "Pupuk Organik", 25000, 10);
            Product p2 = new Product("P02", "Pestisida Alami", 35000, 15);
            Product p3 = new Product("P03", "Bibit Jagung", 15000, 50);
            
            dao.insert(p1);
            dao.insert(p2);
            dao.insert(p3);
            System.out.println("✓ 3 products inserted successfully");
            System.out.println();
            
            // ===== TEST 2: FIND BY CODE =====
            System.out.println("--- TEST 2: FIND BY CODE ---");
            Product found = dao.findByCode("P01");
            if (found != null) {
                System.out.println("✓ Product found:");
                System.out.println("  Code  : " + found.getCode());
                System.out.println("  Name  : " + found.getName());
                System.out.println("  Price : Rp " + found.getPrice());
                System.out.println("  Stock : " + found.getStock());
            } else {
                System.out.println("✗ Product not found");
            }
            System.out.println();
            
            // ===== TEST 3: FIND ALL =====
            System.out.println("--- TEST 3: FIND ALL ---");
            List<Product> allProducts = dao.findAll();
            System.out.println("✓ Total products: " + allProducts.size());
            System.out.println();
            for (Product p : allProducts) {
                System.out.printf("  %-6s | %-25s | Rp %10.0f | Stock: %3d%n",
                    p.getCode(), p.getName(), p.getPrice(), p.getStock());
            }
            System.out.println();
            
            // ===== TEST 4: UPDATE =====
            System.out.println("--- TEST 4: UPDATE ---");
            Product updatedProduct = new Product("P01", "Pupuk Organik Premium", 30000, 8);
            dao.update(updatedProduct);
            System.out.println("✓ Product P01 updated successfully");
            
            Product afterUpdate = dao.findByCode("P01");
            System.out.println("  New name : " + afterUpdate.getName());
            System.out.println("  New price: Rp " + afterUpdate.getPrice());
            System.out.println("  New stock: " + afterUpdate.getStock());
            System.out.println();
            
            // ===== TEST 5: DELETE =====
            System.out.println("--- TEST 5: DELETE ---");
            dao.delete("P03");
            System.out.println("✓ Product P03 deleted successfully");
            
            List<Product> afterDelete = dao.findAll();
            System.out.println("  Remaining products: " + afterDelete.size());
            System.out.println();
            
            // ===== CLEANUP (Optional) =====
            System.out.println("--- CLEANUP ---");
            dao.delete("P01");
            dao.delete("P02");
            System.out.println("✓ All test data cleaned up");
            
            System.out.println();
            System.out.println("=================================================");
            System.out.println("   ALL TESTS COMPLETED SUCCESSFULLY!");
            System.out.println("=================================================");
            
        } catch (Exception e) {
            System.err.println("❌ ERROR: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Close connection
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                    System.out.println("\n✓ Database connection closed");
                }
            } catch (Exception e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}