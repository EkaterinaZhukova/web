<%--
  Created by IntelliJ IDEA.
  User: ekaterina
  Date: 4/15/19
  Time: 11:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page errorPage="error.jsp" %>

<!DOCTYPE html>
<html lang="en">
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
    <a class="navbar-brand" href="#">Main Page</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="collapsibleNavbar">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}?command=allServicesAvailable">Services list</a>
            </li>
            <c:if test="${sessionScope.user.isAbonent()}">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}?command=yourServices&name=${sessionScope.abonent.getName()}&surname=${sessionScope.abonent.getSurname()}">Your services</a>
                </li>
            </c:if>
            <c:if test="${sessionScope.user.isAdmin()}">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}?command=allAbonents">All abonents</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}?command=blockList">Block user</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}?command=unblockList">Unblock user</a>
                </li>
            </c:if>

        </ul>
    </div>


    <div class="navbar-collapse collapse w-30 order-3 dual-collapse2">
        <ul class="navbar-nav ml-auto">
            <c:if test="${not sessionScope.user.isAdmin() && not sessionScope.user.isAbonent()}">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}?command=login">Login</a>
                </li>
            </c:if>
            <c:if test="${sessionScope.user.isAdmin() || sessionScope.user.isAbonent()}">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}?command=logout">Logout ${type}</a>
                </li>
            </c:if>
        </ul>
    </div>
</nav>

<div class="container" style="margin-top:30px">
    <h1 class="display-4">Telephone station</h1>
    <p class="lead">Hello</p>
    <p class="lead">This is a telephone station website where you can get your balance, pay for service and so on</p>
</div>
</body>

</html>
