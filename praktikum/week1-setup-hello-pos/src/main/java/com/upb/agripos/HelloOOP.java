package main.java.com.upb.agripos;

class mahasiswa{
    String nama;
    String nim;

    public mahasiswa(String nama, String nim) {
        this.nama = nama;
        this.nim = nim;
    }
    void sapa(){
        System.out.println("Hello POS World, I am " +nama + "-" +nim);
    }
}
class Produk{
    String namaProduk;
    int harga;

    public Produk(String namaProduk, int harga) {
        this.namaProduk = namaProduk;
        this.harga = harga;
    }
    
}
public class HelloOOP {
    public static void main(String[] args) {
        mahasiswa m = new mahasiswa("Bunga Maura Aulya", "240320562");
        m.sapa();
        Produk[] daftar = {
         new Produk("Benih Padi IR64", 25000),
         new Produk("Pupuk Urea 50kg", 350000),
         new Produk("Cangkul Baja", 90000)
        };
        int total = 0;
        System.out.println("Daftar Produk:");
        for (Produk p : daftar) {
            System.out.println("- " + p.namaProduk + ": " + p.harga);
            total += p.harga;
      }
      System.out.println("Total harga semua produk: " + total);
      
    }
}