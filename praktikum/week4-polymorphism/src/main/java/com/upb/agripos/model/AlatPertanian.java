package model;

public class AlatPertanian extends Produk {
    private String bahan;

    public AlatPertanian(String kode, String nama, double harga, int stok, String bahan) {
        super(kode, nama, harga, stok);
        this.bahan = bahan;
    }
    public String getbahan() { return bahan; }
    public void setbahan(String bahan) { this.bahan = bahan; }

    @Override
    public String getInfo() {
        return "Alat Pertanian: " + getKode() + " - " + getNama() + " - Harga: " + getHarga() + " - Stok: " + getStok() + " - Bahan: " + bahan;
    }
}
