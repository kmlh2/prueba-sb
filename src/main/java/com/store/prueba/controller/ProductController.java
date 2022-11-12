package com.store.prueba.controller;


import com.store.prueba.entity.Product;
import com.store.prueba.exception.ProductNotFoundException;
import com.store.prueba.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{sku}")
    private Mono<Product> getBySku(@PathVariable String sku) {
        return productService.findBySku(sku);
    }

    @GetMapping
    private Flux<Product> getAll() {
        return productService.findAll();
    }

    @PostMapping("/create")
    private Mono<Product> create(@RequestBody Product product) {
        return productService.create(product);
    }

    @PutMapping("/update/{sku}")
    private Mono<Product> update(@PathVariable String sku, @RequestBody Product product) {
        return productService.update(sku, product);

    }

    @DeleteMapping("/delete/{sku}")
    private Mono<Product> delete(@PathVariable String sku) {
        return productService.delete(sku);
//                .switchIfEmpty(Mono.error(new ProductNotFoundException(sku)));

    }
}
