package com.upb.agripos.dao;

import com.upb.agripos.model.Product;
import java.util.List;

public interface ProductDAO {
    List<Product> findAll();
    void insert(Product product);
    void delete(String code);
}
