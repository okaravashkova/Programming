package com.cigshop.dao;

import com.cigshop.model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao extends BaseDao {

    public List<Category> getAllCategories() {
        List<Category> list = new ArrayList<>();
        String sql = "SELECT * FROM categories ORDER BY name";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Category c = new Category();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.err.println("Ошибка получения категорий: " + e.getMessage());
        }
        return list;
    }
}
