package com.upb.agripos.model.pembayaran;

/**
 * Abstract class untuk semua jenis pembayaran.
 */
public abstract class Pembayaran {
    protected String invoiceNo;
    protected double total;

    public Pembayaran(String invoiceNo, double total) {
        this.invoiceNo = invoiceNo;
        this.total = total;
    }

    // Method abstrak
    public abstract double biaya();                 // biaya tambahan
    public abstract boolean prosesPembayaran();     // status berhasil/gagal

    // Method konkrit
    public double totalBayar() {
        return total + biaya();
    }

    public String getInvoiceNo() { return invoiceNo; }
    public double getTotal() { return total; }
}
