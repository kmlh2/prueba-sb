package com.store.prueba.controller;


import com.store.prueba.entity.Product;
import com.store.prueba.service.ProductService;
import org.springframework.web.bind.annotation.*;
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
        return Mono.just(productService.findBySku(sku));
    }

    @GetMapping
    private Flux<Product> getAll() {
        return Flux.fromIterable(productService.findAll());
    }

    @PostMapping("/create")
    private Mono<Product> create(@RequestBody Product product) {
        return Mono.just(productService.create(product));
    }

    @PutMapping("/update/{sku}")
    private Mono<Product> update(@PathVariable String sku, @RequestBody Product product) {
        try {
            return Mono.just(productService.update(sku, product));
        } catch (Exception e) {
            return Mono.error(new IllegalArgumentException(e.getMessage()));
        }
    }
}
