<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Users List</title>
</head>
<body>

<section>
    <h3><a href="index.html">Home</a></h3>
    <h2>Users List</h2>
    <hr/>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Name</th>
            <th>Email</th>
            <th>Enabled</th>
        </tr>
        </thead>
        <c:forEach items="${users}" var="user">
            <jsp:useBean id="user" type="restaurant.model.User"/>
            <tr >
                <td>${user.name}</td>
                <td>${user.email}</td>
                <td>${user.enabled}</td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>