package com.github.rinnn31.pttkhttt.quanlysieuthi.model;

import java.math.BigDecimal;

public class RevenueStatistic2 {
    BigDecimal totalRevenue;
    BigDecimal instoreRevenue;
    BigDecimal onlineRevenue;
    Integer totalOrders;

    public RevenueStatistic2(BigDecimal totalRevenue, BigDecimal instoreRevenue, BigDecimal onlineRevenue, Integer totalOrders) {
        this.totalRevenue = totalRevenue;
        this.instoreRevenue = instoreRevenue;
        this.onlineRevenue = onlineRevenue;
        this.totalOrders = totalOrders;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public BigDecimal getInstoreRevenue() {
        return instoreRevenue;
    }

    public void setInstoreRevenue(BigDecimal instoreRevenue) {
        this.instoreRevenue = instoreRevenue;
    }

    public BigDecimal getOnlineRevenue() {
        return onlineRevenue;
    }

    public void setOnlineRevenue(BigDecimal onlineRevenue) {
        this.onlineRevenue = onlineRevenue;
    }

    public Integer getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(Integer totalOrders) {
        this.totalOrders = totalOrders;
    }
}
