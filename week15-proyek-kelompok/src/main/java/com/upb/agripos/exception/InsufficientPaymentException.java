package com.upb.agripos.exception;

public class InsufficientPaymentException extends Exception {
    private double totalAmount;
    private double paidAmount;

    public InsufficientPaymentException(double totalAmount, double paidAmount) {
        super(String.format("Pembayaran tidak cukup. Total: Rp %.2f, Dibayar: Rp %.2f", 
            totalAmount, paidAmount));
        this.totalAmount = totalAmount;
        this.paidAmount = paidAmount;
    }

    public double getTotalAmount() { return totalAmount; }
    public double getPaidAmount() { return paidAmount; }
}
