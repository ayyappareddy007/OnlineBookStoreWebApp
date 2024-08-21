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


@WebServlet("/inc-dec")
public class IncDecController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out = response.getWriter();){
			String action = request.getParameter("action");
			int id = Integer.parseInt(request.getParameter("id"));
			HttpSession session = request.getSession();
			ArrayList<CartModel> cartList = (ArrayList<CartModel>)session.getAttribute("cart-list");
			
			if(action != null && id>=1) {
				if(action.equals("inc")) {
					for(CartModel book:cartList) {
						if(book.getId() == id) {
							int quantity = book.getQuantity();
							quantity++;
							book.setQuantity(quantity);
							break;
						}
					}
				}
				if(action.equals("dec")) {
					for(CartModel book:cartList) {
						if(book.getId() == id && book.getQuantity()>1) {
							int quantity = book.getQuantity();
							quantity--;
							book.setQuantity(quantity);
							break;
						}
					}
				}
				response.sendRedirect("Cart.jsp");
			}
			else {
				response.sendRedirect("Cart.jsp");
			}
			
		}
	}
}
