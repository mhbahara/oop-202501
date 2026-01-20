package com.upb.agripos.service;

import com.upb.agripos.exception.InsufficientPaymentException;
import com.upb.agripos.payment.CashPayment;
import com.upb.agripos.payment.EWalletPayment;
import com.upb.agripos.payment.PaymentMethod;

public class PaymentService {
    
    public double processPayment(String method, double totalAmount, double paidAmount) 
            throws InsufficientPaymentException {
        PaymentMethod paymentMethod = getPaymentMethod(method);
        return paymentMethod.processPayment(totalAmount, paidAmount);
    }
    
    public boolean validatePayment(String method, double totalAmount, double paidAmount) {
        PaymentMethod paymentMethod = getPaymentMethod(method);
        return paymentMethod.validatePayment(totalAmount, paidAmount);
    }
    
    private PaymentMethod getPaymentMethod(String method) {
        switch (method.toUpperCase()) {
            case "TUNAI":
            case "CASH":
                return new CashPayment();
            case "E-WALLET":
            case "EWALLET":
                return new EWalletPayment();
            default:
                return new CashPayment(); // Default to cash
        }
    }
}