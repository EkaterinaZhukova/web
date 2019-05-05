<%--
  Created by IntelliJ IDEA.
  User: ekaterina
  Date: 4/23/19
  Time: 10:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page errorPage="../errorPage.html" %>


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
    <a class="navbar-brand" href="#">Main Page</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="collapsibleNavbar">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}?command=home">Get back</a>
            </li>
        </ul>
    </div>
</nav>

<div class="container" style="margin-top:30px">
    <h1 class="display-4">Get list of all abonents</h1>

    <table class="table">
        <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Name</th>
            <th scope="col">Surname</th>
            <th scope="col">Phone</th>
            <th scope="col">Is blocked?</th>
            <th scope="col">Unblock</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${abonents}" var="abonent">
            <tr>
                <td>${abonent.getId()}</td>
                <td>${abonent.getName()}</td>
                <td>${abonent.getSurname()}</td>
                <td>${abonent.getPhone()}</td>
                <c:if test="${abonent.isBlocked() == 1}">
                    <td>Yes</td>
                </c:if>
                <c:if test="${abonent.isBlocked() == 0}">
                    <td>No</td>
                </c:if>
                <td>
                    <a href="${pageContext.request.contextPath}?command=unblock&name=${abonent.getName()}&surname=${abonent.getSurname()}">Unblock user</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
