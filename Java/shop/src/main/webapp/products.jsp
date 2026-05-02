<%@ page import="java.util.List" %>
<%@ page import="com.cigshop.model.Product" %>

<h1>Products</h1>

<%
    List<Product> products = (List<Product>) request.getAttribute("products");
    for (Product p : products) {
%>
    <div>
        <p><%= p.getName() %> - <%= p.getPrice() %></p>
    </div>
<%
    }
%>