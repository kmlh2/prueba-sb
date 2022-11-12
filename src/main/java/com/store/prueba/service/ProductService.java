package com.store.prueba.service;

import com.store.prueba.entity.Product;
import com.store.prueba.dao.IProductDAO;
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

    @Override
    public Product update(String sku, Product product) throws Exception {

        Product old = findBySku(sku);

        if(old == null){
            throw new Exception("SKU invalid");
        }

        product.setId(old.getId());

        return dao.save(product);
    }


}
