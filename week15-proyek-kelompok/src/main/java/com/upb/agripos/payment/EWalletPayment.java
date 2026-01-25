package com.upb.agripos.payment;

import com.upb.agripos.exception.InsufficientPaymentException;

public class EWalletPayment implements PaymentMethod {
    private String walletProvider;
    
    public EWalletPayment() {
        this.walletProvider = "Generic E-Wallet";
    }
    
    public EWalletPayment(String walletProvider) {
        this.walletProvider = walletProvider;
    }
    
    @Override
    public double processPayment(double totalAmount, double paidAmount) 
            throws InsufficientPaymentException {
        if (!validatePayment(totalAmount, paidAmount)) {
            throw new InsufficientPaymentException(totalAmount, paidAmount);
        }
        
        // E-Wallet must be exact amount
        if (Math.abs(paidAmount - totalAmount) > 0.01) {
            throw new InsufficientPaymentException(totalAmount, paidAmount);
        }
        
        System.out.println("Pembayaran E-Wallet (" + walletProvider + ") berhasil");
        return 0.0; // No change for e-wallet
    }
    
    @Override
    public String getMethodName() {
        return "E-WALLET";
    }
    
    @Override
    public boolean validatePayment(double totalAmount, double paidAmount) {
        // E-Wallet requires exact amount
        return Math.abs(paidAmount - totalAmount) < 0.01;
    }
    
    public String getWalletProvider() {
        return walletProvider;
    }
}
