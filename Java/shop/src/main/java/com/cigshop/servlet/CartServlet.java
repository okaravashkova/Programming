package com.cigshop.servlet;

import com.cigshop.dao.CartDao;
import com.cigshop.model.CartItem;
import com.cigshop.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        CartDao dao = new CartDao();
        List<CartItem> items = dao.getByUser(user.getId());
        double total = items.stream().mapToDouble(CartItem::getSubtotal).sum();

        request.setAttribute("cartItems", items);
        request.setAttribute("cartTotal", total);
        request.getRequestDispatcher("/cart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        CartDao dao = new CartDao();

        if ("add".equals(action)) {
            int productId = Integer.parseInt(request.getParameter("productId"));
            dao.addItem(user.getId(), productId);
            response.sendRedirect(request.getContextPath() + "/cart");
        } else if ("remove".equals(action)) {
            int itemId = Integer.parseInt(request.getParameter("itemId"));
            dao.removeItem(itemId, user.getId());
            response.sendRedirect(request.getContextPath() + "/cart");
        } else {
            response.sendRedirect(request.getContextPath() + "/cart");
        }
    }
}
