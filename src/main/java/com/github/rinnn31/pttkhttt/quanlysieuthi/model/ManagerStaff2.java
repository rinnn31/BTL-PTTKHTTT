package com.github.rinnn31.pttkhttt.quanlysieuthi.model;

import java.math.BigDecimal;

public class ManagerStaff2 extends Staff2 {

    public ManagerStaff2(Integer id, String fullName, String password, String phoneNumber, String email, String address,
            String type, BigDecimal salary, String role) {
        super(id, fullName, password, phoneNumber, email, address, type, salary, role);
    }

    public ManagerStaff2() {
    }
    
}
