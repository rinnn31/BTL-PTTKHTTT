package com.github.rinnn31.pttkhttt.quanlysieuthi.model;

import java.math.BigDecimal;

public class OrderDetails2 {
    Integer id;
    BigDecimal unitPrice;
    Integer quantity;
    Item2 item;

    public OrderDetails2(Integer id, BigDecimal unitPrice, Integer quantity, Item2 item) {
        this.id = id;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.item = item;
    }

    public OrderDetails2() {
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
