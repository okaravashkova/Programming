<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>История заказов — CigShop</title>
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
    <h1>История заказов</h1>

    <% if ("1".equals(request.getParameter("placed"))) { %>
        <div class="success">Заказ успешно оформлен!</div>
    <% } %>

    <c:if test="${empty orders}">
        <p>Заказов пока нет. <a href="${pageContext.request.contextPath}/products">Перейти к каталогу</a></p>
    </c:if>

    <c:forEach var="order" items="${orders}">
        <div class="order-block">
            <h3>Заказ #${order.id} — ${order.createdAt} — <strong>${order.total} ₽</strong></h3>
            <table>
                <thead>
                    <tr>
                        <th>Товар</th>
                        <th>Цена</th>
                        <th>Кол-во</th>
                        <th>Сумма</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${order.items}">
                        <tr>
                            <td>${item.product.name}</td>
                            <td>${item.price} ₽</td>
                            <td>${item.quantity}</td>
                            <td>${item.subtotal} ₽</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </c:forEach>
</div>
</body>
</html>
