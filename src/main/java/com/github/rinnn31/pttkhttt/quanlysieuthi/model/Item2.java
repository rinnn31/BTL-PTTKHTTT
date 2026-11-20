package com.github.rinnn31.pttkhttt.quanlysieuthi.model;

import java.math.BigDecimal;

public class Item2 {
    Integer id;
    String name;
    String model;
    String brand;
    String category;
    String description;
    String specs;
    String image;
    Integer stocks;
    BigDecimal price;
    BigDecimal originalPrice;
    Double weight;
    Double volumeM3;

    public Item2(Integer id, String name, String model, String brand, String category, String description, String specs,
            String image, Integer stocks, BigDecimal price, BigDecimal originalPrice, Double weight, Double volumeM3) {
        this.id = id;
        this.name = name;
        this.model = model;
        this.brand = brand;
        this.category = category;
        this.description = description;
        this.specs = specs;
        this.image = image;
        this.stocks = stocks;
        this.price = price;
        this.originalPrice = originalPrice;
        this.weight = weight;
        this.volumeM3 = volumeM3;
    }

    public Item2() {
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpecs() {
        return specs;
    }

    public void setSpecs(String specs) {
        this.specs = specs;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getStocks() {
        return stocks;
    }

    public void setStocks(Integer stocks) {
        this.stocks = stocks;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getVolumeM3() {
        return volumeM3;
    }

    public void setVolumeM3(Double volumeM3) {
        this.volumeM3 = volumeM3;
    }
}
