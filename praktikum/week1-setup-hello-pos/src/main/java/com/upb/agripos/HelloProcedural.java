package main.java.com.upb.agripos;

public class HelloProcedural {
    public static void main(String[] args) {
        String nama = "Bunga Maura Aulya";
        String nim = "240320562";
        String[] produk = {"Benih Padi IR64", "Pupuk Urea 50kg", "Cangkul Baja"};
        int[] harga = {25000, 350000, 90000};
        int total = 0;
        System.out.println("Hello POS World, I am " +nama + "-" +nim);
        System.out.println("Daftar Produk: ");
        for (int i = 0; i < produk.length; i++){
            System.out.println("- " +produk[i] + ": " +harga[i]);
            total += harga[i];
        }
        System.out.println("Total harga semua produk: " +total);
    }
}