package main.java.com.upb.agripos.model;

public class Benih extends Produk {
    private String varietas;

    // Constructor
    public Benih(String kode, String nama, double harga, int stok, String varietas) {
        super(kode, nama, harga, stok);  // Inisialisasi atribut superclass
        this.varietas = varietas;        // Inisialisasi atribut spesifik
    }

    // Getter
    public String getVarietas() {
        return varietas;
    }

    // Setter
    public void setVarietas(String varietas) {
        this.varietas = varietas;
    }

    // Override toString
    @Override
    public String toString() {
        return super.toString() + ", Varietas: " + varietas;  // Gabung data superclass + spesifik
    }
}