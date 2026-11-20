package com.github.rinnn31.pttkhttt.quanlysieuthi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.github.rinnn31.pttkhttt.quanlysieuthi.model.Item2;

public class ItemDAO2 extends DAO2 {

    /**
     * Lấy danh sách các mặt hàng có tên chứa từ khóa tìm kiếm.
     * @param keyword Từ khóa tìm kiếm.
     * @return danh sách các mặt hàng có tên chứa từ khóa tìm kiếm. Nếu không có mặt hàng nào, trả về danh sách rỗng.
     */
    public List<Item2> getItemsContainKeyword(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return new ArrayList<>();
        }

        List<Item2> items = new ArrayList<>();
        String sql = "SELECT * FROM tblitem2 WHERE name LIKE ?";

        Connection conn = getConnection();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + keyword.trim() + "%");
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Item2 item = new Item2(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("model"),
                        rs.getString("brand"),
                        rs.getString("category"),
                        rs.getString("description"),
                        rs.getString("specs"),
                        rs.getString("image"),
                        rs.getInt("stocks"),
                        rs.getBigDecimal("price"),
                        rs.getBigDecimal("originalPrice"),
                        rs.getDouble("weight"),
                        rs.getDouble("volumeM3")
                    );
                    items.add(item);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return items;
    }

    /**
     * Cập nhật thông tin mặt hàng trong CSDL
     * @param item Mặt hàng cần cập nhật
     * @return true nếu cập nhật thành công, false nếu thất bại
     */
    public boolean updateItem(Item2 item) {
        String sql = "UPDATE tblitem2 SET name = ?, model = ?, brand = ?, category = ?, description = ?, specs = ?, image = ?, stocks = ?, price = ?, originalPrice = ?, weight = ?, volumeM3 = ? WHERE id = ?";

        Connection conn = getConnection();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, item.getName());
            stmt.setString(2, item.getModel());
            stmt.setString(3, item.getBrand());
            stmt.setString(4, item.getCategory());
            stmt.setString(5, item.getDescription());
            stmt.setString(6, item.getSpecs());
            stmt.setString(7, item.getImage());
            stmt.setInt(8, item.getStocks());
            stmt.setBigDecimal(9, item.getPrice());
            stmt.setBigDecimal(10, item.getOriginalPrice());
            stmt.setDouble(11, item.getWeight());
            stmt.setDouble(12, item.getVolumeM3());
            stmt.setInt(13, item.getId());

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    /**
     * Lấy thông tin mặt hàng theo ID
     * @param id ID của mặt hàng
     * @return Item2 nếu tìm thấy, null nếu không tìm thấy
     */
    public Item2 getItemById(int id) {
        String sql = "SELECT * FROM tblitem2 WHERE id = ?";

        Connection conn = getConnection();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Item2(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("model"),
                        rs.getString("brand"),
                        rs.getString("category"),
                        rs.getString("description"),
                        rs.getString("specs"),
                        rs.getString("image"),
                        rs.getInt("stocks"),
                        rs.getBigDecimal("price"),
                        rs.getBigDecimal("originalPrice"),
                        rs.getDouble("weight"),
                        rs.getDouble("volumeM3")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Lấy danh sách các mặt hàng theo tập hợp ID
     * @param itemIds Tập hợp ID của các mặt hàng cần lấy
     * @return Danh sách các mặt hàng tương ứng với các ID trong tập hợp. Nếu không có mặt hàng nào, trả về danh sách rỗng.
     */
    public List<Item2> getItemsByIds(Set<Integer> itemIds) {
        if (itemIds == null || itemIds.isEmpty()) {
            return new ArrayList<>();
        }

        List<Item2> items = new ArrayList<>();
        StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM tblitem2 WHERE id IN (");
        for (int i = 0; i < itemIds.size(); i++) {
            sqlBuilder.append("?");
            if (i < itemIds.size() - 1) {
                sqlBuilder.append(",");
            }
        }
        sqlBuilder.append(")");
        String sql = sqlBuilder.toString();

        Connection conn = getConnection();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            int i = 0;
            for (Integer itemId : itemIds) {
                stmt.setInt(++i, itemId);
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Item2 item = new Item2(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("model"),
                        rs.getString("brand"),
                        rs.getString("category"),
                        rs.getString("description"),
                        rs.getString("specs"),
                        rs.getString("image"),
                        rs.getInt("stocks"),
                        rs.getBigDecimal("price"),
                        rs.getBigDecimal("originalPrice"),
                        rs.getDouble("weight"),
                        rs.getDouble("volumeM3")
                    );
                    items.add(item);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return items;
    }
}