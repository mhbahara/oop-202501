package com.upb.agripos;

public class MainExceptionDemo {
    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Hello, I am [Bunga Maura Aulya]-[240320562] (Week9-exception)");

        ShoppingCart cart = new ShoppingCart();
        Product p1 = new Product("P01", "Pupuk Organik", 25000, 3);
        Product p2 = new Product("P02", "Pestisida", -18000, 10);

        // NegativePriceException
        try {
            cart.addProduct(p2, 2);
        } catch (Exception e) {
            System.out.println("Kesalahan: " + e.getMessage());
        }

        // InvalidQuantityException
        try {
            cart.addProduct(p1, -1);
        } catch (Exception e) {
            System.out.println("Kesalahan: " + e.getMessage());
        }

        // ProductNotFoundException
        try {
            cart.removeProduct(p1);
        } catch (Exception e) {
            System.out.println("Kesalahan: " + e.getMessage());
        }

        // EmptyCartException
        try {
            cart.checkout();
        } catch (Exception e) {
            System.out.println("Kesalahan: " + e.getMessage());
        }

        // InsufficientStockException
        try {
            ShoppingCart cart2 = new ShoppingCart();
            cart2.addProduct(p1, 5); // stok cuma 3
            cart2.checkout();
        } catch (Exception e) {
            System.out.println("Kesalahan: " + e.getMessage());
        }

    }
}