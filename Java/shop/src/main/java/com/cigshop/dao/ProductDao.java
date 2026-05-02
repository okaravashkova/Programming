package com.cigshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cigshop.model.Product;

import ch.qos.logback.classic.db.DBHelper;

public class ProductDao {
    public List<Product> getAllProducts() {
    List<Product> products = new ArrayList<>();

    try {
        Connection conn = DBHelper.getConnectionSource().getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM shop");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            products.add(new Product(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getDouble("price")
            ));
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return products;
}
}
