package com.controller;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.ecommerce.dao.DAOFactory;
import com.ecommerce.dao.ProduitDaoImpl;
import com.ecommerce.model.produit;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/mycontroller")
public class MyController extends HttpServlet  {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        request.setAttribute("login", login);
        request.setAttribute("password", password);

        DAOFactory daoFactory = DAOFactory.getInstance();
        ProduitDaoImpl imp= new ProduitDaoImpl(daoFactory);
        try {
            List<produit> list=imp.getAll();
            request.setAttribute("list", list);
            System.out.println("Elements list size: " + list.size());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("hello.jsp");
        dispatcher.forward(request, response);

    }


}

