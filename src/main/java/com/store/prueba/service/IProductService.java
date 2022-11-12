package com.store.prueba.service;

import com.store.prueba.entity.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();
    Product findBySku(String sku);
    Product create(Product product);

    Product update(String sku, Product product) throws Exception;
}
