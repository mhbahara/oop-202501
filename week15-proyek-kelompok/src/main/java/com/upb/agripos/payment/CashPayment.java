package com.upb.agripos.payment;

import com.upb.agripos.exception.InsufficientPaymentException;

public class CashPayment implements PaymentMethod {
    
    @Override
    public double processPayment(double totalAmount, double paidAmount) 
            throws InsufficientPaymentException {
        if (!validatePayment(totalAmount, paidAmount)) {
            throw new InsufficientPaymentException(totalAmount, paidAmount);
        }
        
        double change = paidAmount - totalAmount;
        System.out.println("Pembayaran Tunai berhasil. Kembalian: Rp " + change);
        return change;
    }
    
    @Override
    public String getMethodName() {
        return "TUNAI";
    }
    
    @Override
    public boolean validatePayment(double totalAmount, double paidAmount) {
        return paidAmount >= totalAmount;
    }
}