package com.store.prueba.controller;


import com.store.prueba.entity.Product;
import com.store.prueba.exception.ProductNotFoundException;
import com.store.prueba.repository.ProductRepository;
import com.store.prueba.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@WebFluxTest(ProductController.class)
public class ProductControllerTest {
    @Autowired
    WebTestClient webTestClient;

    @MockBean
    private ProductService productService;

    Product sampleProduct;
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
    }

    @Test
    void findAll() {

        List<Product> sampleProductList = new ArrayList<>();
        sampleProductList.add(sampleProduct);
        sampleProductList.add(sampleProduct);

        Flux<Product> products = Flux.fromIterable(sampleProductList);

        when(productService.findAll()).thenReturn(products);

        webTestClient.get()
                .uri("/products")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();

    }

    @Test
    void findBySku() {

        Mono<Product> productMono = Mono.just(sampleProduct);

        when(productService.findBySku("FAL-234567654")).thenReturn(productMono);

        webTestClient.get()
                .uri("/products/FAL-234567654")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void create() {

        Mono<Product> productMono = Mono.just(sampleProduct);

        when(productService.create(sampleProduct)).thenReturn(productMono);

        webTestClient.post()
                .uri("/products/create")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(sampleProduct))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void update() {

        Mono<Product> productMono = Mono.just(sampleProduct);

        when(productService.update("FAL-234567654", sampleProduct)).thenReturn(productMono);

        webTestClient.put()
                .uri("/products/update/FAL-234567654")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(sampleProduct))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void delete() {

        Mono<Product> productMono = Mono.just(sampleProduct);

        when(productService.delete("FAL-234567654")).thenReturn(productMono);

        webTestClient.delete()
                .uri("/products/delete/FAL-234567654")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();
    }

}
