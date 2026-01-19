package com.upb.agripos;

import java.sql.Connection;
import java.sql.DriverManager;

import com.upb.agripos.dao.ProductDAO;
import com.upb.agripos.dao.ProductDAOImpl;
import com.upb.agripos.model.Product;

public class MainDAOTest {
    public static void main(String[] args) {
        try {
            System.out.println("====================================");
            System.out.println("MENGHUBUNGKAN KE DATABASE...");
            System.out.println("====================================");

            Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/agripos",
                "postgres",
                "postgres"
            );

            System.out.println("KONEKSI DATABASE BERHASIL");
            System.out.println("Database : agripos");
            System.out.println("====================================\n");

            ProductDAO dao = new ProductDAOImpl(conn);

            // INSERT
            System.out.println("[INSERT]");
            dao.insert(new Product("P01", "Pupuk Organik", 25000, 10));
            System.out.println("Produk berhasil ditambahkan\n");

            // UPDATE
            System.out.println("[UPDATE]");
            dao.update(new Product("P01", "Pupuk Organik Premium", 30000, 8));
            System.out.println("Produk berhasil diperbarui\n");

            // READ
            System.out.println("[READ]");
            Product p = dao.findByCode("P01");
            if (p != null) {
                System.out.println("Kode  : " + p.getCode());
                System.out.println("Nama  : " + p.getName());
                System.out.println("Harga : " + p.getPrice());
                System.out.println("Stok  : " + p.getStock());
            }

            // DELETE
            System.out.println("\n[DELETE]");
            dao.delete("P01");
            System.out.println("Produk berhasil dihapus\n");

            conn.close();

            System.out.println("====================================");
            System.out.println("PROGRAM SELESAI");
            System.out.println("====================================");

        } catch (Exception e) {
            System.out.println("KONEKSI DATABASE GAGAL");
            e.printStackTrace();
        }
    }
}