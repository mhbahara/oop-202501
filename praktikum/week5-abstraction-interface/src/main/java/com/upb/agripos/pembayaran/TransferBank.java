package com.upb.agripos.pembayaran;

import com.upb.agripos.model.kontrak.Validatable;
import com.upb.agripos.model.kontrak.Receiptable;
import java.text.DecimalFormat;

public class TransferBank extends Pembayaran implements Validatable, Receiptable {
    private String rekening;
    private String pin;

    public TransferBank(String invoiceNo, double total, String rekening, String pin) {
        super(invoiceNo, total);
        this.rekening = rekening;
        this.pin = pin;
    }

    @Override
    public double biaya() {
        // Biaya tetap (misalnya biaya admin)
        return 3500.0;
    }

    @Override
    public boolean validasi() {
        // PIN harus terdiri dari 4 digit
        return pin != null && pin.length() == 4;
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
            "Metode      : TRANSFER BANK\n" +
            "Rekening    : %s\n" +
            "Total+Fee   : Rp%s\n" +
            "Status      : %s\n" +
            "----------------------------------------\n" +
            "Terima kasih telah melakukan pembayaran.\n" +
            "========================================\n",
            invoiceNo,
            rekening,
            df.format(totalBayar()),
            status
        );
    }
}
