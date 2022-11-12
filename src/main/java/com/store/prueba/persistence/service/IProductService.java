package com.store.prueba.persistence.service;

import com.store.prueba.entity.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();
    Product findBySku(String sku);
    Product create(Product product);
}
