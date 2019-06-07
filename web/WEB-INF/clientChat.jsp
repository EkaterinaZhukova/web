<%--
  Created by IntelliJ IDEA.
  User: Ekaterina
  Date: 18.05.2019
  Time: 14:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="controller.resources.locale"/>
<!DOCTYPE html>
<html lang="${lang}">
<style>
    .chat {
        z-index: 9999;
        position: absolute;
        bottom: 0;
        right: 0;
        margin: 30px;
    }

    .chat-card {
        height: 100%;
        width: 300px;
        padding: 10px;
    }

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
        display: table;
        max-width: 80%;
        padding: 5px;
        font-size: 14px;
        background: #e5f7e8;
        border-radius: 5px;
        margin-bottom: 10px;
    }

    .close-chat {
        position: absolute;
        top: 0;
        right: 0;
        margin: 5px;
        width: 15px;
    }
</style>
<div class="chat">
    <img src="https://img.icons8.com/color/50/000000/facebook-messenger.png" onclick="chatClick(true)">
</div>
<script>
    function chatClick(ev) {
        let chat = document.getElementsByClassName('chat')[0];
        if (ev) {
            chat.innerHTML = '<div class="card chat-card">\n' +
                '            <div class="chat-elements">\n' +
                '                <p><fmt:message key="chat"/></p>\n' +
                '                <div class="chat-container"></div>\n' +
                '                <input type="text" class="form-control" style="width: 100%; margin-bottom: 5px" id="send-msg"/>\n' +
                '                <input id="send-btn" type="button"  onclick="sendMessage()" value="<fmt:message key="Send"/>" class="btn btn-sm btn-primary" />\n' +
                '            </div>\n' +
                '        </div>' +
                '<img class ="close-chat" src="https://img.icons8.com/material-rounded/26/000000/delete-sign.png" onclick="chatClick(false)">';

            init();
        } else {
            chat.innerHTML = '<img src="https://img.icons8.com/color/50/000000/speech-bubble-with-dots.png" onclick="chatClick(true)">\n';
            closeConnection();
        }
    }

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
        websocket.send('<c:out value='${user.getName()}'/> ' + '<c:out value='${user.getPhone()}'/>');
    }

    function websocketMessage(event) {
        console.log("websocketMessage invoked");
        let msg = document.createElement('div');
        msg.setAttribute('class', 'chat-msg');
        msg.innerText = event.data;
        document.getElementsByClassName('chat-container')[0].appendChild(msg);
        // let audio = new Audio('sounds/notification.mp3');
        // audio.play();
    }

    function websocketError(event) {
        console.log("websocketError invoked");
    }

    function sendMessage() {
        let msg = document.getElementById('send-msg');
        let sent = document.createElement('div');
        sent.setAttribute('class', 'chat-msg');
        sent.setAttribute('style', 'background: #d5e8fa; margin-left: auto; margin-right: 0;');
        sent.innerText = '<fmt:message key="Me"/>: ' + msg.value;
        document.getElementsByClassName('chat-container')[0].appendChild(sent);
        websocket.send('<c:out value='${user.getName()}'/> ' + '<c:out value='${user.getPhone()}'/> ' + msg.value);
        msg.value = "";
    }

    function closeConnection() {
        console.log("websocketClose invoked");
        websocket.close();
    }
</script>
