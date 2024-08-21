package com.obs.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.obs.model.BookModel;
import com.obs.model.CartModel;

public class BookDao {
	
	private String dbUrl = "jdbc:oracle:thin:@localhost:1521:xe";
	private String dbName = "database1";
	private String dbPswd = "db1";
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	
	//get single book using book id
	
	public BookModel getSingleBook(int b_id) {
		BookModel book = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(dbUrl, dbName, dbPswd);
			String query = "select * from books where id = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, b_id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				book = new BookModel();
				book.setId(rs.getInt("id"));
				book.setTitle(rs.getString("title"));
				book.setPrice(rs.getDouble("price"));
				book.setAuthor(rs.getString("author"));
				book.setGenre(rs.getString("genre"));
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}finally {
			try {
				if(con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return book;
	}
	
	
	public List<BookModel> getBooks(){
		List<BookModel> books = new ArrayList<BookModel>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(dbUrl, dbName, dbPswd);
			String query = "select * from books";
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BookModel book = new BookModel();
				book.setId(rs.getInt("id"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setDescription(rs.getString("description"));
				book.setPrice(rs.getDouble("price"));
				book.setGenre(rs.getString("genre"));
				book.setImage(rs.getString("image"));
				books.add(book);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return books;
	}
	
	public List<CartModel> getCart(ArrayList<CartModel> cartList){
		List<CartModel> books = new ArrayList<>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(dbUrl, dbName, dbPswd);
			if(cartList.size()>0) {
				for(CartModel book:cartList) {
					String query = "select * from books where id=?";
					pstmt = con.prepareStatement(query);
					pstmt.setInt(1, book.getId());
					rs = pstmt.executeQuery();
					while(rs.next()) {
						CartModel cart = new CartModel();
						cart.setId(rs.getInt("id"));
						cart.setTitle(rs.getString("title"));
						cart.setAuthor(rs.getString("author"));
						cart.setPrice(rs.getDouble("price")*book.getQuantity());
						cart.setQuantity(book.getQuantity());
						books.add(cart);
					}
				}	
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.getMessage();
			e.printStackTrace();
		}finally {
			try {
				if(con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return books;
	}
	
	public double getTotalPrice(ArrayList<CartModel> cartList) {
		double total = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(dbUrl,dbName, dbPswd);
			for(CartModel book:cartList) {
				pstmt = con.prepareStatement("select price from books where id=?");
				pstmt.setInt(1, book.getId());
				rs = pstmt.executeQuery();
				while(rs.next()) {
					total += rs.getDouble("price") * book.getQuantity();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return total;
	}
	
	public List<BookModel> searchBook(String search, String searchColumn){
		List<BookModel> books = new ArrayList<BookModel>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(dbUrl, dbName, dbPswd);
			if(searchColumn.equals("title")) {
				pstmt = con.prepareStatement("select * from books where title like ?");
			}else if (searchColumn.equals("author")) {
				pstmt = con.prepareStatement("select * from books where author like ?");
			}else if (searchColumn.equals("genre")) {
				pstmt = con.prepareStatement("select * from books where genre like ?");
			}
			pstmt.setString(1, "%"+search+"%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BookModel book = new BookModel();
				book.setId(rs.getInt("id"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setDescription(rs.getString("description"));
				book.setPrice(rs.getDouble("price"));
				book.setGenre(rs.getString("genre"));
				book.setImage(rs.getString("image"));
				books.add(book);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return books;
	}
}
