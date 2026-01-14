package com.upb.agripos.service;

import java.util.ArrayList;
import java.util.List;

import com.upb.agripos.model.Product;

public class ProductService {

    private final List<Product> products = new ArrayList<>();

    public void insert(Product product) {
        products.add(product);
    }

    public List<Product> getAll() {
        return products;
    }
}
