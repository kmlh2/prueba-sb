package com.store.prueba.exception;

public class ProductConflictException extends RuntimeException {
    public ProductConflictException(String sku) {
        super("Product SKU: " + sku +" Duplicated");
    }

}
