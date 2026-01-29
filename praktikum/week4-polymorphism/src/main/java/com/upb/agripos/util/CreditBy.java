package com.upb.agripos.util;

import com.upb.agripos.model.Produk;

public class CreditBy {
    public static void print(String nim, String nama) {
        System.out.println("\nCredit By: " + nim + " - " + nama);
    }

    public static void beliKredit(Produk p, int bulan) {
        double bunga = 0.1;
        double total = p.getHarga() + (p.getHarga() * bunga * bulan);
        double cicilan = total / bulan;

        System.out.println("\n=== PEMBELIAN KREDIT ===");
        System.out.println("Produk : " + p.getNama());
        System.out.println("Harga Asli : Rp" + p.getHarga());
        System.out.println("Lama Kredit: " + bulan + " bulan");
        System.out.println("Total dengan bunga: Rp" + total);
        System.out.println("Cicilan per bulan : Rp" + cicilan);
    }
}
