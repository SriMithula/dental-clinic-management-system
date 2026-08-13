<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sunrise Dental Clinic</title>
    <link rel="stylesheet" type="text/css" href="./assets/css/login/login.css">
</head>

<body>

<div class="login-container">

    <h2>Sunrise Dental Clinic</h2>

    <% if (request.getParameter("error") != null) { %>
        <div class="error">
            Invalid username or password
        </div>
    <% } %>

    <form id="loginForm" action="${pageContext.request.contextPath}/login" method="post" novalidate>

        <div class="form-group">
            <label>Username</label>
            <input type="text"  id="username" name="username" placeholder="Enter username">
            <div id="usernameError" class="field-error"></div>
        </div>

        <div class="form-group">
            <label>Password</label>
            <input type="password" id="password" name="password" placeholder="Enter password">
            <div id="passwordError" class="field-error"></div>
        </div>

        <button type="submit">Login</button>

    </form>
</div>
<script src="${pageContext.request.contextPath}/assets/js/login/login.js"></script>
</body>
</html>