package com.upb.agripos.service;

import java.util.List;

import com.upb.agripos.dao.ProductDAO;
import com.upb.agripos.model.Product;

public class ProductService {

    private final ProductDAO productDAO;

    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public void insert(Product product) throws Exception {
        productDAO.insert(product);
    }

    public void delete(String code) throws Exception {
        productDAO.delete(code);
    }

    public List<Product> findAll() throws Exception {
        return productDAO.findAll();
    }
}
