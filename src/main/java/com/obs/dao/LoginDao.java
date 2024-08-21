package com.obs.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.obs.model.UserModel;

public class LoginDao {
	public static UserModel userExist(String email, String password) {
		UserModel um = null;
		String dbUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String dbName = "database1";
		String dbPswd = "db1";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select * from users where email=? and password=?";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(dbUrl,dbName,dbPswd);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				um = new UserModel();
				um.setId(rs.getInt("id"));
				um.setName(rs.getString("name"));
				um.setEmail(rs.getString("email"));
				um.setPassword(rs.getString("password"));
				um.setNumber(rs.getString("contact"));
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
		return um;
	}
}
