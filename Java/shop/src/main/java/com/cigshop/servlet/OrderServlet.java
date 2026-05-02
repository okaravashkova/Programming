package com.cigshop.servlet;

import com.cigshop.dao.CartDao;
import com.cigshop.dao.OrderDao;
import com.cigshop.model.CartItem;
import com.cigshop.model.Order;
import com.cigshop.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/orders")
public class OrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        OrderDao dao = new OrderDao();
        List<Order> orders = dao.getByUser(user.getId());
        request.setAttribute("orders", orders);
        request.getRequestDispatcher("/orders.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        CartDao cartDao = new CartDao();
        List<CartItem> items = cartDao.getByUser(user.getId());

        if (items.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/cart");
            return;
        }

        OrderDao orderDao = new OrderDao();
        boolean success = orderDao.createOrder(user.getId(), items);
        if (success) {
            cartDao.clearCart(user.getId());
            response.sendRedirect(request.getContextPath() + "/orders?placed=1");
        } else {
            response.sendRedirect(request.getContextPath() + "/cart?error=1");
        }
    }
}
