package main.java.com.upb.agripos;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class HelloFunctional {
    public static void main(String[] args) {
        String nim = "240320562";
        String nama = "Bunga Maura Aulya";
        List<String> produk = Arrays.asList("Benih Padi IR64", "Pupuk Urea 50kg", "Cangkul Baja");
        List<Integer> harga = Arrays.asList(25000, 350000, 90000);
        System.out.println("Hello POS World");
        System.out.println("Nama: " + nama);
        System.out.println("NIM: " + nim);
        System.out.println("Daftar Produk:");
        IntStream.range(0, produk.size())
            .forEach(i -> System.out.println("- " + produk.get(i) + ": " + harga.get(i)));
        int total = harga.stream().mapToInt(Integer::intValue).sum();
        System.out.println("Total harga semua produk: " + total);
   
    }
}
