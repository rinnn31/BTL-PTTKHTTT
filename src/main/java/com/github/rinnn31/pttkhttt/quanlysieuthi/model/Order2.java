package com.github.rinnn31.pttkhttt.quanlysieuthi.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public class Order2 {
    public static String TYPE_INSTORE = "instore";
    public static String TYPE_ONLINE = "online";

    public static String STATUS_PENDING = "pending";
    public static String STATUS_APPROVED = "approved";
    public static String STATUS_REJECTED = "rejected";
    public static String STATUS_PAID = "paid";

    Integer id;
    Instant createdAt;
    String status;
    String type;
    Customer2 customer;
    List<OrderDetail2> details;
    
    public Order2(Integer id, Instant createdAt, String status, BigDecimal total, String type, Customer2 customer,
            List<OrderDetail2> details) {
        this.id = id;
        this.createdAt = createdAt;
        this.status = status;
        this.type = type;
        this.customer = customer;
        this.details = details;
    }

    public Order2() {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Customer2 getCustomer() {
        return customer;
    }

    public void setCustomer(Customer2 customer) {
        this.customer = customer;
    }

    public List<OrderDetail2> getDetails() {
        return details;
    }

    public void setDetails(List<OrderDetail2> details) {
        this.details = details;
    }

    
}
