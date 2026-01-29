package com.upb.agripos.model;

public class Produk {
    protected String nama;
    protected double harga;
    protected String kategori;

    public Produk(String nama, double harga, String kategori) {
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
    }

    public void tampilkanInfo() {
        System.out.println("Nama Produk : " + nama);
        System.out.println("Kategori    : " + kategori);
        System.out.println("Harga       : Rp " + harga);
        System.out.println("-----------------------------");
    }
}
