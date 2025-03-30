package com.controller;

import com.ecommerce.dao.DAOFactory;
import com.ecommerce.dao.ProduitDaoImpl;
import com.ecommerce.dao.UserDaoImp;
import com.ecommerce.model.produit;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
@WebServlet("/ProductController")
public class ProductController extends HttpServlet {
    private static final String SAVE_DIR ="images" ;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if("deleteProduct".equals(action)){
            String productIdStr = request.getParameter("productId");
            int productId = Integer.parseInt(productIdStr);
            DAOFactory daoFactory = DAOFactory.getInstance();
            ProduitDaoImpl imp= new ProduitDaoImpl(daoFactory);

            try {
                produit p=imp.getById(productId);
                imp.delete(p);
                System.out.println("gg");
                System.out.println(request.getContextPath());

                response.sendRedirect( "/hello/hello.jsp");
                System.out.println("after");
                return;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } else if ("modifyButton".equals(action)) {
            String productIdStr = request.getParameter("productId");
            int productId = Integer.parseInt(productIdStr);

            request.setAttribute("id" ,productId );
            response.sendRedirect(request.getContextPath() + "/modProduct.jsp?id=" + productId);

            return;




        } else if ("updateProduct".equals(action)) {
            String productIdStr = request.getParameter("id");
            int productId = Integer.parseInt(productIdStr);
            DAOFactory daoFactory = DAOFactory.getInstance();
            ProduitDaoImpl imp= new ProduitDaoImpl(daoFactory);
            String name=request.getParameter("nom");
            String description=request.getParameter("description");

            double price = Double.parseDouble(request.getParameter("prix"));

            String currentImagePath=request.getParameter("imagePath");




            try {
                produit product = imp.getById(productId);


                product.setName(name);
                product.setDescription(description);
                product.setPrice(price);
                Part filePart = request.getPart("image");
                if (filePart != null && filePart.getSize() > 0) {

                    String uploadPath = getServletContext().getRealPath("") + File.separator + "images";
                    File uploadDir = new File(uploadPath);
                    if (!uploadDir.exists()) {
                        uploadDir.mkdir();
                    }

                    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                    String filePath = "images/" + fileName;
                    filePart.write(uploadPath + File.separator + fileName);


                    File oldImage = new File(getServletContext().getRealPath("") + File.separator + currentImagePath);
                    if (oldImage.exists()) {
                        oldImage.delete();
                    }

                    product.setImagePath(filePath);
                } else {

                    product.setImagePath(currentImagePath);
                }



                imp.update(product , productId);

                response.sendRedirect( "/hello/hello.jsp");
                return;


            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


        }
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("nom");
        String description = request.getParameter("description");
        int price = Integer.parseInt(request.getParameter("prix"));
        String uploadPath = getServletContext().getRealPath("") + File.separator + SAVE_DIR;

        // Create the directory if it doesn't exist
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        Part filePart = request.getPart("image"); // Image file
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String filePath = uploadPath + File.separator + fileName;

        // Save the file on the server
        filePart.write(filePath);
        produit newProduct = new produit();
        newProduct.setId(id);
        newProduct.setName(name);
        newProduct.setPrice(price);
        newProduct.setDescription(description);
        newProduct.setImagePath("images/" + fileName); // Relative path
        DAOFactory daoFactory = DAOFactory.getInstance();
        ProduitDaoImpl imp= new ProduitDaoImpl(daoFactory);



        try {

            imp.insert(newProduct);
            response.sendRedirect( request.getContextPath() +"/hello.jsp");
            return;

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Database Error: " + e.getMessage());
        }




    }
}
