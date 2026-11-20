package com.github.rinnn31.pttkhttt.quanlysieuthi.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public class OnlineOrder2 extends Order2 {
    String deliveryAddress;
    String contactPhone;
    BigDecimal deliveryFee;
    
    public OnlineOrder2(Integer id, Instant createdAt, String status, BigDecimal total, String type, Customer2 customer,
            List<OrderDetails2> details, String deliveryAddress, String contactPhone, BigDecimal deliveryFee) {
        super(id, createdAt, status, total, type, customer, details);
        this.deliveryAddress = deliveryAddress;
        this.contactPhone = contactPhone;
        this.deliveryFee = deliveryFee;
    }

    public OnlineOrder2() {
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public BigDecimal getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(BigDecimal deliveryFee) {
        this.deliveryFee = deliveryFee;
    }
}
