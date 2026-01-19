package model;

public class AlatPertanian extends Produk {
    private String material;

    public AlatPertanian(String kode, String nama, double harga, int stok, String material) {
        super(kode, nama, harga, stok);
        this.material = material;
    }

    public String getMaterial() { return material; }
    public void setMaterial(String material) { this.material = material; }

    // Method tambahan
    public void deskripsi() {
        System.out.println("Nama Alat: " + getNama() + ", Bahan Dasar: " + material + 
                           ", Harga: Rp" + getHarga() + ", Stok: " + getStok());
    }
}