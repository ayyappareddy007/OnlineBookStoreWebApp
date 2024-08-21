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


@WebServlet("/remove-order")
public class RemoveOrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out = response.getWriter()){
			int od_id = Integer.parseInt(request.getParameter("id"));
			int o_id = Integer.parseInt(request.getParameter("oid"));
			HttpSession session = request.getSession();
			UserModel user = (UserModel)session.getAttribute("user");
			if(user != null) {
				OrderDao od = new OrderDao();
				List<OrderModel> order_details = (List<OrderModel>)session.getAttribute("order-details");
				
				if (order_details != null) {
					if (od.removeOrderDetails(od_id) > 0) {
						for(OrderModel om: order_details) {
							if(om.getOd_id() == od_id) {
								order_details.remove(om);
								break;
							}
						}						
						if(order_details.size()<1) {
							if(od.removeOrder(o_id)>0) {
								response.sendRedirect("Orders.jsp");
							}
						}
						response.sendRedirect("OrderDetails.jsp");
					}
					
				}
				
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
