package com.github.rinnn31.pttkhttt.quanlysieuthi.model;

import java.math.BigDecimal;
import java.time.Instant;

public class ImportReceipt2 {
    Integer id;
    Instant createdAt;
    Supplier2 supplier;
    WarehouseStaff2 warehouseStaff;
    BigDecimal total;
    ImportDetails2 details;

    public ImportReceipt2(Integer id, Instant createdAt, Supplier2 supplier, WarehouseStaff2 warehouseStaff,
            BigDecimal total, ImportDetails2 details) {
        this.id = id;
        this.createdAt = createdAt;
        this.supplier = supplier;
        this.warehouseStaff = warehouseStaff;
        this.total = total;
        this.details = details;
    }

    public ImportReceipt2() {
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

    public Supplier2 getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier2 supplier) {
        this.supplier = supplier;
    }

    public WarehouseStaff2 getWarehouseStaff() {
        return warehouseStaff;
    }

    public void setWarehouseStaff(WarehouseStaff2 warehouseStaff) {
        this.warehouseStaff = warehouseStaff;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public ImportDetails2 getDetails() {
        return details;
    }

    public void setDetails(ImportDetails2 details) {
        this.details = details;
    }
}
