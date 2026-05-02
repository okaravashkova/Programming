package com.cigshop.dao;

import com.cigshop.model.CartItem;
import com.cigshop.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDao extends BaseDao {

    public List<CartItem> getByUser(int userId) {
        List<CartItem> items = new ArrayList<>();
        String sql = "SELECT c.id, c.quantity, p.id AS pid, p.name, p.description, p.price, p.category_id " +
                     "FROM cart c JOIN products p ON c.product_id = p.id " +
                     "WHERE c.user_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product();
                    product.setId(rs.getInt("pid"));
                    product.setName(rs.getString("name"));
                    product.setDescription(rs.getString("description"));
                    product.setPrice(rs.getDouble("price"));
                    product.setCategoryId(rs.getInt("category_id"));

                    CartItem item = new CartItem();
                    item.setId(rs.getInt("id"));
                    item.setProduct(product);
                    item.setQuantity(rs.getInt("quantity"));
                    items.add(item);
                }
            }
        } catch (SQLException e) {
            System.err.println("Ошибка получения корзины: " + e.getMessage());
        }
        return items;
    }

    public boolean addItem(int userId, int productId) {
        // Если товар уже в корзине — увеличить количество
        String checkSql = "SELECT id, quantity FROM cart WHERE user_id = ? AND product_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement checkPs = conn.prepareStatement(checkSql)) {
            checkPs.setInt(1, userId);
            checkPs.setInt(2, productId);
            try (ResultSet rs = checkPs.executeQuery()) {
                if (rs.next()) {
                    int cartId = rs.getInt("id");
                    int qty = rs.getInt("quantity");
                    String updateSql = "UPDATE cart SET quantity = ? WHERE id = ?";
                    try (PreparedStatement updatePs = conn.prepareStatement(updateSql)) {
                        updatePs.setInt(1, qty + 1);
                        updatePs.setInt(2, cartId);
                        return updatePs.executeUpdate() > 0;
                    }
                }
            }
            String insertSql = "INSERT INTO cart (user_id, product_id, quantity) VALUES (?, ?, 1)";
            try (PreparedStatement insertPs = conn.prepareStatement(insertSql)) {
                insertPs.setInt(1, userId);
                insertPs.setInt(2, productId);
                return insertPs.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            System.err.println("Ошибка добавления в корзину: " + e.getMessage());
            return false;
        }
    }

    public boolean removeItem(int cartItemId, int userId) {
        String sql = "DELETE FROM cart WHERE id = ? AND user_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cartItemId);
            ps.setInt(2, userId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Ошибка удаления из корзины: " + e.getMessage());
            return false;
        }
    }

    public void clearCart(int userId) {
        String sql = "DELETE FROM cart WHERE user_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Ошибка очистки корзины: " + e.getMessage());
        }
    }
}
