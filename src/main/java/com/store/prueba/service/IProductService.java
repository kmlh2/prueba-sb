package com.store.prueba.service;

import com.store.prueba.entity.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IProductService {
    Flux<Product> findAll();
    Mono<Product> findBySku(String sku);
    Mono<Product> create(Product product);

    Mono<Product> update(String sku, Product product);
    Mono<Product> delete(String sku);
}
