package com.upb.agripos.payment;

import com.upb.agripos.exception.InsufficientPaymentException;

public interface PaymentMethod {
    /**
     * Process payment
     * @param totalAmount Total amount to be paid
     * @param paidAmount Amount given by customer
     * @return Change amount (0 for e-wallet)
     * @throws InsufficientPaymentException if payment is insufficient
     */
    double processPayment(double totalAmount, double paidAmount) 
        throws InsufficientPaymentException;
    
    /**
     * Get payment method name
     */
    String getMethodName();
    
    /**
     * Validate payment before processing
     */
    boolean validatePayment(double totalAmount, double paidAmount);
}