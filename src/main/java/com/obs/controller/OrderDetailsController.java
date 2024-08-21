package com.obs.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.obs.dao.OrderDao;
import com.obs.model.OrderModel;
import com.obs.model.UserModel;

@WebServlet("/order-details")
public class OrderDetailsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out = response.getWriter()){
			
			int o_id = Integer.parseInt(request.getParameter("orderId"));
			HttpSession session = request.getSession();
			UserModel user = (UserModel)session.getAttribute("user");
			if(user != null) {
				OrderDao od = new OrderDao();
				List<OrderModel> order_Details = od.getOrderDetails(o_id);
				OrderModel order = od.getOrder(o_id);
				session.setAttribute("single-order", order);
				session.setAttribute("order-details", order_Details);
				response.sendRedirect("OrderDetails.jsp");
				
			}else {
				response.sendRedirect("Login.jsp");
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
