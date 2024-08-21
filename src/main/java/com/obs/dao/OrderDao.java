package com.obs.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.obs.model.BookModel;
import com.obs.model.OrderModel;

public class OrderDao {
	private String dbUrl = "jdbc:oracle:thin:@localhost:1521:xe";
	private String dbName = "database1";
	private String dbPswd = "db1";
	private Connection con = null;
	PreparedStatement pstmt = null;
	CallableStatement cstmt = null;
	ResultSet rs = null;
	
	public int insertSingleOrder(OrderModel order) {
		int id = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(dbUrl, dbName, dbPswd);
			String plsql = "BEGIN "
                    + "  INSERT INTO orders (user_id, order_date, status) "
                    + "  VALUES (?, ?, ?) "
                    + "  RETURNING id INTO ?; "
                    + "END;";
			cstmt = con.prepareCall(plsql);
			cstmt.setInt(1, order.getU_id());
			cstmt.setString(2, order.getDate());
			cstmt.setString(3, order.getStatus());
			cstmt.registerOutParameter(4, Types.NUMERIC);
			cstmt.execute();
			id = cstmt.getInt(4);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			// Close resources
            try {
                if (cstmt != null) cstmt.close();
                if (con != null) con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
		}
		
		return id;
	}
	public boolean insertSingleOrderIntoOrderItems(OrderModel order) {
		boolean result = false;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(dbUrl, dbName, dbPswd);
			String query = "insert into order_items(order_id, book_id, quantity, price) values(?, ?, ?, ?)";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, order.getO_id());
			pstmt.setInt(2, order.getId());
			pstmt.setInt(3, order.getQuantity());
			pstmt.setDouble(4, order.getPrice()*order.getQuantity());
			
			int n = pstmt.executeUpdate();
			if(n>0) {
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
		}
		
		
		return result;
	}
	
	public OrderModel getOrder(int o_id) {
		OrderModel order = new OrderModel();
		
		try {
			String query = "select id, order_date, status from orders where id = ?";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(dbUrl, dbName, dbPswd);
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, o_id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				order.setO_id(rs.getInt("id"));
				order.setDate(rs.getString("order_date"));
				order.setStatus(rs.getString("status"));
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}finally {
			try {
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
		}
//		System.out.println(order);
		return order;
	}
	
	public List<OrderModel> getOrders(int u_id){
		List<OrderModel> order_list = null;
		
		try {
			order_list = new ArrayList<OrderModel>();
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(dbUrl, dbName, dbPswd);
			String query = "select id, order_date, status from orders where user_id = ? order by id desc";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, u_id);
			rs = pstmt.executeQuery();
			OrderModel order = null;
//			BookDao bookDao = null;
			while(rs.next()) {
				order = new OrderModel();
				order.setO_id(rs.getInt("id"));
				order.setDate(rs.getString("order_date"));
				order.setStatus(rs.getString("status"));
//				order.setQuantity(rs.getInt("quantity"));
//				order.setPrice(rs.getDouble("price"));
				
				order_list.add(order);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}finally {
			try {
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
		}
		return order_list;
	}
	
	public List<OrderModel> getOrderDetails(int id){
		List<OrderModel> orderDetails = null;
		try {
			orderDetails = new ArrayList<OrderModel>();
			String query = "select id, order_id, book_id, quantity, price from order_items where order_id=?";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(dbUrl, dbName, dbPswd);
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			OrderModel orderModel = null;
			BookDao bookDao = null;
			while(rs.next()) {
				orderModel = new OrderModel();
				orderModel.setOd_id(rs.getInt("id"));
				orderModel.setO_id(rs.getInt("order_id"));
				orderModel.setQuantity(rs.getInt("quantity"));
				orderModel.setPrice(rs.getDouble("price"));
				bookDao = new BookDao();
				BookModel book = bookDao.getSingleBook(rs.getInt("book_id"));
				orderModel.setAuthor(book.getAuthor());
				orderModel.setTitle(book.getTitle());
				orderDetails.add(orderModel);
				
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}finally {
			try {
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
		}
		
		return orderDetails;
	}
	
	public int removeOrderDetails(int od_id) {
		int n=0;
		
		String query = "delete from order_items where id = ?";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(dbUrl, dbName, dbPswd);
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, od_id);
			n = pstmt.executeUpdate();
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
		
		return n;
	}
	
	public int removeOrder(int o_id) {
		int n = 0;
		
		String query = "delete from orders where id = ?";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(dbUrl, dbName, dbPswd);
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, o_id);
			n = pstmt.executeUpdate();			
		} catch (Exception e) {
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
		return n;
	}
	
	public double getTotalPrice(int o_id) {
		double totalPrice=0;
		
		try {
			String query = "select sum(price) as price from order_items where order_id=?";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(dbUrl, dbName, dbPswd);
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, o_id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				totalPrice = rs.getDouble("price");
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
		return totalPrice;
	}
}
