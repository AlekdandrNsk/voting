<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<nav class="navbar navbar-dark bg-dark">
    <div class="container">
        <a href="meals" class="navbar-brand"><img src="resources/images/icon-meal.png">Voting System</a>
        <form class="form-inline my-2">
            <sec:authorize access="isAuthenticated()">
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <a class="btn btn-info mr-1" href="users">Users</a>
                </sec:authorize>
                <a class="btn btn-primary" href="logout">
                    <span class="fa fa-sign-out"></span>
                </a>
            </sec:authorize>
        </form>
    </div>
</nav>