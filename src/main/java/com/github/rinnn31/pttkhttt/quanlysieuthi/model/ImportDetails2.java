package com.github.rinnn31.pttkhttt.quanlysieuthi.model;

import java.math.BigDecimal;

public class ImportDetails2 {
    Integer id;
    Item2 item;
    BigDecimal unitPrice;
    Integer quantity;
    
    public ImportDetails2(Integer id, Item2 item, BigDecimal unitPrice, Integer quantity) {
        this.id = id;
        this.item = item;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public ImportDetails2() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Item2 getItem() {
        return item;
    }

    public void setItem(Item2 item) {
        this.item = item;
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
}
