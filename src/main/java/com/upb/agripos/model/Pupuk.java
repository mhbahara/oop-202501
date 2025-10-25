package main.java.com.upb.agripos.model;

public class Pupuk extends Produk {
    private String jenisPupuk;

    // Constructor
    public Pupuk(String kode, String nama, double harga, int stok, String jenisPupuk) {
        super(kode, nama, harga, stok);
        this.jenisPupuk = jenisPupuk;
    }

    // Getter
    public String getJenisPupuk() {
        return jenisPupuk;
    }

    // Setter
    public void setJenisPupuk(String jenisPupuk) {
        this.jenisPupuk = jenisPupuk;
    }

    // Override toString
    @Override
    public String toString() {
        return super.toString() + ", Jenis Pupuk: " + jenisPupuk;
    }
}