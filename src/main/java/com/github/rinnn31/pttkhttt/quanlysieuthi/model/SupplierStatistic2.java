package com.github.rinnn31.pttkhttt.quanlysieuthi.model;

import java.math.BigDecimal;

public class SupplierStatistic2 extends Supplier2 {
    Integer totalImportQuantity;
    BigDecimal totalImportAmount;
    Integer totalImportReceipts;
    
    public SupplierStatistic2(Integer id, String name, String address, String phoneNumber, String email, String note,
            Integer totalImportQuantity, BigDecimal totalImportAmount, Integer totalImportReceipts) {
        super(id, name, address, phoneNumber, email, note);
        this.totalImportQuantity = totalImportQuantity;
        this.totalImportAmount = totalImportAmount;
        this.totalImportReceipts = totalImportReceipts;
    }

    public Integer getTotalImportQuantity() {
        return totalImportQuantity;
    }

    public void setTotalImportQuantity(Integer totalImportQuantity) {
        this.totalImportQuantity = totalImportQuantity;
    }

    public BigDecimal getTotalImportAmount() {
        return totalImportAmount;
    }

    public void setTotalImportAmount(BigDecimal totalImportAmount) {
        this.totalImportAmount = totalImportAmount;
    }

    public Integer getTotalImportReceipts() {
        return totalImportReceipts;
    }

    public void setTotalImportReceipts(Integer totalImportReceipts) {
        this.totalImportReceipts = totalImportReceipts;
    }

    
}
