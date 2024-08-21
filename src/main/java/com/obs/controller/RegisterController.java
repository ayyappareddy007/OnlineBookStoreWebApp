package com.obs.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.obs.dao.RegisterDao;
import com.obs.model.UserModel;

@WebServlet("/register")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String contact = request.getParameter("contact");
		UserModel um = new UserModel();
		um.setName(name);
		um.setEmail(email);
		um.setPassword(password);
		um.setNumber(contact);
		
		if(RegisterDao.registerUser(um) == 1) {
			System.out.println("Successfully registered");
			request.setAttribute("registerMessage", "Successfully Registered.");
            request.getRequestDispatcher("Register.jsp").forward(request, response);
		}
		else{
			request.setAttribute("registerError", "Registration failed. Please try again.");
            request.getRequestDispatcher("Register.jsp").forward(request, response);
		}
	}
}
