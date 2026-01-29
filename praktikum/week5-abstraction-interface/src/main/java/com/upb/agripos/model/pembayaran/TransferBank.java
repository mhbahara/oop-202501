package com.upb.agripos.model.pembayaran;

import com.upb.agripos.model.kontrak.Receiptable;
import com.upb.agripos.model.kontrak.Validatable;

public class TransferBank extends Pembayaran implements Validatable, Receiptable {
    private String bank;
    private boolean otpValid;
    private static final double BIAYA_TETAP = 3500;

    public TransferBank(String invoiceNo, double total, String bank, boolean otpValid) {
        super(invoiceNo, total);
        this.bank = bank;
        this.otpValid = otpValid;
    }

    @Override
    public double biaya() {
        return BIAYA_TETAP;
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
            "=== STRUK TRANSFER BANK ===\nInvoice: %s\nBank: %s\nTotal: Rp%.2f\nBiaya Admin: Rp%.2f\nStatus: %s\n",
            invoiceNo, bank, totalBayar(), BIAYA_TETAP, prosesPembayaran() ? "BERHASIL" : "GAGAL"
        );
    }
}
