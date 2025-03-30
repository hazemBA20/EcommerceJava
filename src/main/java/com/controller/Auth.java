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
import com.ecommerce.dao.UserDaoImp;
import com.ecommerce.model.User;
import com.ecommerce.model.produit;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/auth")
public class Auth extends HttpServlet  {
    /*
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("addProduct.jsp");
        dispatcher.forward(request, response);

    }*/
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        request.setAttribute("login", login);
        request.setAttribute("password", password);

        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDaoImp userDao=new UserDaoImp(daoFactory);
        User user=new User(1 , login , password);
        try {
            User u = userDao.getUser(user);
            if(u != null){
                RequestDispatcher dispatcher = request.getRequestDispatcher("hello.jsp");
                dispatcher.forward(request, response);
            }
            else{
                request.setAttribute("error", "Invalid login or password");
                RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}

