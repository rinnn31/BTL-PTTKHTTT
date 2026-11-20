package com.github.rinnn31.pttkhttt.quanlysieuthi.model;

import java.math.BigDecimal;

public class ItemStatistic2 extends Item2 {
    BigDecimal totalRevenue;
    Integer totalSoldQuantity;
    Integer totalOrders;
    Integer totalImports;
    Integer totalImportQuantity;
    
    public ItemStatistic2(Integer id, String name, String model, String brand, String category, String description,
            String specs, String image, Integer stocks, BigDecimal price, BigDecimal originalPrice, Double weight, Double volumeM3, BigDecimal totalRevenue,
            Integer totalSoldQuantity, Integer totalOrders, Integer totalImports, Integer totalImportQuantity) {
        super(id, name, model, brand, category, description, specs, image, stocks, price, originalPrice, weight, volumeM3);
        this.totalRevenue = totalRevenue;
        this.totalSoldQuantity = totalSoldQuantity;
        this.totalOrders = totalOrders;
        this.totalImports = totalImports;
        this.totalImportQuantity = totalImportQuantity;
    }

    public ItemStatistic2() {
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public Integer getTotalSoldQuantity() {
        return totalSoldQuantity;
    }

    public void setTotalSoldQuantity(Integer totalSoldQuantity) {
        this.totalSoldQuantity = totalSoldQuantity;
    }

    public Integer getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(Integer totalOrders) {
        this.totalOrders = totalOrders;
    }

    public Integer getTotalImports() {
        return totalImports;
    }

    public void setTotalImports(Integer totalImports) {
        this.totalImports = totalImports;
    }

    public Integer getTotalImportQuantity() {
        return totalImportQuantity;
    }

    public void setTotalImportQuantity(Integer totalImportQuantity) {
        this.totalImportQuantity = totalImportQuantity;
    }
}
