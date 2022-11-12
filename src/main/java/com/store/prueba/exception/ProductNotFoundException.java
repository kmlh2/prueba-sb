package com.store.prueba.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String sku) {
        super("Product SKU: " + sku +" is not found.");
    }

}
