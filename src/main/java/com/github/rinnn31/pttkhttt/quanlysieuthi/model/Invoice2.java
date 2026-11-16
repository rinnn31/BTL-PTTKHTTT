package com.github.rinnn31.pttkhttt.quanlysieuthi.model;

import java.math.BigDecimal;
import java.time.Instant;

public class Invoice2 {
    public static String PAYMENT_METHOD_CASH = "cash";
    public static String PAYMENT_METHOD_BANK = "bank";

    Integer id;
    Instant createdAt;
    BigDecimal total;
    String paymentMethod;
    Order2 order;
    
    public Invoice2(Integer id, Instant createdAt, BigDecimal total, String paymentMethod, Order2 order) {
        this.id = id;
        this.createdAt = createdAt;
        this.total = total;
        this.paymentMethod = paymentMethod;
        this.order = order;
    }

    public Invoice2() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Order2 getOrder() {
        return order;
    }

    public void setOrder(Order2 order) {
        this.order = order;
    }
}
