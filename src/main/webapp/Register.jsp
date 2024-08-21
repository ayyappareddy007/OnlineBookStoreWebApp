<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	String registerMessage = (String)request.getAttribute("registerMessage");
	String registerError = (String)request.getAttribute("registerError");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Sign Up</title>
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
	<div class="register-body">
	<div class="registration-container">
		<h2>Register</h2>
		<form action="register" method="post">
			<div class="input-group">
				<label for="name">Name:</label> 
				<input type="text" id="name" name="name" required>
			</div>
			<div class="input-group">
				<label for="email">Email:</label> 
				<input type="email" id="email" name="email" required>
			</div>
			<div class="input-group">
				<label for="password">Password:</label> 
				<input type="password" id="password" name="password" required><br><br>
				<input type="checkbox" onclick="myFunction()">Show Password
			</div>
			<div class="input-group">
				<label for="contact">Contact:</label> 
				<input type="text" id="contact" name="contact" required>
			</div>
			<button type="submit" class="register-btn">Register</button>
		</form>
		<p class="signin-link">
			Already have an account? <a href="Login.jsp">Sign In</a>
		</p>
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

            <% if (registerMessage != null) { %>
                modalMessage.innerText = "<%=registerMessage%>";
                modal.style.display = "flex";
                closeModal.onclick = function() {
                    modal.style.display = "none";
                    window.location.href = "Login.jsp"; // Redirect to Login page after successful update
                }
            <% } else if (registerError != null) { %>
                modalMessage.innerText = "<%=registerError%>";
                modal.style.display = "flex";
                closeModal.onclick = function() {
                    modal.style.display = "none";
                    window.location.href = "Register.jsp"; // Redirect back to Register Profile page on error
                }
            <% } %>
        }
	</script>
	<%@include file="Include/Footer.jsp"%>
</body>
</html>