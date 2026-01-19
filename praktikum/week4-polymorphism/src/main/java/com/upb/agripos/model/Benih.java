package model;

public class Benih extends Produk {
    private String varietas;

    public Benih(String kode, String nama, double harga, int stok, String varietas) {
        super(kode, nama, harga, stok);
        this.varietas = varietas;
    }
    public String getVarietas() { return varietas; }
    public void setVarietas(String varietas) { this.varietas = varietas; }

    @Override
    public String getInfo() {
        return "Benih: " + getKode() + " - " + getNama() + " - Harga: " + getHarga() + " - Stok: " + getStok() + " - Varietas: " + varietas;
    }
  
}
