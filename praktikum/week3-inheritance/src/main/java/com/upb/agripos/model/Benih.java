package com.upb.agripos.model;

public class Benih extends Produk {
    private String jenisTanaman;
    private int masaTanam; // dalam hari

    public Benih(String nama, double harga, String jenisTanaman, int masaTanam) {
        super(nama, harga, "Benih");
        this.jenisTanaman = jenisTanaman;
        this.masaTanam = masaTanam;
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Jenis Tanaman : " + jenisTanaman);
        System.out.println("Masa Tanam    : " + masaTanam + " hari");
        System.out.println("=============================");
    }
}
