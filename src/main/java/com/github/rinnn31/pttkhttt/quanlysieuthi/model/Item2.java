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
    
    public Item2(Integer id, String name, String model, String brand, String category, String description, String specs,
            String image, Integer stocks, BigDecimal price, BigDecimal originalPrice) {
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
}
