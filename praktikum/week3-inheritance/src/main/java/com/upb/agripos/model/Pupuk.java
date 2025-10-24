package com.upb.agripos.model;

public class Pupuk extends Produk {
    private String kandungan;
    private double berat; // dalam kilogram

    public Pupuk(String nama, double harga, String kandungan, double berat) {
        super(nama, harga, "Pupuk");
        this.kandungan = kandungan;
        this.berat = berat;
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Kandungan : " + kandungan);
        System.out.println("Berat     : " + berat + " kg");
        System.out.println("=============================");
    }
}
