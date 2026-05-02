<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Товары — CigShop</title>
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
    <h1>Каталог товаров</h1>
    <c:if test="${empty products}">
        <p>Товаров не найдено.</p>
    </c:if>
    <div class="product-grid">
        <c:forEach var="p" items="${products}">
            <div class="product-card">
                <h3><a href="${pageContext.request.contextPath}/product?id=${p.id}" style="text-decoration:none;color:inherit">${p.name}</a></h3>
                <div class="desc">${p.description}</div>
                <div class="price">${p.price} ₽</div>
                <form method="post" action="${pageContext.request.contextPath}/cart">
                    <input type="hidden" name="action" value="add">
                    <input type="hidden" name="productId" value="${p.id}">
                    <button type="submit" class="btn btn-sm">В корзину</button>
                </form>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
