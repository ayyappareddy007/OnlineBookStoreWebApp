package com.obs.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.obs.dao.LoginDao;
import com.obs.model.UserModel;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String email = request.getParameter("uemail");
		String password = request.getParameter("upswd");
		UserModel um = LoginDao.userExist(email, password);
		if(um != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", um);
			response.sendRedirect("Home.jsp");
		}
		else {
			request.setAttribute("loginError", "Incorrect Details.");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
		}
	}

}
