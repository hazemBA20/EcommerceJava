<%@ page import="java.util.List" %>
<%@ page import="com.ecommerce.model.produit" %><%--
  Created by IntelliJ IDEA.
  User: BAZ INFO
  Date: 3/5/2025
  Time: 11:54 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<h1>Welcome, <%= request.getAttribute("login") %>  </h1>


    <%
        List<produit> list=(List<produit>)request.getAttribute("list");
    %>
    <div class="container">

        <%
            for (produit prod : list){
        %>
        <div class="product-container">
            <div class="details-container">
                <div class="image-container">
                    <img src="<%= prod.getImagePath() %>" alt="Product Image" width="200">
                </div>
                <div class="text">
                    <p><%= prod.getName() %></p>
                    <p><%= prod.getPrice() %></p>
                    <p><%= prod.getDescription() %></p>
                </div>
                </div>
            <div class="button-container">
                <form action="ProductController" method="POST">
                    <input type="hidden" name="action" value="deleteProduct">
                    <input type="hidden" name="productId" value="<%= prod.getId() %>">
                    <%= prod.getId() %>
                    <button type="submit" id="delete">Delete</button>
                </form>
                <button id="Modify">Modify</button>
            </div>
        </div>
        <%
            }%>
        <div class="adding">
            <label>Click here in order to add a product</label>
            <form action="mycontroller" method="GET">
                <button id="add-button" type="submit"  value="Submit">
                    <img src="images/Plus_symbol.svg.png" class="plus" alt="">
                </button>
            </form>

        </div>
    </div>

</body>
</html>