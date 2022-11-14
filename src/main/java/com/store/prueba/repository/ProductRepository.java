package com.store.prueba.repository;

import com.store.prueba.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findBySku(String sku);
    List<Product> findAll();

}
