package com.upb.agripos.model;

public class CartItem {
    private Product product;
    private int quantity;
    private double subtotal;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        updateSubtotal();
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getSubtotal() {
        return subtotal;
    }

    // Tambahan method untuk GUI dinamis
    public void setQuantity(int quantity) {
        this.quantity = quantity;
        updateSubtotal();
    }

    public void updateSubtotal() {
        this.subtotal = this.product.getPrice() * this.quantity;
    }
}
