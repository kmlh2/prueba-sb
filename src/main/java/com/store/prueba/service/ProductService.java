package com.store.prueba.service;

import com.store.prueba.entity.Product;
import com.store.prueba.dao.IProductDAO;
import com.store.prueba.exception.InternalServerException;
import com.store.prueba.exception.ProductBadRequestException;
import com.store.prueba.exception.ProductConflictException;
import com.store.prueba.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductDAO dao;

    @Override
    public Flux<Product> findAll() {
        return Flux.fromIterable(dao.findAll());
    }

    @Override
    public Mono<Product> findBySku(String sku) {

        Product product = dao.findBySku(sku);

        if(product == null){
            return Mono.error(new ProductNotFoundException(sku));
        }

        return Mono.just(product);
    }

    @Override
    public Mono<Product> create(Product product) {
        Product newProduct = null;
        try {
            newProduct = dao.save(product);

        }catch (Exception e){
            System.out.println("kml: "+e.hashCode());

            if(e.getMessage().contains("ConstraintViolationException")){
                return Mono.error(new ProductConflictException(product.getSku()));
            }
            if(e.getMessage().contains("not-null")){
                return Mono.error(new ProductBadRequestException());
            }

           return Mono.error(new InternalServerException(e.getMessage()));

        }

        return Mono.just(newProduct);
    }

    @Override
    public Mono<Product> update(String sku, Product product){

        Product old = dao.findBySku(sku);

        if(old == null){
            return Mono.error(new ProductNotFoundException(sku));
        }

        product.setId(old.getId());

        return Mono.just(dao.save(product));
    }

    @Override
    public Mono<Product> delete(String sku)  {

        Product toDelete = dao.findBySku(sku);
            if(toDelete == null){
                Mono.error(new ProductNotFoundException(sku));
            }
            dao.delete(toDelete);

            return Mono.just(toDelete);


    }


}
