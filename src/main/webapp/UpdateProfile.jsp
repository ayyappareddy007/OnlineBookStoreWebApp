<%@page import="com.obs.model.UserModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% 	response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
	//HttpSession ses = request.getSession(false);
	//UserModel um = (UserModel)session.getAttribute("user"); 
	/*if(um == null){
		response.sendRedirect("Login.jsp");
		return;
	}*/
	String updateMessage = (String)request.getAttribute("updateMessage");
	String updateError = (String)request.getAttribute("updateError");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Update Profile</title>
<link rel="stylesheet" href="cssfiles/register.css">
<link href="cssfiles/header.css" rel="stylesheet">
<style>
    /* Custom modal styling */
    .modal {
        display: none;
        position: fixed;
        z-index: 1;
        left: 0;
        top: 0;
        width: 100%;
        height: 100%;
        overflow: auto;
        background-color: rgba(0, 0, 0, 0.5);
        justify-content: center;
        align-items: center;
    }

    .modal-content {
        background-color: #fff;
        margin: auto;
        padding: 20px;
        border: 1px solid #888;
        width: 80%;
        max-width: 400px;
        border-radius: 10px;
        text-align: center;
    }

    .modal-content h3 {
        margin: 0 0 15px;
    }

    .modal-content p {
        margin: 15px 0;
    }

    .modal-content button {
        padding: 10px 20px;
        background-color: #28a745;
        color: white;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }

    .modal-content button:hover {
        background-color: #218838;
    }
</style>
</head>
<body>
	<%@include file="Include/Header.jsp"%>
	<% if(um == null){
		response.sendRedirect("Login.jsp");
		return;		
	}
	%>
	<div class="register-body">
	<div class="registration-container">
		<h2>Update User Details</h2>
		<form action="update-profile-details?id=<%=um.getId() %>" method="post">
			<div class="input-group">
				<label for="name">Name:</label> 
				<input type="text" id="name" name="name" value="<%=um.getName() %>" required>
			</div>
			<div class="input-group">
				<label for="email">Email:</label> 
				<input type="email" id="email" name="email" value="<%=um.getEmail() %>" readonly>
			</div>
			<div class="input-group">
				<label for="password">Password:</label> 
				<input type="password" id="password" name="password" value="<%=um.getPassword() %>" required><br><br>
				<input type="checkbox" onclick="myFunction()">Show Password
			</div>
			<div class="input-group">
				<label for="contact">Contact:</label> 
				<input type="text" id="contact" name="contact" value="<%=um.getNumber() %>" required>
			</div>
			<button type="submit" class="register-btn">Update</button>
		</form>
		
	</div>
	<!-- Custom modal structure -->
    <div id="myModal" class="modal">
        <div class="modal-content">
            <h3>Notification</h3>
            <p id="modalMessage"></p>
            <button id="closeModal">OK</button>
        </div>
    </div>
    </div>
	<script>
		function myFunction() {
			var x = document.getElementById("password");
			if (x.type === "password") {
				x.type = "text";
			} else {
				x.type = "password";
			}
		}
		window.onload = function() {
            var modal = document.getElementById("myModal");
            var modalMessage = document.getElementById("modalMessage");
            var closeModal = document.getElementById("closeModal");

            <% if (updateMessage != null) { %>
                modalMessage.innerText = "<%=updateMessage%>";
                modal.style.display = "flex";
                closeModal.onclick = function() {
                    modal.style.display = "none";
                    window.location.href = "Home.jsp"; // Redirect to Profile page after successful update
                }
            <% } else if (updateError != null) { %>
                modalMessage.innerText = "<%=updateError%>";
                modal.style.display = "flex";
                closeModal.onclick = function() {
                    modal.style.display = "none";
                    window.location.href = "UpdateProfile.jsp"; // Redirect back to Update Profile page on error
                }
            <% } %>
        }
	</script>
	<%@include file="Include/Footer.jsp"%>
</body>
</html>