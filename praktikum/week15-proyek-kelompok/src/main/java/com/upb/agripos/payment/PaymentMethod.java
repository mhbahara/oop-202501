package com.upb.agripos.payment;

import com.upb.agripos.exception.PaymentException;

public interface PaymentMethod {
    void processPayment(double amount) throws PaymentException;
    String getMethodName();
}
