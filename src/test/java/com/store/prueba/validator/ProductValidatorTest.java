package com.store.prueba.validator;

import com.store.prueba.entity.Product;
import com.store.prueba.service.IProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ProductValidatorTest {

    @Autowired
    ProductValidator productValidator;


    Product sampleProduct;

    @BeforeEach
    void setup(){
        sampleProduct = new Product();
        sampleProduct.setSku("FAL-234567654");
        sampleProduct.setName("Po");
        sampleProduct.setBrand("Adidas");
        sampleProduct.setSize("XXL");
        sampleProduct.setPrice(1);
        sampleProduct.setPrimaryImage("http://polera.png");
        List<String> otherImages = new ArrayList<>();
        otherImages.add("http://img1.png");
        otherImages.add("http://img2.png");
        sampleProduct.setOtherImages(otherImages);

        productValidator = new ProductValidator();

    }

    @Test
    void runValidator(){

        assertNotNull(productValidator.validate(sampleProduct));

    }
}
