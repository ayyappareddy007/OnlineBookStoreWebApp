<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="com.obs.model.UserModel"%>
<%@page import="com.obs.model.BookModel" %>
<%@page import="com.obs.dao.BookDao" %>
<%@page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>	
<%
	response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
	BookDao bd = new BookDao();
	List<BookModel> books = bd.getBooks();

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Online Book Store</title>
<link href="cssfiles/header.css" rel="stylesheet">
<link href="cssfiles/body.css" rel="stylesheet">
</head>
<body>
	<%@include file="Include/Header.jsp"%>
	<div class="books-container">
        <h2>All Books</h2>
        <div class="book-grid">
        <% if(!books.isEmpty()) {
        	for(BookModel b:books){ %>
        		<div class="book-card">
                    <img src="Images/books/<%= b.getImage() %>" alt="image">
                    <h3><%=b.getTitle() %></h3>
                    <h4>by <%=b.getAuthor() %></h4>
                    <p><%=b.getDescription() %></p>
                    <h5>Price: $<%=b.getPrice() %></h5>
                    <div class="btn-container">
                    	<a class="btn" href="add-cart?id=<%=b.getId() %>">Add to Cart</a>
                    	<a class="btn" href="buy-now?quantity=1&id=<%=b.getId()%>">Buy Now</a>
                	</div>
                </div>
        	<%
			}
			} else {%>
			<p>There is no Books</p>
			<%}
			%>
        </div>
    </div>
	<%@include file="Include/Footer.jsp"%>
</body>
</html>