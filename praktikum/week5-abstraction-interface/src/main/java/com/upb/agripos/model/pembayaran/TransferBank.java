package model.pembayaran;

import model.kontrak.Receiptable;
import model.kontrak.Validatable;

public class TransferBank extends Pembayaran implements Validatable, Receiptable {
    private String bankTujuan;
    private boolean otpValid;
    private static final double BIAYA_ADMIN = 3500.0;

    public TransferBank(String invoiceNo, double total, String bankTujuan, boolean otpValid) {
        super(invoiceNo, total);
        this.bankTujuan = bankTujuan;
        this.otpValid = otpValid;
    }

    @Override
    public double biaya() {
        // Biaya tetap untuk transfer bank
        return BIAYA_ADMIN;
    }

    @Override
    public boolean validasi() {
        // Misal validasi OTP (disimulasikan dengan nilai boolean otpValid)
        return otpValid;
    }

    @Override
    public boolean prosesPembayaran() {
        // Pembayaran berhasil hanya jika OTP valid
        if (validasi()) {
            System.out.println("Transfer ke bank " + bankTujuan + " berhasil diproses.");
            return true;
        } else {
            System.out.println("Validasi OTP gagal. Transfer dibatalkan.");
            return false;
        }
    }

    @Override
    public String cetakStruk() {
        return String.format(
            "INVOICE %s | TOTAL: Rp%.2f | BANK TUJUAN: %s | BIAYA ADMIN: Rp%.2f | TOTAL BAYAR: Rp%.2f | STATUS: %s",
            invoiceNo, total, bankTujuan, biaya(), totalBayar(), (otpValid ? "BERHASIL" : "GAGAL"));
    }
}
