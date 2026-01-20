package com.upb.agripos.payment;

import com.upb.agripos.exception.InsufficientPaymentException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PaymentMethodTest {
    
    @Test
    void testCashPaymentSuccess() throws InsufficientPaymentException {
        PaymentMethod cashPayment = new CashPayment();
        double change = cashPayment.processPayment(100000, 150000);
        assertEquals(50000, change, 0.01);
    }
    
    @Test
    void testCashPaymentExactAmount() throws InsufficientPaymentException {
        PaymentMethod cashPayment = new CashPayment();
        double change = cashPayment.processPayment(100000, 100000);
        assertEquals(0, change, 0.01);
    }
    
    @Test
    void testCashPaymentInsufficientThrowsException() {
        PaymentMethod cashPayment = new CashPayment();
        assertThrows(InsufficientPaymentException.class, () -> {
            cashPayment.processPayment(100000, 50000);
        });
    }
    
    @Test
    void testEWalletPaymentSuccess() throws InsufficientPaymentException {
        PaymentMethod ewalletPayment = new EWalletPayment();
        double change = ewalletPayment.processPayment(100000, 100000);
        assertEquals(0, change, 0.01);
    }
    
    @Test
    void testEWalletPaymentMustBeExact() {
        PaymentMethod ewalletPayment = new EWalletPayment();
        assertThrows(InsufficientPaymentException.class, () -> {
            ewalletPayment.processPayment(100000, 150000);
        });
    }
    
    @Test
    void testCashPaymentValidation() {
        PaymentMethod cashPayment = new CashPayment();
        assertTrue(cashPayment.validatePayment(100000, 150000));
        assertTrue(cashPayment.validatePayment(100000, 100000));
        assertFalse(cashPayment.validatePayment(100000, 50000));
    }
    
    @Test
    void testEWalletPaymentValidation() {
        PaymentMethod ewalletPayment = new EWalletPayment();
        assertTrue(ewalletPayment.validatePayment(100000, 100000));
        assertFalse(ewalletPayment.validatePayment(100000, 150000));
        assertFalse(ewalletPayment.validatePayment(100000, 50000));
    }
}
