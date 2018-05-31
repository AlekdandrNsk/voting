<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="messages.app"/>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <h3>Users</h3>
        <br/>
        <button class="btn btn-primary">
            <span class="fa fa-plus"></span>
            add
        </button>
        <br/><br/>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>User name</th>
                <th>email</th>
                <th>roles</th>
                <th>active</th>
                <th>registered</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${users}" var="user">
                <jsp:useBean id="user" type="restaurant.model.User"/>
                <tr>
                    <td><c:out value="${user.name}"/></td>
                    <td><a href="mailto:${user.email}">${user.email}</a></td>
                    <td>${user.roles}</td>
                    <td><input type="checkbox" <c:if test="${user.enabled}">checked</c:if>/></td>
                    <td><fmt:formatDate value="${user.registered}" pattern="dd-MMMM-yyyy"/></td>
                    <td><a><span class="fa fa-pencil"></span></a></td>
                    <td><a><span class="fa fa-remove"></span></a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>