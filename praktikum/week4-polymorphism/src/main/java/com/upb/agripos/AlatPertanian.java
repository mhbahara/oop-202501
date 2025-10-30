package main.java.com.upb.agripos.model;

public class AlatPertanian extends Produk {
    private String bahan;

    public AlatPertanian(String kode, String nama, double harga, int stok, String bahan) {
        super(kode, nama, harga, stok);
        this.bahan = bahan;
    }

    // Overriding: Mengganti implementasi getInfo() dari superclass
    @Override
    public String getInfo() {
        return "Alat Pertanian: " + super.getInfo() + ", Bahan: " + bahan;
    }

    // Getter untuk spesifik (diperlukan untuk tabel)
    public String getBahan() {
        return bahan;
    }
}
