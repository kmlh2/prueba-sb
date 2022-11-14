package com.store.prueba.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.*;

@Entity
@Table(name="products")
public class Product {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id", nullable = false, unique = true)
    private int id;

    @NotNull(message = "SKU cannot be null")
    @Column(name="sku", nullable = false, unique = true)
    private String sku;


    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name must not be empty")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    @Column(name="name", nullable = false)
    private String name;



    @NotNull(message = "Brand cannot be null")
    @NotBlank(message = "Brand must not be empty")
    @Size(min = 3, max = 50, message = "Brand must be between 3 and 50 characters")
    @Column(name="brand", nullable = false)
    private String brand;

    @NotBlank(message = "Size must not be empty")
    @Column(name="size")
    private String size;

    @NotNull(message = "Price cannot be null")
    @Min(value = 1, message = "Price should not be less than 1")
    @Max(value = 99999999, message = "Price should not be greater than 99999999")
    @Column(name="price", nullable = false )
    private int price;

    @NotNull(message = "Primary image cannot be null")
    @NotBlank(message = "Primary image must not be empty")
    @Column(name="primary_image", nullable = false)
    private String primaryImage;

    @ElementCollection
    @CollectionTable(name ="other_images")
    @Fetch(FetchMode.JOIN)
    @Column(name="other_images")
    private List<String> otherImages = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPrimaryImage() {
        return primaryImage;
    }

    public void setPrimaryImage(String primaryImage) {
        this.primaryImage = primaryImage;
    }

    public List<String> getOtherImages() {
        return otherImages;
    }

    public void setOtherImages(List<String> otherImages) {
        this.otherImages = otherImages;
    }
}
