<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Restaurants List</title>
</head>
<body>


<%--<section>--%>
    <%--<h3><a href="index.html">Home</a></h3>--%>
    <%--<h2>Restaurants List</h2>--%>
    <%--<hr/>--%>
    <%--<table border="1" cellpadding="8" cellspacing="0">--%>
        <%--<thead>--%>
        <%--<tr>--%>
            <%--<th>Date</th>--%>
            <%--<th>Restaurant</th>--%>
            <%--<th>Name</th>--%>
            <%--<th>Price</th>--%>
        <%--</tr>--%>
        <%--</thead>--%>
        <%--<c:forEach items="${restaurants}" var="dish">--%>
            <%--<jsp:useBean id="dish" type="restaurant.model.Dish"/>--%>
            <%--<tr >--%>
                <%--<td>${dish.date}</td>--%>
                <%--<td>${dish.restaurant}</td>--%>
                <%--<td>${dish.name}</td>--%>
                <%--<td>${dish.price}</td>--%>
            <%--</tr>--%>
        <%--</c:forEach>--%>
    <%--</table>--%>
<%--</section>--%>

<section>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>E-mail</th>
            <th>Registererd</th>
            <th>Name</th>
        </tr>
        </thead>
        <c:forEach items="${users}" var="user">
            <jsp:useBean id="user" type="restaurant.model.User"/>
            <tr >
                <td>${user.email}</td>
                <td>${user.registered}</td>
                <td>${user.name}</td>
            </tr>
        </c:forEach>
    </table>
</section>

</body>
</html>





