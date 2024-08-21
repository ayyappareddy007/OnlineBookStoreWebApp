<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.obs.model.*"%>
<%@page import="com.obs.dao.*" %>
<%@page import="java.util.*" %>
<%	List<BookModel> searchBook = (List<BookModel>)request.getAttribute("search-books"); 
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
        <h2>Search Results</h2>
        <div class="book-grid">
        <% if(!searchBook.isEmpty()) {
        	for(BookModel b:searchBook){ %>
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
				<p>No books found matching your search criteria.</p>
			<%}
			%>
        </div>
    </div>
	<%@include file="Include/Footer.jsp"%>
	
</body>
</html>