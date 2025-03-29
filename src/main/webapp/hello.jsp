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
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<h1>Welcome, <%= request.getAttribute("login") %>  </h1>

    <div class="container">

    <div class="products"  >
        <p><%=request.getAttribute("productName_1")%>
            </p>
        <p>Price: <%=request.getAttribute("productPrice_1")%></p>
        <p>Product Informations : <%=request.getAttribute("productInfo_1")%>></p></div>
    <div class="products" >
        <p><%=request.getAttribute("productName_2")%></p>
        <p> Price: <%=request.getAttribute("productPrice_2")%></p>
        <p>Product Informations : <%=request.getAttribute("productInfo_2")%></p></div>
        <div class="products" ><p><%=request.getAttribute("productName_3")%></p>
            <p>Price: <%=request.getAttribute("productPrice_3")%></p>
            <p>Product Informations : <%=request.getAttribute("productInfo_3")%></p></div>

        <div class="products" ><p><%=request.getAttribute("productName_4")%></p>
            <p>Price: <%=request.getAttribute("productPrice_4")%></p>
            <p>Product Informations : <%=request.getAttribute("productInfo_4")%></p></div>
</div>

</body>
</html>
