package com.github.rinnn31.pttkhttt.quanlysieuthi.dao;

import java.sql.Connection;

public class DAO2 {
    private static String DB_URL = "jdbc:mysql://localhost:3306/qlst?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static String DB_USER = "root";
    private static String DB_PASSWORD = "root";

    private static Connection conn;

    public static synchronized Connection getConnection() {
        if (conn == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = java.sql.DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return conn;
    }
}
