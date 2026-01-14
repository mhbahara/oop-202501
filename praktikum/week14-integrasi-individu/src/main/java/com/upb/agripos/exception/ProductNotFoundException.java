package com.upb.agripos.exception;

public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(String code) {
        super("Produk dengan kode '" + code + "' tidak ditemukan");
    }
}