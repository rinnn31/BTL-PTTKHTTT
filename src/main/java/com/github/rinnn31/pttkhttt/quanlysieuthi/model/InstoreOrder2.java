package com.github.rinnn31.pttkhttt.quanlysieuthi.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public class InstoreOrder2 extends Order2 {
    SalesStaff2 salesStaff;

    public InstoreOrder2(Integer id, Instant createdAt, String status, BigDecimal total, String type, Customer2 customer,
            List<OrderDetail2> details, SalesStaff2 salesStaff) {
        super(id, createdAt, status, total, type, customer, details);
        this.salesStaff = salesStaff;
    }

    public SalesStaff2 getSalesStaff() {
        return salesStaff;
    }

    public void setSalesStaff(SalesStaff2 salesStaff) {
        this.salesStaff = salesStaff;
    }
}
