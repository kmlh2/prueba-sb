package com.store.prueba.entity;

import com.store.prueba.repository.ProductRepository;
import com.store.prueba.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ProductTest {

    Product sampleProduct;

    @BeforeEach
    void setup(){

        sampleProduct = new Product();
        sampleProduct.setId(1);
        sampleProduct.setSku("FAL-234567654");
        sampleProduct.setName("Polera");
        sampleProduct.setBrand("Adidas");
        sampleProduct.setSize("XXL");
        sampleProduct.setPrice(20000);
        sampleProduct.setPrimaryImage("http://polera.png");
        List<String> otherImages = new ArrayList<>();
        otherImages.add("http://img1.png");
        otherImages.add("http://img2.png");
        sampleProduct.setOtherImages(otherImages);
    }

    @Test
    void setTest(){

        assertNotNull(sampleProduct);
        assertTrue(sampleProduct.getOtherImages().size()>1);
        assertEquals(20000, sampleProduct.getPrice());
        assertNotNull(sampleProduct.getSize());
        assertNotNull(sampleProduct.getBrand());
        assertNotNull(sampleProduct.getName());
        assertNotNull(sampleProduct.getSku());
        assertNotNull(sampleProduct.getPrimaryImage());
        assertTrue(sampleProduct.getId()>0);

    }

}
