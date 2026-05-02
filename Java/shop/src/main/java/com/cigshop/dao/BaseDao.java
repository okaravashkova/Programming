package com.cigshop.dao;

import java.sql.*;

public abstract class BaseDao {
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("!", e);
        }
    }
    
    protected Connection getConnection() throws SQLException {
    return DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/shop?" +
        "useSSL=false&" +
        "allowPublicKeyRetrieval=true&" +
        "serverTimezone=UTC",
        "shopuser",
        "shoppass"
    );
    }
}