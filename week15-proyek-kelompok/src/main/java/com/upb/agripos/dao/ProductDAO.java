package com.upb.agripos.dao;

import java.util.List;

import com.upb.agripos.model.Product;

public interface ProductDAO {
    void save(Product product) throws Exception;
    void update(Product product) throws Exception;
    void delete(String kode) throws Exception;
    Product findByKode(String kode) throws Exception;
    Product findById(Integer id) throws Exception;
    List<Product> findAll() throws Exception;
    void updateStock(String kode, int newStock) throws Exception;
}