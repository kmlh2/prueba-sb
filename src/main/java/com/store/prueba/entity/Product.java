package com.store.prueba.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="products")
public class Product {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private int id;

    @Column(name="sku", nullable = false)
    private String sku;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="brand", nullable = false)
    private String brand;

    @Column(name="size")
    private String size;

    @Column(name="price", nullable = false )
    private int price;

    @Column(name="primary_image", nullable = false)
    private String primaryImage;

    @Column(name="other_image")
    private String[] otherImages;

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

    public String[] getOtherImages() {
        return otherImages;
    }

    public void setOtherImages(String[] otherImages) {
        this.otherImages = otherImages;
    }
}
