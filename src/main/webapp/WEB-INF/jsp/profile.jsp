<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="restaurant" tagdir="/WEB-INF/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>

<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron pt-4">
    <div class="container">
        <%--@elvariable id="userTo" type="ru.javawebinar.topjava.to.UserTo"--%>
        <div class="row">
            <div class="col-5 offset-3">
                <h3>${userTo.name} profile</h3>
                <form:form class="form-group" modelAttribute="userTo" method="post" action="profile" charset="utf-8"
                           accept-charset="UTF-8">

                    <restaurant:inputField name="name"/>
                    <restaurant:inputField name="email"/>
                    <restaurant:inputField name="password" inputType="password"/>
                    <restaurant:inputField name="caloriesPerDay" inputType="number"/>

                    <div class="text-right">
                        <button type="submit" class="btn btn-primary">
                            <span class="fa fa-check"></span>
                            save
                        </button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>