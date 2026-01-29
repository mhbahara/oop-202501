
package com.upb.agripos.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Bab 7: Collections - Cart menggunakan List dan Map
 */
public class Cart {
    private List<CartItem> items; // List untuk menyimpan item
    private Map<String, CartItem> itemMap; // Map untuk akses cepat berdasarkan kode

    public Cart() {
        this.items = new ArrayList<>();
        this.itemMap = new HashMap<>();
    }

    public void addItem(Product product, int quantity) {
        String code = product.getCode();
        
        if (itemMap.containsKey(code)) {
            // Update quantity jika produk sudah ada
            CartItem existing = itemMap.get(code);
            existing.setQuantity(existing.getQuantity() + quantity);
        } else {
            // Tambah item baru
            CartItem newItem = new CartItem(product, quantity);
            items.add(newItem);
            itemMap.put(code, newItem);
        }
    }

    public void removeItem(String productCode) {
        CartItem item = itemMap.remove(productCode);
        if (item != null) {
            items.remove(item);
        }
    }

    public void updateQuantity(String productCode, int newQuantity) {
        CartItem item = itemMap.get(productCode);
        if (item != null) {
            if (newQuantity <= 0) {
                removeItem(productCode);
            } else {
                item.setQuantity(newQuantity);
            }
        }
    }

    public void clear() {
        items.clear();
        itemMap.clear();
    }

    public BigDecimal calculateTotal() {
        return items.stream()
                .map(CartItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<CartItem> getItems() {
        return new ArrayList<>(items); // Return copy untuk encapsulation
    }

    public int getTotalItems() {
        return items.stream()
                .mapToInt(CartItem::getQuantity)
                .sum();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}