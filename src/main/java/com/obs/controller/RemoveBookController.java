package com.obs.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.obs.model.CartModel;


@WebServlet("/remove-book")
public class RemoveBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		response.setContentType("text/html;charset=UTF-8");
		
		try(PrintWriter out = response.getWriter();){
			int id = Integer.parseInt(request.getParameter("id"));
			if(id >=0 ) {
				HttpSession session = request.getSession();
				ArrayList<CartModel> cartList = (ArrayList<CartModel>)session.getAttribute("cart-list");
				
				if(cartList != null) {
					for(CartModel book:cartList) {
						if(book.getId() == id) {
							cartList.remove(book);
							break;
						}
					}
					response.sendRedirect("Cart.jsp");
				}
			}
			else {
				response.sendRedirect("Cart.jsp");
			}
			
		}
		
		
	}

	

}
