package com.github.rinnn31.pttkhttt.quanlysieuthi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.github.rinnn31.pttkhttt.quanlysieuthi.model.OnlineOrder2;
import com.github.rinnn31.pttkhttt.quanlysieuthi.model.OrderDetail2;

public class OnlineOrderDAO2 extends DAO2 {

    /**
     * Lưu thông tin đơn hàng trực tuyến vào cơ sở dữ liệu.
     * @param order Đơn hàng trực tuyến cần lưu
     * @return true nếu lưu thành công, false nếu thất bại
     */
    public boolean saveOnlineOrder(OnlineOrder2 order) {
        Connection conn = null;
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;
        PreparedStatement stmt3 = null;

        String sql1 = "INSERT INTO tblorder2 (customerId, createdAt, status, type) VALUES (?, ?, ?, ?)";
        String sql2 = "INSERT INTO tblonlineorder2 (orderId, deliveryAddress, deliveryFee, contactPhone) VALUES (?, ?, ?, ?)";
        String sql3 = "INSERT INTO tblorderdetails2 (orderId, itemId, quantity, unitPrice) VALUES (?, ?, ?, ?)";

        try {
            conn = getConnection();
            // Bắt đầu giao dịch, cần rollback nếu có lỗi xảy ra khi thêm đơn hàng hoặc chi tiết đơn hàng
            conn.setAutoCommit(false);

            stmt = conn.prepareStatement(sql1, PreparedStatement.RETURN_GENERATED_KEYS); // Trả về khóa chính được tạo tự động
            stmt2 = conn.prepareStatement(sql2);
            stmt3 = conn.prepareStatement(sql3);

            stmt.setInt(1, order.getCustomer().getId());
            stmt.setTimestamp(2, java.sql.Timestamp.from(order.getCreatedAt()));
            stmt.setString(3, order.getStatus());
            stmt.setString(4, order.getType());

            if (stmt.executeUpdate() > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int orderId = generatedKeys.getInt(1);

                        stmt2.setInt(1, orderId);
                        stmt2.setString(2, order.getDeliveryAddress());
                        stmt2.setBigDecimal(3, order.getDeliveryFee());
                        stmt2.setString(4, order.getContactPhone());
                        if (stmt2.executeUpdate() > 0) {
                            // Thêm chi tiết đơn hàng
                            for (OrderDetail2 detail : order.getDetails()) {
                                stmt3.setInt(1, orderId);
                                stmt3.setInt(2, detail.getItem().getId());
                                stmt3.setInt(3, detail.getQuantity());
                                stmt3.setBigDecimal(4, detail.getUnitPrice());
                                stmt3.addBatch();
                            }

                            if (stmt3.executeBatch().length == order.getDetails().size()) {
                                conn.commit();
                                return true;
                            }
                        }
                    }
                }
            }

            conn.rollback();
            return false;

        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (stmt3 != null) stmt3.close();
                if (conn != null) conn.setAutoCommit(true); // Đặt lại chế độ tự động commit
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return false;
    }
}
