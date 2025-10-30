package main.java.com.upb.agripos.model;

public class Pupuk extends Produk {
    private String jenis;

    public Pupuk(String kode, String nama, double harga, int stok, String jenis) {
        super(kode, nama, harga, stok);
        this.jenis = jenis;
    }

    // Overriding: Mengganti implementasi getInfo() dari superclass
    @Override
    public String getInfo() {
        return "Pupuk: " + super.getInfo() + ", Jenis: " + jenis;
    }

    // Getter untuk spesifik (diperlukan untuk tabel)
    public String getJenis() {
        return jenis;
    }
}