<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Категории — CigShop</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<nav>
    <a href="${pageContext.request.contextPath}/products">Все товары</a>
    <a href="${pageContext.request.contextPath}/categories">Категории</a>
    <a href="${pageContext.request.contextPath}/cart">Корзина</a>
    <a href="${pageContext.request.contextPath}/orders">Мои заказы</a>
    <span class="nav-user">${sessionScope.user.login} &nbsp;|&nbsp; <a href="${pageContext.request.contextPath}/logout">Выйти</a></span>
</nav>
<div class="container">
    <h1>Категории товаров</h1>
    <ul class="category-list">
        <c:forEach var="cat" items="${categories}">
            <li>
                <a href="${pageContext.request.contextPath}/products?categoryId=${cat.id}">${cat.name}</a>
            </li>
        </c:forEach>
    </ul>
</div>
</body>
</html>
