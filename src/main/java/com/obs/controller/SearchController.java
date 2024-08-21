package com.obs.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.obs.dao.BookDao;
import com.obs.model.BookModel;

/**
 * Servlet implementation class SearchController
 */
@WebServlet("/search")
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out = response.getWriter()){
			String search = request.getParameter("query");
			String searchColumn = request.getParameter("searchType");
			
			if(search.isEmpty())
				response.sendRedirect("Home.jsp");
			BookDao bookDao = new BookDao();
			List<BookModel> search_books = bookDao.searchBook(search, searchColumn);
			
			request.setAttribute("search-books", search_books);
			RequestDispatcher rd = request.getRequestDispatcher("Search.jsp");
			rd.forward(request, response);
			
			
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
