package com.upb.agripos.service;

import com.upb.agripos.model.CartItem;
import com.upb.agripos.exception.PaymentException;
import com.upb.agripos.payment.PaymentMethod;
import java.util.List;

public class PaymentService {
    
    public void processCheckout(List<CartItem> items, double total, PaymentMethod paymentMethod, 
                               double amountPaid) throws PaymentException {
        if (items == null || items.isEmpty()) {
            throw new PaymentException("Keranjang kosong, tidak bisa checkout");
        }
        if (total <= 0) {
            throw new PaymentException("Total tidak valid");
        }
        if (amountPaid < total) {
            throw new PaymentException("Jumlah pembayaran kurang. Total: Rp" + total + 
                                      ", Dibayar: Rp" + amountPaid);
        }

        paymentMethod.processPayment(amountPaid);
    }

    public double calculateChange(double total, double amountPaid) throws PaymentException {
        if (amountPaid < total) {
            throw new PaymentException("Jumlah pembayaran kurang");
        }
        return amountPaid - total;
    }
}
