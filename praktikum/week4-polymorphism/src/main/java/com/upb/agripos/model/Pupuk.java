package model;

public class Pupuk extends Produk {
    private String jenis;

    public Pupuk(String kode, String nama, double harga, int stok, String jenis) {
        super(kode, nama, harga, stok);
        this.jenis = jenis;
    }
    public String getjenis() { return jenis; }
    public void setjenis(String jenis) { this.jenis = jenis; }

    @Override
    public String getInfo() {
        return "Pupuk: " + getKode() + " - " + getNama() + " - Harga: " + getHarga() + " - Stok: " + getStok() + " - Jenis: " + jenis;
    }
}
