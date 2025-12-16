package com.upb.agripos;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCartMap {

    private final Map<Product, Integer> items = new HashMap<>();

    public void addProduct(Product product) {
        items.put(product, items.getOrDefault(product, 0) + 1);
        System.out.println(product.getName() + " ditambahkan (qty +1).");
    }

    public double getTotal() {
        double total = 0;
        for (Map.Entry<Product, Integer> e : items.entrySet()) {
            total += e.getKey().getPrice() * e.getValue();
        }
        return total;
    }

    public void printCart() {
        System.out.println("\n=== Isi Keranjang (Map + Quantity) ===");
        for (Map.Entry<Product, Integer> e : items.entrySet()) {
            System.out.println("- " + e.getKey().getName() + " x" + e.getValue());
        }
        System.out.println("Total: Rp" + getTotal());
    }
}
