package com.store.prueba.exception;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class ExceptionTest {

    @Test
    void internalServerException()  {
        Mono<Object> strMono = Mono.error(new InternalServerException("Exception Occured")).log();
        StepVerifier.create(strMono)
                .expectError(InternalServerException.class)
                .verify();
    }
    @Test
    void productBadRequestException()  {
        Mono<Object> strMono = Mono.error(new ProductBadRequestException()).log();
        StepVerifier.create(strMono)
                .expectError(ProductBadRequestException.class)
                .verify();
    }
    @Test
    void productConflictException()  {
        Mono<Object> strMono = Mono.error(new ProductConflictException("Exception Occured")).log();
        StepVerifier.create(strMono)
                .expectError(ProductConflictException.class)
                .verify();
    }
    @Test
    void productNotFoundException()  {
        Mono<Object> strMono = Mono.error(new ProductNotFoundException("Exception Occured")).log();
        StepVerifier.create(strMono)
                .expectError(ProductNotFoundException.class)
                .verify();
    }
}
