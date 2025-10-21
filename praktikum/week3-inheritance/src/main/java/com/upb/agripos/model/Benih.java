package model;

public class Benih extends Produk {
    private String varietas;

    public Benih(String kode, String nama, double harga, int stok, String varietas) {
        super(kode, nama, harga, stok);
        this.varietas = varietas;
    }

    public String getVarietas() { return varietas; }
    public void setVarietas(String varietas) { this.varietas = varietas; }
    
    // Method tambahan
    public void deskripsi() {
        System.out.println("Nama Benih: " + getNama() + ", Varietas: " + varietas + 
                           ", Harga: Rp" + getHarga() + ", Stok: " + getStok());
    }
}