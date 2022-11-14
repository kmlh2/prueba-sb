package com.store.prueba.service;

import com.store.prueba.entity.Product;
import com.store.prueba.exception.InternalServerException;
import com.store.prueba.exception.ProductNotFoundException;
import com.store.prueba.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@WebFluxTest(ProductService.class)
public class ProductServiceTest {

    @Autowired
    IProductService productService;

    @MockBean
    private ProductRepository productRepository;

    Product sampleProduct;
    Exception exception;

    @BeforeEach
    void setup(){

        sampleProduct = new Product();
        sampleProduct.setSku("FAL-234567654");
        sampleProduct.setName("Polera");
        sampleProduct.setBrand("Adidas");
        sampleProduct.setSize("XXL");
        sampleProduct.setPrice(20000);
        sampleProduct.setPrimaryImage("http://polera.png");
        List<String> otherImages = new ArrayList<>();
        otherImages.add("http://img1.png");
        otherImages.add("http://img2.png");
        sampleProduct.setOtherImages(otherImages);

        exception = new Exception("ConstraintViolationException");
    }

    @Test
    void findAll() {

        List<Product> sampleProductList = new ArrayList<>();
        sampleProductList.add(sampleProduct);
        sampleProductList.add(sampleProduct);

        Flux<Product> productsExpected = Flux.fromIterable(sampleProductList);

        when(productRepository.findAll()).thenReturn(sampleProductList);

        Flux<Product> productsActual = productService.findAll();

        assertEquals(productsExpected.toStream().toArray().length, productsActual.toStream().toArray().length);

    }


    @Test
    void findBySku() {
        when(productRepository.findBySku("FAL-234567654")).thenReturn(sampleProduct);
        assertEquals(productService.findBySku("FAL-234567654").block().getName(), "Polera" );
    }


    @Test
    void findBySkuNotFound() {
        when(productRepository.findBySku("FAL-234567654")).thenReturn(null);
        Mono<Product> productNotFound = productService.findBySku("FAL-234567654");
        StepVerifier.create(productNotFound)
                    .expectErrorMatches(throwable -> throwable instanceof ProductNotFoundException &&
                        throwable.getMessage().equals("Product SKU: FAL-234567654 is not found.")).verify();
  }


    @Test()
    void create() {

        when(productRepository.save(sampleProduct)).thenReturn(sampleProduct);
        assertEquals(productService.create(sampleProduct).block().getSize(), "XXL" );
    }


//    @Test
//    void createWithProductConflictException()  {
//
//    }
//    @Test
//    void createWithProductBadRequestException() {
//    }
//    @Test
//    void createWithInternalServerException() {
//    }

    @Test
    void update() {
        Mono<Product> productMono = Mono.just(sampleProduct);
        when(productRepository.findBySku("FAL-234567654")).thenReturn(sampleProduct);
        when(productRepository.save(sampleProduct)).thenReturn(sampleProduct);
        assertEquals(productService.update("FAL-234567654",sampleProduct).block().getBrand(), "Adidas" );
    }

    @Test
    void updateNotFound() {
        when(productRepository.findBySku("FAL-234567654")).thenReturn(null);

        Mono<Product> productNotFound = productService.update("FAL-234567654",sampleProduct);

        StepVerifier.create(productNotFound)
                    .expectErrorMatches(throwable -> throwable instanceof ProductNotFoundException &&
                        throwable.getMessage().equals("Product SKU: FAL-234567654 is not found.")).verify();
    }

    @Test
    void delete() {

        Mono<Product> productMono = Mono.just(sampleProduct);
        when(productRepository.findBySku("FAL-234567654")).thenReturn(sampleProduct);

        assertEquals(productService.delete("FAL-234567654").block().getBrand(), "Adidas" );
    }

    @Test
    void deleteNotFound() {
        when(productRepository.findBySku("FAL-234567654")).thenReturn(null);

        Mono<Product> productNotFound = productService.delete("FAL-234567654");

        StepVerifier.create(productNotFound)
                .expectErrorMatches(throwable -> throwable instanceof ProductNotFoundException &&
                        throwable.getMessage().equals("Product SKU: FAL-234567654 is not found.")).verify();
    }
}
