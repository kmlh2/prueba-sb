package com.store.prueba.persistence.service;

import com.store.prueba.entity.Product;
import com.store.prueba.persistence.dao.IProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductDAO dao;

    @Override
    public List<Product> findAll() {
        return dao.findAll();
    }

    @Override
    public Product findBySku(String sku) {
        return dao.findBySku(sku);
    }

    @Override
    public Product create(Product product) {
        return dao.save(product);
    }


}
