package com.obs.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.obs.model.UserModel;

public class RegisterDao {
	static String dbUrl = "jdbc:oracle:thin:@localhost:1521:xe";
	static String dbName = "database1";
	static String dbPswd = "db1";
	static Connection con = null;
	static PreparedStatement pstmt = null;
	public static int registerUser(UserModel um) {
		
		String query = "INSERT INTO users (name, email, password, contact) VALUES (?,?,?,?)";
		int n = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(dbUrl, dbName, dbPswd);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1,um.getName());
			pstmt.setString(2,um.getEmail());
			pstmt.setString(3,um.getPassword());
			pstmt.setString(4,um.getNumber());
			n = pstmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
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
	public static int updateProfile(UserModel um) {
		int n=0;
		String query = "update users set name=?, password=?, contact=? where id=?";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(dbUrl, dbName, dbPswd);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, um.getName());
			pstmt.setString(2, um.getPassword());
			pstmt.setString(3, um.getNumber());
			pstmt.setInt(4, um.getId());
			n = pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
		return n;
	}
	
}
