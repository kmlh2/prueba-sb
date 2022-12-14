package com.store.prueba.service;

import com.store.prueba.entity.Product;
import com.store.prueba.repository.ProductRepository;
import com.store.prueba.exception.InternalServerException;
import com.store.prueba.exception.ProductBadRequestException;
import com.store.prueba.exception.ProductConflictException;
import com.store.prueba.exception.ProductNotFoundException;
import com.store.prueba.validator.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductValidator productValidator;

    public ProductService(ProductRepository productRepository, ProductValidator productValidator){
        this.productRepository = productRepository;
        this.productValidator = productValidator;
    }

    @Override
    public Flux<Product> findAll() {
        return Flux.fromIterable(productRepository.findAll());
    }

    @Override
    public Mono<Product> findBySku(String sku) {

        Product product = productRepository.findBySku(sku);

        if(product == null){
            return Mono.error(new ProductNotFoundException(sku));
        }

        return Mono.just(product);
    }

    @Override
    public Mono<Product> create(Product product) {

        Optional<String> validations = productValidator.validate(product);
        if(validations.isPresent()){
            return Mono.error(new ProductBadRequestException(validations.get()));
        }

        Product newProduct = null;
        try {
            newProduct = productRepository.save(product);

        }catch (Exception e){

            if(e.getMessage().contains("ConstraintViolationException")){
                return Mono.error(new ProductConflictException(product.getSku()));
            }
            if(e.getMessage().contains("not-null")){
                return Mono.error(new ProductBadRequestException("Product is not valid (needs non-null values)"));
            }

           return Mono.error(new InternalServerException(e.getMessage()));

        }

        return Mono.just(newProduct);
    }

    @Override
    public Mono<Product> update(String sku, Product product){

        Optional<String> validations = productValidator.validate(product);
        if(validations.isPresent()){
            return Mono.error(new ProductBadRequestException(validations.get()));
        }

        Product old = productRepository.findBySku(sku);

        if(old == null){
            return Mono.error(new ProductNotFoundException(sku));
        }

        product.setId(old.getId());

        return Mono.just(productRepository.save(product));
    }

    @Override
    public Mono<Product> delete(String sku)  {

        Product toDelete = productRepository.findBySku(sku);
        if(toDelete == null){
            return Mono.error(new ProductNotFoundException(sku));
        }
        productRepository.delete(toDelete);

        return Mono.just(toDelete);

    }



}
