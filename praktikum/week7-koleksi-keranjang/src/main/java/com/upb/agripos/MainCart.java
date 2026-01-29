package com.upb.agripos;

public class MainCart {
    public static void main(String[] args) {

        System.out.println("Hello, I am Rafi Kurniawan-[240202878] (Week7)");

        Product beras = new Product("P01", "Beras", 50000);
        Product pupuk = new Product("P02", "Pupuk", 30000);

        // ArrayList
        ShoppingCart cart = new ShoppingCart();
        cart.addProduct(beras);
        cart.addProduct(pupuk);
        cart.printCart();

        cart.removeProduct(beras);
        cart.printCart();

        // Map + Quantity
        ShoppingCartMap cartMap = new ShoppingCartMap();
        cartMap.addProduct(beras);
        cartMap.addProduct(beras);
        cartMap.addProduct(pupuk);

        cartMap.printCart();
    }
}
