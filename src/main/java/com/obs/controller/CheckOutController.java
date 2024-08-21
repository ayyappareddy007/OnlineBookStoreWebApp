package com.obs.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.obs.dao.BookDao;
import com.obs.dao.OrderDao;
import com.obs.model.BookModel;
import com.obs.model.CartModel;
import com.obs.model.OrderModel;
import com.obs.model.UserModel;

/**
 * Servlet implementation class CheckOutController
 */
@WebServlet("/check-out")
public class CheckOutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out = response.getWriter()){
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            
			HttpSession session = request.getSession();
			UserModel user = (UserModel)session.getAttribute("user");
			if(user != null) {
				List<CartModel> cart_list = (List<CartModel>) session.getAttribute("cart-list");
				if(cart_list != null) {
					OrderModel orderModel = new OrderModel();
					orderModel.setU_id(user.getId());
					orderModel.setDate(formatter.format(date));
					orderModel.setStatus("pending");
					OrderDao orderDao = new OrderDao();
					orderModel.setO_id(orderDao.insertSingleOrder(orderModel));
					for(CartModel item:cart_list) {
						orderModel.setId(item.getId());
						orderModel.setQuantity(item.getQuantity());
						BookDao bookDao = new BookDao();
						BookModel bookModel = bookDao.getSingleBook(item.getId());
						orderModel.setPrice(bookModel.getPrice());
						boolean result = orderDao.insertSingleOrderIntoOrderItems(orderModel);
					}
					cart_list.clear();
					response.sendRedirect("Orders.jsp");
				}else {
					response.sendRedirect("Home.jsp");
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
