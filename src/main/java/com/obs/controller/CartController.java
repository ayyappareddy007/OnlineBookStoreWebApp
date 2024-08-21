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


@WebServlet("/add-cart")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out = response.getWriter();){
			ArrayList<CartModel> cartList = new ArrayList<>();
			int id = Integer.parseInt(request.getParameter("id"));
			CartModel cart = new CartModel();
			cart.setId(id);
			cart.setQuantity(1);
			
			HttpSession session = request.getSession();
			ArrayList<CartModel> cart_list = (ArrayList<CartModel>)session.getAttribute("cart-list");
			
			if(cart_list == null) {
				cartList.add(cart);
				session.setAttribute("cart-list", cartList);
				response.sendRedirect("Home.jsp");
			}
			else {
				cartList = cart_list;
				boolean exist = false;
				for(CartModel c:cart_list) {
					if(c.getId() == id) {
						exist = true;
						out.println("<h3 style='color:crimson; text-align: center'>Item Already in Cart. <a href='Cart.jsp'>GO to Cart Page</a></h3>");
					}
				}
				if(!exist) {
					cartList.add(cart);
					response.sendRedirect("Home.jsp");
				}
			}
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
