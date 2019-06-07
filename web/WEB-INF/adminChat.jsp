<%--
  Created by IntelliJ IDEA.
  User: ekaterina
  Date: 6/2/19
  Time: 4:24 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page errorPage="error.jsp" %>


<style>

    .chat-elements p {
        text-align: center;
        font-weight: bold;
    }

    .chat-container {
        width: 100%;
        height: 250px;
        margin-bottom: 5px;
        overflow: auto;
    }

    .chat-msg {
        max-width: 60%;
        padding: 5px;
        font-size: 14px;
        background: #ebf7f0;
        border-radius: 5px;
        margin-bottom: 10px;
        display: table;
    }

    .chat-input {
        width: 100%;
        margin-bottom: 10px;
    }

</style>


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
    <div class="card card-main">
        <div class="chat-elements">
            <div class="chat-container"></div>
            <select class="form-control" style="margin-bottom: 5px" id="selected-user">
                <c:forEach items="${abonents}" var="abonents">
                    <option>${abonents.getSurname()} ${abonents.getPhone()} </option>
                </c:forEach>
            </select>
            <input type="text" id="send-msg" class="form-control chat-input"/>
            <input id="send-btn" type="button" onclick="sendMessage()" value="<fmt:message key="Send"/>"
                   class="btn btn-sm btn-primary"/>
        </div>
    </div>
</div>
</body>
<script>
    let websocket;

    function init() {
        websocket = new WebSocket('ws://localhost:8080/Part1_war_exploded/chat');
        websocket.onopen = function (event) {
            websocketOpen(event);
        };
        websocket.onmessage = function (event) {
            websocketMessage(event);
        };
        websocket.onerror = function (event) {
            websocketError(event);
        };
    }

    function websocketOpen(event) {
        console.log("webSocketOpen invoked");
        websocket.send('<c:out value='${user.getName()}'/> ' + '<c:out value='${user.getPhone()}'/> ');
    }

    function websocketMessage(event) {
        console.log("websocketMessage invoked");
        let msg = document.createElement('div');
        msg.setAttribute('class', 'chat-msg');
        msg.innerText = event.data;
        document.getElementsByClassName('chat-container')[0].appendChild(msg);
    }

    function websocketError(event) {
        console.log("websocketError invoked");
    }

    function sendMessage() {
        let msg = document.getElementById('send-msg');
        let sent = document.createElement('div');
        sent.setAttribute('class', 'chat-msg');
        sent.setAttribute('style', 'background: #CCFFFF; margin-left: auto; margin-right: 0;');
        sent.innerText = document.getElementById('selected-user').value + ': ' + msg.value;
        document.getElementsByClassName('chat-container')[0].appendChild(sent);
        websocket.send(document.getElementById('selected-user').value + ': ' + msg.value);
        msg.value = "";
    }

    function closeConnection() {
        console.log("websocketClose invoked");
        websocket.close();
    }

    window.addEventListener("load", init);
    window.addEventListener("unload", closeConnection)
</script>
</html>
