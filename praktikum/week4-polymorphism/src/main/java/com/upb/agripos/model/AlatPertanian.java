package com.upb.agripos.model;

public class AlatPertanian extends Produk {
    private String merk;
    private String bahan;

    public AlatPertanian(String nama, double harga, int stok, String merk, String bahan) {
        super(nama, harga, stok);
        this.merk = merk;
        this.bahan = bahan;
    }

    @Override
    public void getInfo() {
        System.out.println("[ALAT PERTANIAN]");
        super.getInfo();
        System.out.println("Merk  : " + merk);
        System.out.println("Bahan : " + bahan);
    }
}
