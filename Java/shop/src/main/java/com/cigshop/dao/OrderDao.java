package com.cigshop.dao;

import com.cigshop.model.CartItem;
import com.cigshop.model.Order;
import com.cigshop.model.OrderItem;
import com.cigshop.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao extends BaseDao {

    public boolean createOrder(int userId, List<CartItem> cartItems) {
        if (cartItems == null || cartItems.isEmpty()) return false;

        double total = cartItems.stream()
                .mapToDouble(CartItem::getSubtotal)
                .sum();

        String insertOrder = "INSERT INTO orders (user_id, total) VALUES (?, ?)";
        String insertItem  = "INSERT INTO order_items (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection()) {
            conn.setAutoCommit(false);
            try {
                int orderId;
                try (PreparedStatement ps = conn.prepareStatement(insertOrder, Statement.RETURN_GENERATED_KEYS)) {
                    ps.setInt(1, userId);
                    ps.setDouble(2, total);
                    ps.executeUpdate();
                    try (ResultSet keys = ps.getGeneratedKeys()) {
                        keys.next();
                        orderId = keys.getInt(1);
                    }
                }

                try (PreparedStatement ps = conn.prepareStatement(insertItem)) {
                    for (CartItem item : cartItems) {
                        ps.setInt(1, orderId);
                        ps.setInt(2, item.getProduct().getId());
                        ps.setInt(3, item.getQuantity());
                        ps.setDouble(4, item.getProduct().getPrice());
                        ps.addBatch();
                    }
                    ps.executeBatch();
                }

                conn.commit();
                return true;
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException e) {
            System.err.println("Ошибка создания заказа: " + e.getMessage());
            return false;
        }
    }

    public List<Order> getByUser(int userId) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE user_id = ? ORDER BY created_at DESC";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Order order = new Order();
                    order.setId(rs.getInt("id"));
                    order.setUserId(rs.getInt("user_id"));
                    order.setCreatedAt(rs.getTimestamp("created_at"));
                    order.setTotal(rs.getDouble("total"));
                    order.setItems(getItemsByOrder(order.getId(), conn));
                    orders.add(order);
                }
            }
        } catch (SQLException e) {
            System.err.println("Ошибка получения заказов: " + e.getMessage());
        }
        return orders;
    }

    private List<OrderItem> getItemsByOrder(int orderId, Connection conn) throws SQLException {
        List<OrderItem> items = new ArrayList<>();
        String sql = "SELECT oi.id, oi.quantity, oi.price, " +
                     "p.id AS pid, p.name, p.description, p.category_id " +
                     "FROM order_items oi JOIN products p ON oi.product_id = p.id " +
                     "WHERE oi.order_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product();
                    product.setId(rs.getInt("pid"));
                    product.setName(rs.getString("name"));
                    product.setDescription(rs.getString("description"));
                    product.setPrice(rs.getDouble("price"));
                    product.setCategoryId(rs.getInt("category_id"));

                    OrderItem item = new OrderItem();
                    item.setId(rs.getInt("id"));
                    item.setProduct(product);
                    item.setQuantity(rs.getInt("quantity"));
                    item.setPrice(rs.getDouble("price"));
                    items.add(item);
                }
            }
        }
        return items;
    }
}
