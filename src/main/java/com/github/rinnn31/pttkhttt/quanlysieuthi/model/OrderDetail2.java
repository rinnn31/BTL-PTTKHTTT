package com.github.rinnn31.pttkhttt.quanlysieuthi.model;

import java.math.BigDecimal;

public class OrderDetail2 {
    Integer id;
    BigDecimal unitPrice;
    Integer quantity;
    Item2 item;

    public OrderDetail2(Integer id, BigDecimal unitPrice, Integer quantity, Item2 item) {
        this.id = id;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.item = item;
    }

    public OrderDetail2() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Item2 getItem() {
        return item;
    }

    public void setItem(Item2 item) {
        this.item = item;
    }
}
