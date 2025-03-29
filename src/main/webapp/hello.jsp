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
    <div class="products" style=" background-color: lightblue ;width: 50px;"><%=request.getAttribute("productName")%>></div>

</div>

</body>
</html>
