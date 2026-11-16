package com.github.rinnn31.pttkhttt.quanlysieuthi.model;

import java.time.Instant;

public class ExportReceipt2 {
    Integer id;
    Instant createdAt;
    WarehouseStaff2 warehouseStaff;
    DeliveryStaff2 deliveryStaff;
    OnlineOrder2 onlineOrder;
    
    public ExportReceipt2(Integer id, Instant createdAt, WarehouseStaff2 warehouseStaff, DeliveryStaff2 deliveryStaff,
            OnlineOrder2 onlineOrder) {
        this.id = id;
        this.createdAt = createdAt;
        this.warehouseStaff = warehouseStaff;
        this.deliveryStaff = deliveryStaff;
        this.onlineOrder = onlineOrder;
    }

    public ExportReceipt2() {
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

    public WarehouseStaff2 getWarehouseStaff() {
        return warehouseStaff;
    }

    public void setWarehouseStaff(WarehouseStaff2 warehouseStaff) {
        this.warehouseStaff = warehouseStaff;
    }

    public DeliveryStaff2 getDeliveryStaff() {
        return deliveryStaff;
    }

    public void setDeliveryStaff(DeliveryStaff2 deliveryStaff) {
        this.deliveryStaff = deliveryStaff;
    }

    public OnlineOrder2 getOnlineOrder() {
        return onlineOrder;
    }

    public void setOnlineOrder(OnlineOrder2 onlineOrder) {
        this.onlineOrder = onlineOrder;
    }
}
