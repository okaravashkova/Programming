<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Корзина — CigShop</title>
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
    <h1>Корзина</h1>

    <c:if test="${empty cartItems}">
        <p>Корзина пуста. <a href="${pageContext.request.contextPath}/products">Перейти к каталогу</a></p>
    </c:if>

    <c:if test="${not empty cartItems}">
        <table>
            <thead>
                <tr>
                    <th>Товар</th>
                    <th>Цена</th>
                    <th>Кол-во</th>
                    <th>Сумма</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${cartItems}">
                    <tr>
                        <td><a href="${pageContext.request.contextPath}/product?id=${item.product.id}">${item.product.name}</a></td>
                        <td>${item.product.price} ₽</td>
                        <td>${item.quantity}</td>
                        <td>${item.subtotal} ₽</td>
                        <td>
                            <form method="post" action="${pageContext.request.contextPath}/cart">
                                <input type="hidden" name="action" value="remove">
                                <input type="hidden" name="itemId" value="${item.id}">
                                <button type="submit" class="btn btn-danger btn-sm">Удалить</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                <tr class="total-row">
                    <td colspan="3">Итого:</td>
                    <td colspan="2">${cartTotal} ₽</td>
                </tr>
            </tbody>
        </table>

        <div style="margin-top:20px">
            <form method="post" action="${pageContext.request.contextPath}/orders">
                <button type="submit" class="btn">Оформить заказ</button>
            </form>
        </div>
    </c:if>
</div>
</body>
</html>
