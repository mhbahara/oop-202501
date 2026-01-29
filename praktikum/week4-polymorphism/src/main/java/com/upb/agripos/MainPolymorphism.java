package com.upb.agripos;

import com.upb.agripos.model.*;
import com.upb.agripos.util.CreditBy;

public class MainPolymorphism {
    public static void main(String[] args) {
        Produk[] daftarProduk = {
            new Benih("Benih Jagung Hibrida", 30000, 100, "Jagung", 90),
            new Pupuk("Pupuk Urea", 50000, 70, "Kimia", 2.0),
            new AlatPertanian("Sprayer Elektrik", 250000, 15, "Makita", "Plastik dan Logam")
        };

        System.out.println("=== DAFTAR PRODUK AGRIPOS ===");
        for (Produk p : daftarProduk) {
            System.out.println("-----------------------------");
            p.getInfo(); // ✅ Dynamic Binding (otomatis panggil versi subclass)
        }

        // ✅ Overloading demo
        daftarProduk[0].tambahStok(10);
        daftarProduk[0].tambahStok(5.5);

        // ✅ Kredit demo
        CreditBy.beliKredit(daftarProduk[1], 6);
        CreditBy.print("240202878", "Rafi Kurniawan");
    }
}
