<%--
  Created by IntelliJ IDEA.
  User: ekaterina
  Date: 4/19/19
  Time: 11:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page errorPage="error.jsp" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="controller.resources.locale"/>
<!DOCTYPE html>
<html lang="${lang}">
<head>
    <title>Bootstrap 4 Website Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <style>
        .fakeimg {
            height: 200px;
            background: #aaa;
        }
    </style>
</head>
<body>
<c:if test="${user.isAbonent()}">
    <%@include file="clientChat.jsp"%>
</c:if>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <a class="navbar-brand" href="${pageContext.request.contextPath}?command=home"><fmt:message key="MainMenu"/></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="collapsibleNavbar">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}?command=allServicesAvailable"><fmt:message key="ServiceList"/> </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}?command=chooseLanguage"><fmt:message key="ChooseLanguage"/> </a>
            </li>
            <c:if test="${sessionScope.user.isAbonent()}">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}?command=yourServices&name=${sessionScope.abonent.getName()}&surname=${sessionScope.abonent.getSurname()}"><fmt:message key="YourServiceList"/> </a>
                </li>
            </c:if>
            <c:if test="${sessionScope.user.isAdmin()}">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}?command=allAbonents"><fmt:message key="AllAbonents"/> </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}?command=blockList"><fmt:message key="BlockUser"/> </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}?command=unblockList"><fmt:message key="UnblockUser"/> </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}?command=adminChat"><fmt:message key="chat"/> </a>
                </li>
            </c:if>

        </ul>
    </div>


    <div class="navbar-collapse collapse w-30 order-3 dual-collapse2">
        <ul class="navbar-nav ml-auto">
            <c:if test="${not sessionScope.user.isAdmin() && not sessionScope.user.isAbonent()}">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}?command=login"><fmt:message key="Login"/> </a>
                </li>
            </c:if>
            <c:if test="${sessionScope.user.isAdmin() || sessionScope.user.isAbonent()}">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}?command=logout"><fmt:message key="Logout"/> ${type}</a>
                </li>
            </c:if>
        </ul>
    </div>
</nav>

<div class="container" style="margin-top:30px">
    <h1 class="display-4"><fmt:message key="TelephoneStation"/> </h1>
    <p class="lead"><fmt:message key="Hello"/> </p>
    <p class="lead"><fmt:message key="ThisIsATelephoneStationWebSite"/> </p>
</div>
</body>

</html>
