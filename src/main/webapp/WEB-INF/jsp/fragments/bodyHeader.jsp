<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<nav class="navbar navbar-dark bg-dark">
    <div class="container">
        <a href="meals" class="navbar-brand"><img src="resources/images/icon-meal.png">Voting System</a>
        <form:form class="form-inline my-2" action="logout" method="post">
            <sec:authorize access="isAuthenticated()">
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <a class="btn btn-info mr-1" href="users">Users</a>
                </sec:authorize>
                <a class="btn btn-info mr-1" href="profile">${userTo.name}profile</a>
                <button class="btn btn-primary" type="submit">
                    <span class="fa fa-sign-out"></span>
                </button>
            </sec:authorize>
        </form:form>
    </div>
</nav>