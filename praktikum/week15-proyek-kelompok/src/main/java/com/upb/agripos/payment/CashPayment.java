package com.upb.agripos.payment;

import com.upb.agripos.exception.PaymentException;

public class CashPayment implements PaymentMethod {
    @Override
    public void processPayment(double amount) throws PaymentException {
        if (amount <= 0) {
            throw new PaymentException("Jumlah pembayaran harus lebih dari 0");
        }
    }

    @Override
    public String getMethodName() {
        return "CASH";
    }
}
