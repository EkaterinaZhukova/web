<%--
  Created by IntelliJ IDEA.
  User: ekaterina
  Date: 5/19/19
  Time: 5:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <a class="navbar-brand" href="${pageContext.request.contextPath}?command=home"><fmt:message key="MainMenu"/> </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="collapsibleNavbar">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}?command=home"><fmt:message key="GetBack"/> </a>
            </li>
        </ul>
    </div>
</nav>

<div class="container" style="margin-top:30px">
    <h1 class="display-4"><fmt:message key="ChooseLanguage"/> </h1>
    <ul>
        <li><a href="?sessionLocale=en"> <fmt:message key="English"/> </a></li>
        <li><a href="?sessionLocale=ru"> <fmt:message key="Russian"/> </a></li>
    </ul>
    <c:if test="${not empty param.sessionLocale}">
    <button><a href="${pageContext.request.contextPath}?command=home"><fmt:message key="Home"/> </a></button>
    </c:if>
</body>
</div>
</html>