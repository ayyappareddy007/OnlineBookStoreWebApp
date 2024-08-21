<%@page import="com.obs.model.CartModel"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.obs.model.UserModel"%>
<% 
	HttpSession ses = request.getSession(false);
	UserModel um = (UserModel)session.getAttribute("user");
	int id = 0;
	String name = "";
	if(um != null){
		id = um.getId();
		String arr[] = um.getName().split(" ");
		if(!(arr.length > 1)){
			name = arr[0];
		}
		else{
			for(int i=0;i<arr.length;i++){
				if(arr[i].length() >= 3){
					name = arr[i];
					break;
				}
			}
		}
	}
	ArrayList<CartModel> list = (ArrayList<CartModel>)session.getAttribute("cart-list");
%>
<header>
	<div class="logo">
		<a href="Home.jsp"> <img src="Images/logo.png" alt="Logo">
		</a>
	</div>
	<div class="search-bar">
		<form action="search" method="GET">
			<input type="text" name="query" placeholder="Search..."> 
			<select name="searchType">
				<option value="title">Title</option>
				<option value="author">Author</option>
				<option value="genre">Genre</option>
			</select>
			<button type="submit">Search</button>
		</form>
	</div>
	<div class="header-links">
		<a href="Cart.jsp" class="cart">Cart<% if(list != null && list.size()>=1){ %><span style="color: red;font-size: 16px;"><%=list.size() %></span><%}%></a>
		<% if (id != 0) { %>
		
		<div class="dropdown">
			<button class="dropbtn"><%=name %></button>
			<div class="dropdown-content">
				<a href="Orders.jsp">Orders</a> 
				<a href="UpdateProfile.jsp">Update Profile</a> 
				<a href="logout">Logout</a>
			</div>
		</div>
		<% } else { %>
		<a href="Login.jsp" class="login">Login</a>
		<% } %>
	</div>
</header>