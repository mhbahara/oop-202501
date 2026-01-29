package com.upb.agripos.exception;

public class InsufficientStockException extends Exception {
    public InsufficientStockException(String productName, int requested, int available) {
        super(String.format("Stok tidak cukup untuk '%s'. Diminta: %d, Tersedia: %d", 
                productName, requested, available));
    }
}