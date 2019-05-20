<%--
  Created by IntelliJ IDEA.
  User: ekaterina
  Date: 4/19/19
  Time: 12:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="controller.resources.locale"/>
<!DOCTYPE html>
<html lang="${lang}">
<html>
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
                <a class="nav-link" href=""><fmt:message key="LoginPage"/> </a>
            </li>
        </ul>
    </div>
</nav>

<div class="container" style="margin-top:30px">
    <form action="${pageContext.request.contextPath}?command=login" name="clientsServices" id="clientsServices" method="post">
            <div class="form-group">
                <label for="surname"><fmt:message key="Surname"/> </label>
                <input type="text" class="form-control" id="surname" name="surname"  placeholder="Ivanov">
            </div>
            <div class="form-group">
                <label for="phone"><fmt:message key="Phone"/> </label>
                <input type="text" class="form-control" id="phone" name="phone" placeholder="+375291745656">
            </div>

        <button type="submit" class="btn btn-primary">
            <fmt:message key="Login"/>
        </button>
        <a href="${pageContext.request.contextPath}?command=registration">
            <fmt:message key="OrClickHereToRegister"/> </a>
    </form>
</div>

</body>
</html>
