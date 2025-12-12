package com.upb.agripos;

import java.util.ArrayList;

public class ShoppingCart {
    private final ArrayList<Product> items = new ArrayList<>();

    public void addProduct(Product p) {
        items.add(p);
        System.out.println(p.getName() + " ditambahkan ke keranjang.");
    }

    public void removeProduct(Product p) {
        items.remove(p);
        System.out.println(p.getName() + " dihapus dari keranjang.");
    }

    public double getTotal() {
        double total = 0;
        for (Product p : items) total += p.getPrice();
        return total;
    }

    public void printCart() {
        System.out.println("\n=== Isi Keranjang (ArrayList) ===");
        for (Product p : items) {
            System.out.println("- " + p.getCode() + " | " + p.getName() + " = Rp" + p.getPrice());
        }
        System.out.println("Total: Rp" + getTotal());
    }
}
