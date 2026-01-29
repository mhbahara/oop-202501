package com.upb.agripos.model.pembayaran;

import com.upb.agripos.model.kontrak.Validatable;
import com.upb.agripos.model.kontrak.Receiptable;

public class EWallet extends Pembayaran implements Validatable, Receiptable {
    private String akun;
    private boolean otpValid;

    public EWallet(String invoiceNo, double total, String akun, boolean otpValid) {
        super(invoiceNo, total);
        this.akun = akun;
        this.otpValid = otpValid;
    }

    @Override
    public double biaya() {
        return total * 0.015; // 1.5% fee
    }

    @Override
    public boolean validasi() {
        return otpValid;
    }

    @Override
    public boolean prosesPembayaran() {
        return validasi();
    }

    @Override
    public String cetakStruk() {
        return String.format(
            "=== STRUK PEMBAYARAN E-WALLET ===\nInvoice: %s\nAkun: %s\nTotal: Rp%.2f\nStatus: %s\n",
            invoiceNo, akun, totalBayar(), prosesPembayaran() ? "BERHASIL" : "GAGAL"
        );
    }
}
