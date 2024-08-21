<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.obs.dao.*"%>
<%@page import="com.obs.model.*"%>
<%@page import="java.util.*"%>
    <% 
    	response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
    	UserModel user = (UserModel)session.getAttribute("user");
    	List<OrderModel> orders = null;
   		OrderDao od = new OrderDao();
    	if(user!=null){
    		orders = od.getOrders(user.getId());
    		session.setAttribute("order-list", orders);
    	}else{
    		response.sendRedirect("Login.jsp");
    	}
    	
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta charset="UTF-8">
<title>My Orders</title>
<link rel="stylesheet" href="cssfiles/orders.css">
<link href="cssfiles/header.css" rel="stylesheet">
</head>
<body>
	<%@include file="Include/Header.jsp"%>
	<div class="container">
        <h2>My Orders</h2>
        <%if(orders!=null && orders.size() >= 1){%>
        <table class="orders-table">
            <thead>
                <tr>
                    <th>Order ID</th>
                    <th>Order Date</th>
                    <th>Status</th>
                    <th>Total Amount</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <!-- Repeat this block for each order -->
                
                <%for(OrderModel o:orders){%>
                <tr>
                    <td><%=o.getO_id() %></td>
                    <td><%=o.getDate() %></td>
                    <td><%=o.getStatus() %></td>
                    <td><%=od.getTotalPrice(o.getO_id()) %></td>
                    <td><a href="order-details?orderId=<%=o.getO_id()%>">View Details</a></td>
                </tr>
               <% }
                } else{%>
                	<h3>No orders. Place Order first Go to <a href="Home.jsp">Home</a></h3>
                <%}
                %>
                
                <!-- End repeat -->
            </tbody>
        </table>
    </div>
    <%@include file="Include/Footer.jsp"%>
</body>
</html>