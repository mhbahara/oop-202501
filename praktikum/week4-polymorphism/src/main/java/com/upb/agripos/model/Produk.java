package com.upb.agripos.model;

public class Produk {
    protected String nama;
    protected double harga;
    protected int stok;

    public Produk(String nama, double harga, int stok) {
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }

    // Getter
    public String getNama() { return nama; }
    public double getHarga() { return harga; }
    public int getStok() { return stok; }

    // ✅ Overloading
    public void tambahStok(int jumlah) {
        this.stok += jumlah;
        System.out.println("Stok bertambah " + jumlah + " unit. Total sekarang: " + stok);
    }

    public void tambahStok(double jumlah) {
        this.stok += (int) jumlah;
        System.out.println("Stok bertambah " + jumlah + " unit (double). Total sekarang: " + stok);
    }

    // ✅ Method yang akan dioverride
    public void getInfo() {
        System.out.println("Nama Produk : " + nama);
        System.out.println("Harga       : Rp" + harga);
        System.out.println("Stok        : " + stok + " unit");
    }
}
