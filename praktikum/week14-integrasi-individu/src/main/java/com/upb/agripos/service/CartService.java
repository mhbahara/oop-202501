package com.upb.agripos.service;

import com.upb.agripos.model.Cart;
import com.upb.agripos.model.Product;

public class CartService {

    private Cart cart = new Cart();

    public void addToCart(Product product, int qty) {
        cart.addItem(product, qty);
    }

    public double getTotal() {
        return cart.getTotal();
    }

    public Cart getCart() {
        return cart;
    }
}
