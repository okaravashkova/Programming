package com.cigshop.servlet;

import com.cigshop.dao.UserDao;
import com.cigshop.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String login    = request.getParameter("login");
        String password = request.getParameter("password");
        String email    = request.getParameter("email");

        if (login == null || login.isBlank() || password == null || password.isBlank()) {
            request.setAttribute("error", "Логин и пароль обязательны");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        User user = new User();
        user.setLogin(login.trim());
        user.setPassword(password);
        user.setEmail(email != null ? email.trim() : "");

        UserDao dao = new UserDao();
        boolean created = dao.create(user);
        if (created) {
            response.sendRedirect(request.getContextPath() + "/login?registered=1");
        } else {
            request.setAttribute("error", "Логин уже занят или произошла ошибка");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
    }
}
