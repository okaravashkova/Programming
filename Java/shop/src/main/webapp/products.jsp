<%@ page import="java.util.List" %>
<%@ page import="com.cigshop.model.Product" %>

<%
List<Product> products = (List<Product>) request.getAttribute("products");

if (products != null) {
    for (Product p : products) {
%>
        <div>
            <p><%= p.getName() %> - <%= p.getPrice() %></p>
        </div>
<%
    }
} else {
%>
    <p>Нет данных</p>
<%
}
%>