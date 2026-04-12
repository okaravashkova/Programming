package com.cigshop.dao;

import com.cigshop.model.User;
import java.sql.*;

public class UserDao extends BaseDao {
    
    public User findByLoginPassword(String login, String password) {
        String sql = "SELECT * FROM users WHERE login=? AND password=?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, login);
            pstmt.setString(2, password);
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                return user;
            }
            
        } catch (SQLException e) {
            System.err.println("Ошибка поиска пользователя: " + e.getMessage());
        }
    }
    
    // Создать пользователя
    public boolean create(User user) {
        String sql = "INSERT INTO users (login, password, email) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, user.getLogin());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getEmail());
            
            int rows = pstmt.executeUpdate();
            return rows > 0;
            
        } catch (SQLException e) {
            System.err.println("Ошибка создания пользователя: " + e.getMessage());
            return false;
        }
    }
}