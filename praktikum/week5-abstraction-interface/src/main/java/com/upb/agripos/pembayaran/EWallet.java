package com.upb.agripos.pembayaran;

import com.upb.agripos.model.kontrak.Validatable;
import com.upb.agripos.model.kontrak.Receiptable;
import java.text.DecimalFormat;

public class EWallet extends Pembayaran implements Validatable, Receiptable {
    private String akun;
    private String otp;

    public EWallet(String invoiceNo, double total, String akun, String otp) {
        super(invoiceNo, total);
        this.akun = akun;
        this.otp = otp;
    }

    @Override
    public double biaya() {
        return total * 0.015;
    }

    @Override
    public boolean validasi() {
        return otp != null && otp.length() == 6;
    }

    @Override
    public boolean prosesPembayaran() {
        return validasi();
    }

    @Override
    public String cetakStruk() {
        DecimalFormat df = new DecimalFormat("#,###.00");
        String status = prosesPembayaran() ? "BERHASIL" : "GAGAL";

        return String.format(
            "========================================\n" +
            "              STRUK PEMBAYARAN\n" +
            "========================================\n" +
            "Invoice No  : %s\n" +
            "Metode      : E-WALLET\n" +
            "Akun        : %s\n" +
            "Total+Fee   : Rp%s\n" +
            "Status      : %s\n" +
            "----------------------------------------\n" +
            "Terima kasih telah melakukan pembayaran.\n" +
            "========================================\n",
            invoiceNo,
            akun,
            df.format(totalBayar()),
            status
        );
    }
}

