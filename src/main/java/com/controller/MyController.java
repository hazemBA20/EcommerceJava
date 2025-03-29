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

@WebServlet("/mycontroller")
public class MyController extends HttpServlet  {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        request.setAttribute("login", login);
        request.setAttribute("password", password);
        RequestDispatcher dispatcher = request.getRequestDispatcher("hello.jsp");
        DAOFactory daoFactory = DAOFactory.getInstance();
        ProduitDaoImpl imp= new ProduitDaoImpl(daoFactory);
        produit[] productTable = new produit[10];
        for (int i = 1; i < 5; i++) {

            try {
                produit p =imp.getById(i);
                //System.out.println("Product " + i + ": " + (p != null ? p.getName() : "null"));
                request.setAttribute("productName_"+i, p.getName());
                request.setAttribute("productPrice_"+i , p.getPrice());
                request.setAttribute("productInfo_"+i, p.getDescription());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


        dispatcher.forward(request, response);

    }


}

