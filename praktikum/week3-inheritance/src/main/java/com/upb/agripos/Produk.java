package main.java.com.upb.agripos.model;

public class Produk {
    private String kode;
    private String nama;
    private double harga;
    private int stok;

    // Constructor
    public Produk(String kode, String nama, double harga, int stok) {
        this.kode = kode;
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }

    // Getters
    public String getKode() { return kode; }
    public String getNama() { return nama; }
    public double getHarga() { return harga; }
    public int getStok() { return stok; }

    // Setters
    public void setKode(String kode) { this.kode = kode; }
    public void setNama(String nama) { this.nama = nama; }
    public void setHarga(double harga) { this.harga = harga; }
    public void setStok(int stok) { this.stok = stok; }
    
    // Method untuk menambah stok
    public void tambahStok(int jumlah) {
        if (jumlah > 0) {
            stok += jumlah;
            System.out.println(jumlah + " stok ditambahkan. Stok sekarang: " + stok);
        } else {
            System.out.println("Jumlah harus lebih dari 0");
        }
    }

    // Method untuk mengurangi stok
    public void kurangiStok(int jumlah) {
        if (jumlah > 0 && jumlah <= stok) {
            stok -= jumlah;
            System.out.println(jumlah + " stok dikurangi. Stok sekarang: " + stok);
        } else {
            System.out.println("Jumlah tidak valid atau stok tidak cukup");
        }
    }

    // Method toString untuk menampilkan data dasar (akan di-override di subclass)
    @Override
    public String toString() {
        return "Kode: " + kode + ", Nama: " + nama + ", Harga: Rp" + harga + ", Stok: " + stok;
    }
}