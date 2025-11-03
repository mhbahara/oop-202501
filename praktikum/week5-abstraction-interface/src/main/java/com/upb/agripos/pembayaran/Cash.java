package com.upb.agripos.pembayaran;

import com.upb.agripos.model.kontrak.Receiptable;
import java.text.DecimalFormat;

public class Cash extends Pembayaran implements Receiptable {
    private double tunai;

    public Cash(String invoiceNo, double total, double tunai) {
        super(invoiceNo, total);
        this.tunai = tunai;
    }

    @Override
    public double biaya() {
        return 0.0;
    }

    @Override
    public boolean prosesPembayaran() {
        return tunai >= totalBayar();
    }

    @Override
    public String cetakStruk() {
        DecimalFormat df = new DecimalFormat("#,###.00");
        double kembali = Math.max(0, tunai - totalBayar());
        String status = prosesPembayaran() ? "BERHASIL" : "GAGAL";

        return String.format(
            "========================================\n" +
            "              STRUK PEMBAYARAN\n" +
            "========================================\n" +
            "Invoice No  : %s\n" +
            "Metode      : CASH\n" +
            "Total       : Rp%s\n" +
            "Bayar       : Rp%s\n" +
            "Kembali     : Rp%s\n" +
            "Status      : %s\n" +
            "----------------------------------------\n" +
            "Terima kasih telah melakukan pembayaran.\n" +
            "========================================\n",
            invoiceNo,
            df.format(totalBayar()),
            df.format(tunai),
            df.format(kembali),
            status
        );
    }
}
