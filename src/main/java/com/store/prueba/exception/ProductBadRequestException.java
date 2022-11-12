package com.store.prueba.exception;

public class ProductBadRequestException extends RuntimeException {
    public ProductBadRequestException() {
        super("Product is not valid (needs non-null values)");
    }

}
