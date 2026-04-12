package com.cigshop.model;

public class Order {
    private int id;
    private User user;
    private CartItem[] items;
    private double total;

    public Order() {
    }

    public Order(int id, User user, CartItem[] items, double total) {
        this.id = id;
        this.user = user;
        this.items = items;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CartItem[] getItems() {
        return items;
    }

    public void setItems(CartItem[] items) {
        this.items = items;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
