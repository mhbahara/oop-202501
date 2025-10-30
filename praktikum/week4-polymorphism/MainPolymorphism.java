package main.java.com.upb.agripos;

import main.java.com.upb.agripos.model.*;
import main.java.com.upb.agripos.util.CreditBy;

public class MainPolymorphism {

    public static void main(String[] args) {

        System.out.println("==================================================");
        System.out.println("          PRAKTIKUM POLYMORPHISM - WEEK 4          ");
        System.out.println("==================================================\n");

        // ============================================================
        // 🧩 Demo Overloading: Panggil tambahStok() dengan parameter berbeda
        // ============================================================
        System.out.println("--- Demo Overloading: tambahStok() ---");

        Produk produk = new Produk("PRD-001", "Produk Umum", 10_000, 50);

        System.out.printf("%-35s %d%n", "Stok awal:", produk.getStok());
        produk.tambahStok(10);  // Overload dengan int
        System.out.printf("%-35s %d%n", "Setelah tambah int (10):", produk.getStok());
        produk.tambahStok(5.5); // Overload dengan double
        System.out.printf("%-35s %d%n%n", "Setelah tambah double (5.5):", produk.getStok());

        // ============================================================
        // 🧬 Demo Dynamic Binding (Polymorphism): Tabel Data Produk
        // ============================================================
        System.out.println("--- DATA PRODUK ---");
        System.out.printf("%-8s %-20s %-10s %-8s %-10s%n", "Kode", "Nama", "Harga", "Stok", "Spesifik");
        System.out.println("--------------------------------------------------------------");

        Produk[] daftarProduk = {
            new Benih("BNH-001", "Benih Padi IR64", 25_000, 100, "IR64"),
            new Pupuk("PPK-101", "Pupuk Urea", 350_000, 40, "Urea"),
            new AlatPertanian("ALT-501", "Cangkul Baja", 90_000, 15, "Baja"),
            new ObatHama("OBH-201", "Pestisida Serangga", 150_000, 20, "Serangga")
        };

        for (Produk p : daftarProduk) {
            // Menggunakan getter untuk mengakses atribut individual dan spesifik dari subclass
            String spesifik = "";
            if (p instanceof Benih) {
                spesifik = ((Benih) p).getVarietas();
            } else if (p instanceof Pupuk) {
                spesifik = ((Pupuk) p).getJenis();
            } else if (p instanceof AlatPertanian) {
                spesifik = ((AlatPertanian) p).getBahan();
            } else if (p instanceof ObatHama) {
                spesifik = ((ObatHama) p).getJenisHama();
            }
            System.out.printf("%-8s %-20s %-10d %-8d %-10s%n", p.getKode(), p.getNama(), (int) p.getHarga(), p.getStok(), spesifik);
        }

        System.out.println("--------------------------------------------------------------");
        System.out.println("Program ini dibuat oleh:");
        System.out.println("NIM  : 240320565");
        System.out.println("Nama : Wisnu Wibowo Saputro");
        System.out.println("==================================================");
    }
}
