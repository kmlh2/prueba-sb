package com.store.prueba.validator;

import com.store.prueba.entity.Product;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Optional;

@Component
public class ProductValidator {

    public Optional<String> validate(Product product){
        Validator validator = Validation.buildDefaultValidatorFactory()
                .getValidator();

        return validator.validate(product)
                .stream()
                .map(ConstraintViolation::getMessage)
                .reduce((s, s2) -> s + ",\n" + s2);
    }
}
