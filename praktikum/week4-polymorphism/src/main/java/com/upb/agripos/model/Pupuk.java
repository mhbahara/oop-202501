package com.upb.agripos.model;

public class Pupuk extends Produk {
    private String tipe;
    private double berat;

    public Pupuk(String nama, double harga, int stok, String tipe, double berat) {
        super(nama, harga, stok);
        this.tipe = tipe;
        this.berat = berat;
    }

    @Override
    public void getInfo() {
        System.out.println("[PUPUK]");
        super.getInfo();
        System.out.println("Tipe Pupuk : " + tipe);
        System.out.println("Berat      : " + berat + " kg");
    }
}
