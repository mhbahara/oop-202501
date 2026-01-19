package com.upb.agripos.dao;

import java.util.List;

import com.upb.agripos.model.Product;

public interface ProductDAO {

    void insert(Product product) throws Exception;
    void delete(String code) throws Exception;
    List<Product> findAll() throws Exception;
}
