package com.github.rinnn31.pttkhttt.quanlysieuthi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.github.rinnn31.pttkhttt.quanlysieuthi.model.Customer2;
import com.github.rinnn31.pttkhttt.quanlysieuthi.model.User2;

public class UserDAO2 extends DAO2 {
    public User2 getUserByUsername(String username) {
        String sql = "SELECT * FROM tbluser2 WHERE username = ?";
        Connection conn = getConnection();
            try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String type = rs.getString("type");
                    if (User2.TYPE_CUSTOMER.equals(type)) {
                        return new Customer2(
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getString("fullName"),
                            rs.getString("password"),
                            rs.getString("phoneNumber"),
                            rs.getString("email"),
                            rs.getString("address"),
                            type
                        );
                    } else if (User2.TYPE_STAFF.equals(type)) {
                        String sql2 = "SELECT * FROM tblstaff2 WHERE userId = ?";
                        try (PreparedStatement stmt2 = conn.prepareStatement(sql2)) {
                            stmt2.setInt(1, rs.getInt("id"));
                            try (ResultSet rs2 = stmt2.executeQuery()) {
                                if (rs2.next()) {
                                    return new com.github.rinnn31.pttkhttt.quanlysieuthi.model.Staff2(
                                        rs.getInt("id"),
                                        rs.getString("username"),
                                        rs.getString("fullName"),
                                        rs.getString("password"),
                                        rs.getString("phoneNumber"),
                                        rs.getString("email"),
                                        rs.getString("address"),
                                        type,
                                        rs2.getBigDecimal("salary"),
                                        rs2.getString("role")
                                    );
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
