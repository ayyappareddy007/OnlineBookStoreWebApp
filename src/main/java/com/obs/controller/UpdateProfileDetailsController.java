package com.obs.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.obs.dao.RegisterDao;
import com.obs.model.UserModel;

@WebServlet("/update-profile-details")
public class UpdateProfileDetailsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out = response.getWriter()){
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
//			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String contact = request.getParameter("contact");
			UserModel um = new UserModel();
			um.setId(id);
			um.setName(name);
//			um.setEmail(email);
			um.setPassword(password);
			um.setNumber(contact);
			if(RegisterDao.updateProfile(um) == 1) {
				request.setAttribute("updateMessage", "Successfully Updated Details.");
	            request.getRequestDispatcher("UpdateProfile.jsp").forward(request, response);
//				response.sendRedirect("Home.jsp");
			}
			else {
				request.setAttribute("updateError", "Failed to Update. Please try again.");
	            request.getRequestDispatcher("UpdateProfile.jsp").forward(request, response);
			}
			
			
		}catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
