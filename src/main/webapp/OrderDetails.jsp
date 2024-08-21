<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.obs.dao.*"%>
<%@page import="com.obs.model.*"%>
<%@page import="java.util.*"%>
<% 
	response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
	UserModel user = (UserModel)session.getAttribute("user");
	OrderDao orderDao = new OrderDao();
	List<OrderModel> order_details = (List<OrderModel>)session.getAttribute("order-details");
	OrderModel order = (OrderModel)session.getAttribute("single-order");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Order Details</title>
<link rel="stylesheet" href="cssfiles/orders.css">
<link href="cssfiles/header.css" rel="stylesheet">
</head>
<body>
	<%@include file="Include/Header.jsp"%>
	<div class="container">
		<% if(order_details != null && order != null){%>
        <h2>Order Details - #<%=order.getO_id() %></h2>
        <p><strong>Order Date:</strong> <%=order.getDate() %></p>
        <p><strong>Status:</strong> <%=order.getStatus() %></p>
        <p><strong>Contact Details:</strong> <%=user.getNumber() %></p>
        <p><strong>Payment Method:</strong> Credit Card</p>

        <h3>Items in Order</h3>
        <table class="orders-table">
            <thead>
                <tr>
                    <th>Book Title</th>
                    <th>Author</th>
                    <th>Quantity</th>
                    <th>Price per Unit</th>
                    <th>Total Price</th>
                    <th>Cancel</th>
                </tr>
            </thead>
            <tbody>
                <!-- Repeat this block for each item in the order -->
                
                	<% for(OrderModel o: order_details){%>
	                <tr>
	                    <td><%=o.getTitle() %></td>
	                    <td><%=o.getAuthor() %></td>
	                    <td><%=o.getQuantity() %></td>
	                    <td><%=o.getPrice()/o.getQuantity() %></td>
	                    <td><%=o.getPrice() %></td>
	                    <td><a class="remove-btn" href="remove-order?id=<%=o.getOd_id()%>&oid=<%=order.getO_id()%>">cancel</a></td>
	                </tr>
                	<%}
                	%>                               
                <!-- End repeat -->
            </tbody>
        </table>

        <p><strong>Order Total Amount:</strong> <%=orderDao.getTotalPrice(order.getO_id()) %></p>
        <a href="Orders.jsp">Back to My Orders</a>
        <%}else{
        	response.sendRedirect("Orders.jsp");
        }
        	
        	%>
    </div>
    <%@include file="Include/Footer.jsp"%>
</body>
</html>