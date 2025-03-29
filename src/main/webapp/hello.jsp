<%--
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
</head>
<body>
<div class="welcome-container">
    <h1>Welcome, <%= request.getAttribute("login") %>  </h1>
    <div class="products" style=" background-color: lightblue ;"><%=request.getAttribute("productName_1")%> Price: <%=request.getAttribute("productPrice_1")%>
       Product Informations : <%=request.getAttribute("productInfo_1")%>></div>
    <div class="products" style=" background-color: lightpink ;"><%=request.getAttribute("productName_2")%> Price: <%=request.getAttribute("productPrice_2")%>
        Product Informations : <%=request.getAttribute("productInfo_2")%>></div>
    <div class="products" style=" background-color: lightblue ;"><%=request.getAttribute("productName_3")%> Price: <%=request.getAttribute("productPrice_3")%>
        Product Informations : <%=request.getAttribute("productInfo_3")%>></div>
    <div class="products" style=" background-color: lightpink ;"><%=request.getAttribute("productName_4")%> Price: <%=request.getAttribute("productPrice_4")%>
        Product Informations : <%=request.getAttribute("productInfo_4")%>></div>

</div>

</body>
</html>
