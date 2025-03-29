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
            <div class="details-container"><p><%= prod.getName() %></p>
                <p><%= prod.getPrice() %></p>
                <p><%= prod.getDescription() %></p></div>
            <div class="button-container">
                <button id="delete">Delete</button>
                <button id="Modify">Modify</button>
            </div>
        </div>
        <%
            }%>
        <div class="adding">
            <label>Click here in order to add a product</label>
            <button id="add-button" type="submit"  value="Submit">
                <img src="images/Plus_symbol.svg.png" class="plus" alt="">
            </button>
        </div>
    </div>

</body>
</html>