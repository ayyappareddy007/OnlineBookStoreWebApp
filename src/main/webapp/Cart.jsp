<%@page import="com.obs.dao.BookDao"%>
<%@page import="com.obs.model.CartModel"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%
    	response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
		ArrayList<CartModel> cart_list = (ArrayList<CartModel>)session.getAttribute("cart-list"); 
    	List<CartModel> books = null;
    	double sum = 0;
    	if(cart_list != null){
    		BookDao bd = new BookDao();
    		books = bd.getCart(cart_list);
    		sum = bd.getTotalPrice(cart_list);
    	}
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cart</title>
<link href="cssfiles/header.css" rel="stylesheet">
<link href="cssfiles/cart.css" rel="stylesheet">
</head>
<body>
	<%@include file="Include/Header.jsp"%>
	<div class="cart-container">
	<% if(cart_list!=null && cart_list.size()>=1){ %>
        <table class="cart-table">
            <thead>
                <tr>
                    <th>Title</th>
                    <th>Author</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Cancel</th>
                </tr>
            </thead>
            <tbody>
            <%
            	for(CartModel book:books){%>
            		<tr>
                    <td class="item-name"><%=book.getTitle() %></td>
                    <td class="item-category"><%=book.getAuthor() %></td>
                    <td class="item-price">$<%=book.getPrice() %></td>
                    <td>
                        <form class="buy-form" action="buy-now?id=<%=book.getId() %>" method="post">
                            <input type="hidden" name="id" value="1">
                            <div class="quantity-controls">
                                <a href="inc-dec?action=dec&id=<%=book.getId() %>" class="quantity-btn" data-action="decrease"><i>-</i></a>
                                <input type="text" name="quantity" value="<%= book.getQuantity() %>" readonly>
                                <a href="inc-dec?action=inc&id=<%=book.getId() %>" class="quantity-btn" data-action="increase"><i>+</i></a>
                            </div>
                            <button type="submit" class="buy-btn">Buy</button>
                        </form>
                    </td>
                    <td><a class="remove-btn" href="remove-book?id=<%=book.getId() %>">Remove</a></td>
                </tr>
            	<%}%>
                <!-- Add more rows as needed -->
            </tbody>
        </table>
        <div class="cart-header">
            <h3>Total Price: $<span id="total-price"><%=sum %></span></h3>
            <a class="checkout-btn" href="check-out">Check Out</a>
        </div>
        <% }else{ %>
        <h3>Cart is empty add books Go to <a href="Home.jsp">Home</a></h3>
        <% } %>
    </div>
	<%@include file="Include/Footer.jsp"%>
</body>
</html>