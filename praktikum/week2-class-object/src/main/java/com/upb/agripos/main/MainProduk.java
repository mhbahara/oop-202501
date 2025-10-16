package com.upb.agripos.main;

import com.upb.agripos.model.Produk;
import com.upb.agripos.util.CreditBy;

public class MainProduk {
    public static void main(String[] args) {
        // Membuat minimal 3 objek produk
        Produk p1 = new Produk("P001", "Benih Padi", 50000, 20);
        Produk p2 = new Produk("P002", "Pupuk Urea", 75000, 15);
        Produk p3 = new Produk("P003", "Cangkul Baja", 120000, 10);

        // Menampilkan daftar produk
        System.out.println("=== Daftar Produk ===");
        System.out.println(p1.getKode() + " - " + p1.getNama() + " | Rp" + p1.getHarga() + " | Stok: " + p1.getStok());
        System.out.println(p2.getKode() + " - " + p2.getNama() + " | Rp" + p2.getHarga() + " | Stok: " + p2.getStok());
        System.out.println(p3.getKode() + " - " + p3.getNama() + " | Rp" + p3.getHarga() + " | Stok: " + p3.getStok());

        // Menggunakan latihan mandiri
        System.out.println("\n=== Update Stok ===");
        p1.tambahStok(5);
        p2.kurangiStok(3);
        System.out.println(p1.getNama() + " stok baru: " + p1.getStok());
        System.out.println(p2.getNama() + " stok baru: " + p2.getStok());

        // Menampilkan identitas mahasiswa
        System.out.println();
        CreditBy.print("240202878", "Rafi Kurniawan");
    }
}
