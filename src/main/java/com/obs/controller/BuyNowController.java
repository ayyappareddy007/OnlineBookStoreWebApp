package com.obs.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
 * Servlet implementation class BuyNowController
 */
@WebServlet("/buy-now")
public class BuyNowController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out = response.getWriter()){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            
            UserModel user = (UserModel)request.getSession().getAttribute("user");
            if(user != null) {
            	int book_id = Integer.parseInt(request.getParameter("id"));
            	int quantity = Integer.parseInt(request.getParameter("quantity"));
//            	System.out.print(quantity);
            	if(quantity < 1) {
            		quantity = 1;
            	}
            	OrderModel orderModel = new OrderModel();
            	orderModel.setU_id(user.getId());
            	orderModel.setDate(formatter.format(date));
            	orderModel.setId(book_id);
            	orderModel.setStatus("pending");
            	orderModel.setQuantity(quantity);
//            	BookDao book = new BookDao();
//            	List<BookModel> books = book.getBooks();
//            	for(BookModel b:books) {
//            		if(b.getId() == book_id) {
//            			orderModel.setPrice(b.getPrice());
//            			break;
//            		}
//            	}
            	
            	BookDao book = new BookDao();
            	BookModel bk = book.getSingleBook(book_id);
            	orderModel.setPrice(bk.getPrice());
            	
            	OrderDao oDao = new OrderDao();
            	int o_id = oDao.insertSingleOrder(orderModel);
            	orderModel.setO_id(o_id);
            	boolean result = oDao.insertSingleOrderIntoOrderItems(orderModel);
            	if(result) {
            		HttpSession session = request.getSession();
    				ArrayList<CartModel> cartList = (ArrayList<CartModel>)session.getAttribute("cart-list");
    				
    				if(cartList != null) {
    					for(CartModel item:cartList) {
    						if(item.getId() == book_id) {
    							cartList.remove(item);
    							break;
    						}
    					}
    				}
    				response.sendRedirect("Orders.jsp");
            	}else {
            		out.print("order failed");
            	}
            	
            }else {
            	response.sendRedirect("Login.jsp");
            }
			
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
