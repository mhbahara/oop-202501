package com.upb.agripos;

public class MainCart {
    public static void main(String[] args) {
        System.out.println("Hello, I am [Rafi Kurniawan]-[240202878] (Week7)");

        Product p1 = new Product("P01", "Beras", 50000);
        Product p2 = new Product("P02", "Pupuk", 30000);

        // ===== Versi ArrayList =====
        ShoppingCart cart = new ShoppingCart();
        cart.addProduct(p1);
        cart.addProduct(p2);
        cart.printCart();

        cart.removeProduct(p1);
        cart.printCart();

        // ===== Versi Map (dengan quantity) =====
        ShoppingCartMap cartMap = new ShoppingCartMap();
        cartMap.addProduct(p1);
        cartMap.addProduct(p1);
        cartMap.addProduct(p2);
        cartMap.printCart();
    }
}
