package com.upb.agripos.dao;

import com.upb.agripos.model.Product;
import com.upb.agripos.exception.DatabaseException;
import java.util.List;

/**
 * Bab 11: DAO Interface (Dependency Inversion Principle)
 */
public interface ProductDAO {
    List<Product> findAll() throws DatabaseException;
    Product findByCode(String code) throws DatabaseException;
    void insert(Product product) throws DatabaseException;
    void update(Product product) throws DatabaseException;
    void delete(String code) throws DatabaseException;
}