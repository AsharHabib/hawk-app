package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dbconfig.ConnectionUtil;
import dbconfig.ResourceClosers;
import models.User;

public class UserDaoImpl implements UserDao{

//	public UserDaoImpl() {
//		
//	}
	
	public String getOne() {
		// TODO Auto-generated method stub
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet set = null;
		
		try {
			conn = ConnectionUtil.getConnection();
			
			final String SQL = "select * from users";
			stmt = conn.createStatement();
			
			set = stmt.executeQuery(SQL);
			
			System.out.println(set);
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ResourceClosers.closeConnection(conn);
			ResourceClosers.closeStatement(stmt);
			ResourceClosers.closeResultSet(set);
		}
		return "working";
	}
}
