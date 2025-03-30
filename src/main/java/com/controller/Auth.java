package com.controller;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import com.ecommerce.dao.DAOFactory;
import com.ecommerce.dao.ProduitDaoImpl;
import com.ecommerce.dao.UserDaoImp;
import com.ecommerce.model.User;
import com.ecommerce.model.produit;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet({"/auth", "/logout"}) // Handles both login and logout
public class Auth extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        request.setAttribute("login", login);
        request.setAttribute("password", password);

        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDaoImp userDao = new UserDaoImp(daoFactory);
        User user = new User(1, login, password); // 1 doesnt matter here , we should refactor this later

        try {
            User u = userDao.getUser(user);
            if (u != null) { // If login is successful
                HttpSession session = request.getSession(true);
                session.setAttribute("username", login); // Store username in session (string)
                session.setMaxInactiveInterval(120 * 60); // session valid for only 2 hours

                response.sendRedirect("hello.jsp"); // Redirect to welcome page
            } else {
                request.setAttribute("error", "Invalid login or password");
                RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();

        if (path.equals("/logout")) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }

            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
            response.sendRedirect("index.jsp");
        }
    }
}



