package com.upb.agripos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Simple class to test database connection
 * Run this first to verify database connectivity
 */
public class TestConnection {
    
    private static final String DB_URL = "jdbc:postgresql://localhost:1212/agripos";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "230805";
    
    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("   DATABASE CONNECTION TEST");
        System.out.println("=================================================");
        System.out.println();
        
        System.out.println("Connection Details:");
        System.out.println("  URL      : " + DB_URL);
        System.out.println("  Username : " + DB_USER);
        System.out.println("  Password : " + "*".repeat(DB_PASSWORD.length()));
        System.out.println();
        
        Connection conn = null;
        
        try {
            // Test 1: Connect to database
            System.out.println("[1] Attempting to connect to database...");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("    ✓ Connection successful!");
            System.out.println();
            
            // Test 2: Check database info
            System.out.println("[2] Retrieving database information...");
            String dbName = conn.getCatalog();
            String dbVersion = conn.getMetaData().getDatabaseProductVersion();
            System.out.println("    ✓ Database Name: " + dbName);
            System.out.println("    ✓ PostgreSQL Version: " + dbVersion);
            System.out.println();
            
            // Test 3: Check if products table exists
            System.out.println("[3] Checking if 'products' table exists...");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                "SELECT EXISTS (SELECT FROM information_schema.tables " +
                "WHERE table_name = 'products')"
            );
            
            if (rs.next() && rs.getBoolean(1)) {
                System.out.println("    ✓ Table 'products' exists");
                
                // Count records
                ResultSet countRs = stmt.executeQuery("SELECT COUNT(*) FROM products");
                if (countRs.next()) {
                    int count = countRs.getInt(1);
                    System.out.println("    ✓ Total records: " + count);
                }
                countRs.close();
            } else {
                System.out.println("    ✗ Table 'products' does NOT exist");
                System.out.println("    → Please create the table first!");
                System.out.println();
                System.out.println("    SQL to create table:");
                System.out.println("    CREATE TABLE products (");
                System.out.println("        code VARCHAR(10) PRIMARY KEY,");
                System.out.println("        name VARCHAR(100),");
                System.out.println("        price DOUBLE PRECISION,");
                System.out.println("        stock INT");
                System.out.println("    );");
            }
            
            rs.close();
            stmt.close();
            System.out.println();
            
            // Success
            System.out.println("=================================================");
            System.out.println("   ✓✓✓ ALL TESTS PASSED! ✓✓✓");
            System.out.println("   Your database is ready to use!");
            System.out.println("=================================================");
            
        } catch (Exception e) {
            System.out.println("    ✗ CONNECTION FAILED!");
            System.out.println();
            System.out.println("=================================================");
            System.out.println("   ✗✗✗ CONNECTION ERROR ✗✗✗");
            System.out.println("=================================================");
            System.out.println();
            System.out.println("Error Message: " + e.getMessage());
            System.out.println();
            System.out.println("Common Solutions:");
            System.out.println("1. Check if PostgreSQL is running");
            System.out.println("2. Verify port number (should be 1212)");
            System.out.println("3. Verify database 'agripos' exists");
            System.out.println("4. Verify username 'postgres' is correct");
            System.out.println("5. Verify password '230805' is correct");
            System.out.println();
            e.printStackTrace();
            
        } finally {
            // Close connection
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                    System.out.println("\n✓ Connection closed properly");
                }
            } catch (Exception e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}