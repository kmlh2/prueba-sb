package com.store.prueba.controller;


//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;


import com.store.prueba.entity.Product;
import com.store.prueba.persistence.service.ProductService;
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
    private Mono<Product> getProductBySku(@PathVariable String sku) {
        return Mono.just(productService.findBySku(sku));
    }

    @GetMapping
    private Flux<Product> getAllProducts() {
        return Flux.fromIterable(productService.findAll());
    }

    @PostMapping("/create")
    private Mono<Product> updateEmployee(@RequestBody Product product) {
        return Mono.just(productService.create(product));
    }

//    @PostMapping("/update")
//    private Mono<Product> updateEmployee(@RequestBody Employee employee) {
//        return productService.updateEmployee(employee);
//    }
}
