<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.ecommerce.model.produit" %>
<%@ page import="com.ecommerce.dao.ProduitDaoImpl" %>
<%@ page import="com.ecommerce.dao.DAOFactory" %>
<%@ page import="java.sql.SQLException" %>


<%  if (session != null  && session.getAttribute("username")!=null){
%>
<%
    int productId = Integer.parseInt(request.getParameter("id"));
    DAOFactory daoFactory = DAOFactory.getInstance();
    ProduitDaoImpl productDao = new ProduitDaoImpl(daoFactory);
    produit product = null;
    try {
        product = productDao.getById(productId);
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Edit Product</title>
</head>
<body>
<h2>Edit Product</h2>
<form action="ProductController" method="post" enctype="multipart/form-data">
    <input type="hidden" name="action" value="updateProduct">
    <input type="hidden" name="id" value="<%= product.getId() %>">

    <label>Name:</label>
    <input type="text" name="nom" value="<%= product.getName() %>" required><br>

    <label>Description:</label>
    <textarea name="description" required><%= product.getDescription() %></textarea><br>

    <label>Price:</label>
    <input type="number" name="prix" value="<%= product.getPrice() %>" required><br>

    <label>Current Image:</label><br>
    <input type="hidden" name="imagePath" value="<%= product.getImagePath() %>">

    <img src="<%= request.getContextPath() + "/" + product.getImagePath() %>" width="150"><br>

    <label>New Image (optional):</label>
    <input type="file" name="image"><br>


        <button type="submit">Update Product</button>


</form>
</body>
</html>
<%
    }else {
        response.sendRedirect("index.jsp");

    }%>