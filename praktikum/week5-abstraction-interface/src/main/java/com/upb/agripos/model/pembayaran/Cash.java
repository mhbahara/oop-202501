package com.upb.agripos.model.pembayaran;

import com.upb.agripos.model.kontrak.Receiptable;

public class Cash extends Pembayaran implements Receiptable {
    private double tunai;

    public Cash(String invoiceNo, double total, double tunai) {
        super(invoiceNo, total);
        this.tunai = tunai;
    }

    @Override
    public double biaya() {
        return 0; // tidak ada biaya tambahan
    }

    @Override
    public boolean prosesPembayaran() {
        return tunai >= totalBayar();
    }

    @Override
    public String cetakStruk() {
        return String.format(
            "=== STRUK PEMBAYARAN CASH ===\nInvoice: %s\nTotal: Rp%.2f\nTunai: Rp%.2f\nStatus: %s\n",
            invoiceNo, totalBayar(), tunai, prosesPembayaran() ? "BERHASIL" : "GAGAL"
        );
    }
}
