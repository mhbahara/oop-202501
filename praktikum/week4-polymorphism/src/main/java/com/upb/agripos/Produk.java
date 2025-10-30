package main.java.com.upb.agripos.model;

public class Produk {
    private String kode;
    private String nama;
    private double harga;
    private int stok;

    public Produk(String kode, String nama, double harga, int stok) {
        this.kode = kode;
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }

    // Overloading: Method tambahStok dengan parameter int
    public void tambahStok(int jumlah) {
        this.stok += jumlah;
    }

    // Overloading: Method tambahStok dengan parameter double
    public void tambahStok(double jumlah) {
        this.stok += (int) jumlah;
    }

    // Method getInfo() yang akan di-override oleh subclass
    public String getInfo() {
        return "Produk: " + nama + " (Kode: " + kode + ")";
    }

    // Getter untuk akses atribut (diperlukan untuk tabel)
    public String getKode() {
        return kode;
    }

    public String getNama() {
        return nama;
    }

    public double getHarga() {
        return harga;
    }

    public int getStok() {
        return stok;
    }
}