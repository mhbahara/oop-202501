package com.upb.agripos.exception;

public class OutOfStockException extends Exception {
    private String productName;
    private int availableStock;
    private int requestedQuantity;

    public OutOfStockException(String productName, int availableStock, int requestedQuantity) {
        super(String.format("Stok tidak cukup untuk %s. Stok tersedia: %d, diminta: %d", 
            productName, availableStock, requestedQuantity));
        this.productName = productName;
        this.availableStock = availableStock;
        this.requestedQuantity = requestedQuantity;
    }

    public String getProductName() { return productName; }
    public int getAvailableStock() { return availableStock; }
    public int getRequestedQuantity() { return requestedQuantity; }
}