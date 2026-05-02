package com.cigshop.dao;

import com.cigshop.model.User;

public class TestConnectionSQL {
    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        
        System.out.println("🔍 Тест UserDao...");
        
        // Тест CREATE
        User newUser = new User();  // Пустой конструктор
        newUser.setLogin("testuser");
        newUser.setPassword("123456");
        newUser.setEmail("test@example.com");
        
        boolean created = userDao.create(newUser);
        System.out.println("Создан: " + created);
        
        // Тест READ
        User found = userDao.findByLoginPassword("testuser", "123456");
        System.out.println("Найден: " + (found != null ? found.getLogin() : "нет"));
        
        System.out.println("Тест завершён!");
    }
}