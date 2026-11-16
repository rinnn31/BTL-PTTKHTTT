package com.github.rinnn31.pttkhttt.quanlysieuthi.model;

import java.math.BigDecimal;

public class Staff2 extends User2 {
    public static String ROLE_MANAGER = "manager";
    public static String ROLE_SALES = "sales";
    public static String ROLE_DELIVERY = "delivery";
    public static String ROLE_WAREHOUSE = "warehouse";

    BigDecimal salary;
    String role;

    public Staff2(Integer id, String fullName, String password, String phoneNumber, String email, String address,
            String type, BigDecimal salary, String role) {
        super(id, fullName, password, phoneNumber, email, address, type);
        this.salary = salary;
        this.role = role;
    }

    public Staff2() {
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
