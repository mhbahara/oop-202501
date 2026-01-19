package model;

public class Pupuk extends Produk {
    private String jenis;

    public Pupuk(String kode, String nama, double harga, int stok, String jenis) {
        super(kode, nama, harga, stok);
        this.jenis = jenis;
    }

    public String getJenis() { return jenis; }
    public void setJenis(String jenis) { this.jenis = jenis; }

    // Method tambahan
    public void deskripsi() {
        System.out.println("Nama Pupuk: " + getNama() + ", Jenis Pupuk: " + jenis + 
                           ", Harga: Rp" + getHarga() + ", Stok: " + getStok());
    }
}