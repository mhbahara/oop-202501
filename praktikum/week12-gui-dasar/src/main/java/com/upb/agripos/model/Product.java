package com.upb.agripos.model;

/**
 * Model Product untuk Agri-POS
 * Sesuai dengan desain Bab 6
 */
public class Product {
    private String code;
    private String name;
    private double price;
    private int stock;

    // Constructor kosong
    public Product() {
    }

    // Constructor dengan parameter
    public Product(String code, String name, double price, int stock) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    // Getters and Setters
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return code + " - " + name + " | Rp " + price + " | Stok: " + stock;
    }
}