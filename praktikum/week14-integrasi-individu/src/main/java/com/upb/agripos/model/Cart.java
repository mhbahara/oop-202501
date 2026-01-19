package com.upb.agripos.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items = new ArrayList<>();

    public void addItem(Product product, int qty) {
        if (qty <= 0) {
            throw new IllegalArgumentException("Jumlah tidak valid");
        }
        if (qty > product.getStock()) {
            throw new IllegalArgumentException("Stok tidak cukup");
        }
        items.add(new CartItem(product, qty));
    }

    public double getTotal() {
        return items.stream()
                .mapToDouble(CartItem::getSubtotal)
                .sum();
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void clear() {
        items.clear();
    }
}
