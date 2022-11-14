package com.store.prueba;

import com.store.prueba.entity.Product;
import com.store.prueba.repository.ProductRepository;
import com.store.prueba.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class PruebaApplicationTests {
//
//	ProductRepository productRepository;
//	ProductService productService;
//
//	Product sampleProduct;
//	@BeforeEach
//	void setup(){
//		productRepository = mock(ProductRepository.class);
//		productService = new ProductService(productRepository);
//
//		sampleProduct = new Product();
//		sampleProduct.setId(1);
//		sampleProduct.setSku("FAL-234567654");
//		sampleProduct.setName("Polera");
//		sampleProduct.setBrand("Adidas");
//		sampleProduct.setSize("XXL");
//		sampleProduct.setPrice(20000);
//		sampleProduct.setPrimaryImage("http://polera.png");
//		List<String> otherImages = new ArrayList<>();
//		otherImages.add("http://img1.png");
//		otherImages.add("http://img2.png");
//		sampleProduct.setOtherImages(otherImages);
//	}

//	@Test
//	void entityTest() {
//		when(productRepository.findBySku("FAL-234567654")).thenReturn(sampleProduct);
//
//		Product product = productRepository.findBySku("FAL-234567654");
//		assertNotNull(product);
//		assertTrue(product.getOtherImages().size()>1);
//		assertEquals(20000, product.getPrice());
//		assertNotNull(product.getSize());
//		assertNotNull(product.getBrand());
//		assertNotNull(product.getName());
//		assertNotNull(product.getSku());
//		assertNotNull(product.getPrimaryImage());
//		assertTrue(product.getId()>0);
//
//	}

}
