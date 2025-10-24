package com.upb.agripos.model;

public class AlatPertanian extends Produk {
    private String bahan;
    private String ukuran;

    public AlatPertanian(String nama, double harga, String bahan, String ukuran) {
        super(nama, harga, "Alat Pertanian");
        this.bahan = bahan;
        this.ukuran = ukuran;
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Bahan  : " + bahan);
        System.out.println("Ukuran : " + ukuran);
        System.out.println("=============================");
    }
}
