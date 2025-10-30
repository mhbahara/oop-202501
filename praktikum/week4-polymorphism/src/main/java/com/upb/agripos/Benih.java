package main.java.com.upb.agripos.model;

public class Benih extends Produk {
    private String varietas;

    public Benih(String kode, String nama, double harga, int stok, String varietas) {
        super(kode, nama, harga, stok);
        this.varietas = varietas;
    }

    // Overriding: Mengganti implementasi getInfo() dari superclass
    @Override
    public String getInfo() {
        return "Benih: " + super.getInfo() + ", Varietas: " + varietas;
    }

    // Getter untuk spesifik (diperlukan untuk tabel)
    public String getVarietas() {
        return varietas;
    }
}