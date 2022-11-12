package com.store.prueba.configuration;

import com.store.prueba.exception.InternalServerException;
import com.store.prueba.exception.ProductBadRequestException;
import com.store.prueba.exception.ProductConflictException;
import com.store.prueba.exception.ProductNotFoundException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Configuration
@EnableWebFlux
public class WebConfig {
    @Bean
    public WebExceptionHandler exceptionHandler() {
        return (ServerWebExchange exchange, Throwable ex) -> {
            if (ex instanceof ProductNotFoundException) {
                exchange.getResponse().setStatusCode(HttpStatus.NOT_FOUND);
            }
            if (ex instanceof ProductConflictException) {
                exchange.getResponse().setStatusCode(HttpStatus.CONFLICT);
            }
            if (ex instanceof InternalServerException) {
                exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (ex instanceof ProductBadRequestException) {
                exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
            }


            byte[] bytes = ex.getMessage().getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);

            return exchange.getResponse().writeWith(Flux.just(buffer));
        };
    }
}
