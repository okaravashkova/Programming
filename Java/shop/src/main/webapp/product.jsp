<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${product.name} — CigShop</title>
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
    <h1>${product.name}</h1>
    <div style="background:#fff;border-radius:8px;padding:24px;max-width:520px;box-shadow:0 2px 6px rgba(0,0,0,.08)">
        <p style="color:#666;margin-bottom:16px">${product.description}</p>
        <p style="font-size:22px;font-weight:bold;margin-bottom:20px">${product.price} ₽</p>
        <form method="post" action="${pageContext.request.contextPath}/cart">
            <input type="hidden" name="action" value="add">
            <input type="hidden" name="productId" value="${product.id}">
            <button type="submit" class="btn">В корзину</button>
        </form>
        <p style="margin-top:16px"><a href="${pageContext.request.contextPath}/products" style="color:#444">← Назад к каталогу</a></p>
    </div>
</div>
</body>
</html>
