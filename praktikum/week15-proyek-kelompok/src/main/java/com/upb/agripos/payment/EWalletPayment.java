package com.upb.agripos.payment;

import com.upb.agripos.exception.PaymentException;

public class EWalletPayment implements PaymentMethod {
    private double balance; // For mock purposes

    public EWalletPayment(double initialBalance) {
        this.balance = initialBalance;
    }

    @Override
    public void processPayment(double amount) throws PaymentException {
        if (amount <= 0) {
            throw new PaymentException("Jumlah pembayaran harus lebih dari 0");
        }
        if (balance < amount) {
            throw new PaymentException("Saldo E-Wallet tidak cukup. Saldo: Rp" + balance);
        }
        balance -= amount;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String getMethodName() {
        return "E-WALLET";
    }
}
