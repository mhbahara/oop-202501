package main.java.com.upb.agripos.model;

public class ObatHama extends Produk {
    private String jenisHama;

    public ObatHama(String kode, String nama, double harga, int stok, String jenisHama) {
        super(kode, nama, harga, stok);
        this.jenisHama = jenisHama;
    }

    // Overriding: Mengganti implementasi getInfo() dari superclass
    @Override
    public String getInfo() {
        return "Obat Hama: " + super.getInfo() + ", Jenis Hama: " + jenisHama;
    }

    // Getter untuk spesifik (diperlukan untuk tabel)
    public String getJenisHama() {
        return jenisHama;
    }
}

