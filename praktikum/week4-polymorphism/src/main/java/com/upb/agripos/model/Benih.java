package com.upb.agripos.model;

public class Benih extends Produk {
    private String jenisTanaman;
    private int masaTanam;

    public Benih(String nama, double harga, int stok, String jenisTanaman, int masaTanam) {
        super(nama, harga, stok);
        this.jenisTanaman = jenisTanaman;
        this.masaTanam = masaTanam;
    }

    // ✅ Overriding
    @Override
    public void getInfo() {
        System.out.println("[BENIH]");
        super.getInfo();
        System.out.println("Jenis Tanaman: " + jenisTanaman);
        System.out.println("Masa Tanam   : " + masaTanam + " hari");
    }
}
