<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Вход — CigShop</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container">
    <h1>Вход в аккаунт</h1>
    <div class="form-card">
        <%-- Сообщение после успешной регистрации --%>
        <% if ("1".equals(request.getParameter("registered"))) { %>
            <div class="success">Регистрация прошла успешно. Войдите в аккаунт.</div>
        <% } %>
        <%-- Ошибка авторизации --%>
        <% if (request.getAttribute("error") != null) { %>
            <div class="error">${error}</div>
        <% } %>
        <form method="post" action="${pageContext.request.contextPath}/login">
            <div class="form-group">
                <label>Логин</label>
                <input type="text" name="login" required autofocus>
            </div>
            <div class="form-group">
                <label>Пароль</label>
                <input type="password" name="password" required>
            </div>
            <button type="submit" class="btn">Войти</button>
        </form>
        <div class="form-link">Нет аккаунта? <a href="${pageContext.request.contextPath}/register">Зарегистрироваться</a></div>
    </div>
</div>
</body>
</html>
