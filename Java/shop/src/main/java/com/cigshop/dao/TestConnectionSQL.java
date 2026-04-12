package com.cigshop.dao;
import com.cigshop.model.User;
import java.sql.*;

public class TestConnectionSQL {
    public static void main(String[] args) {
        BaseDao dao = new BaseDao() {};  // Анонимный класс
        
        System.out.println("BD Connection Test");
        
        try (Connection conn = dao.getConnection()) {
            System.out.println("MySQL working!");
            System.out.println("DB: " + conn.getCatalog());
            
            // Тест запроса
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as count FROM users");
            rs.next();
            System.out.println("Users: " + rs.getInt("count"));
            
            rs = stmt.executeQuery("SELECT COUNT(*) as count FROM products");
            rs.next();
            System.out.println("Products: " + rs.getInt("count"));
            
            stmt.close();
            
        } catch (SQLException e) {
            System.err.println("DB Error: " + e.getMessage());
            e.printStackTrace();
        }
        
        System.out.println("Test completed!");
    }
    
}
