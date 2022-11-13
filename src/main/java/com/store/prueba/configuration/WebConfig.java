package com.store.prueba.configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.prueba.dto.ResponseError;
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

import java.nio.charset.StandardCharsets;
import java.util.Objects;

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

            exchange.getResponse().getHeaders().add("Content-Type", "application/json");

            byte[] bytes = errorToBytes(exchange, ex);

            DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);

            return exchange.getResponse().writeWith(Flux.just(buffer));
        };
    }

    private byte[] errorToBytes (ServerWebExchange exchange, Throwable ex){
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseError errorsResponse = new ResponseError(
                Objects.requireNonNull(exchange.getResponse().getStatusCode()).value(),
                ex.getMessage()
        );

        try {
            return objectMapper.writeValueAsBytes(errorsResponse);
        } catch (JsonProcessingException e) {
            return ex.getMessage().getBytes(StandardCharsets.UTF_8);
        }
    }
}

