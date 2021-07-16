package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.sql.PreparedStatement;

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
		System.out.println("LOL");
		return "working";
	}

	@Override
	public Map<Integer, User> getAllUsers() {
		HashMap<Integer, User> users = new HashMap<>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet set = null;
		try {
			conn = ConnectionUtil.getConnection();

			//Now that we've gotten a connection, we want to execute our SQL
			//statement. In order to execute the SQL statement, we will need to use
			//the Statement interface.
			final String SQL = "select * from users";
			stmt = conn.createStatement();

			//Once you've executed your query, you get back a ResultSet. The ResultSet
			//is an object which contains the data returned from executing query.
			set = stmt.executeQuery(SQL);

			//At this point, we have the ResultSet. Now we just need to move through
			//the data and place it into the Map that we will return to the caller.

			//We'll start by moving through each row in our ResultSet. We can do this by
			//calling the next() method on the ResultSet. This moves the cursor forward to
			//the next row.

			while(set.next()) {
				//We want to pull out the three values we need (int id, String firstName, String lastName,
				// String email, String password) and construct a user and then throw that user into the map.
				User retrievedUser = new User(set.getInt(1), set.getString(2), set.getString(3),
						set.getString(4), set.getString(5));
				// put(key, value) - the key is the user_id and the value is the whole row
				users.put(set.getInt(1), retrievedUser);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//Remember to close your connection and release all of your JDBC resources.
			ResourceClosers.closeConnection(conn);
			ResourceClosers.closeStatement(stmt);
			ResourceClosers.closeResultSet(set);
		}
		// Then return the Hash Map where we all stored the retrieved users
		return users;
	}

	@Override
	public Map<Integer, User> checkUser(){
		// For the login
		HashMap<Integer, User> user = new HashMap<>(); // To store the retrieved elements in the column

		User getUser = new User(); // default constructor

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet set = null;

		try {
			conn = ConnectionUtil.getConnection();
			final String SQL = "select * from users where user_email = ? and user_password = ?";

			stmt = conn.prepareStatement(SQL); // Prepare statement

			stmt.setString(1, getUser.getEmail());
			stmt.setString(2, getUser.getPassword());

			set = stmt.executeQuery(SQL); // Execute sql

			while(set.next()) {
				User retrievedUser = new User(set.getInt(1), set.getString(2), set.getString(3),
						set.getString(4), set.getString(5));
				// put(key, value) - the key is the user_id and the value is the whole row
				user.put(set.getInt(1), retrievedUser);
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			//Remember to close your connection and release all of your JDBC resources.
			ResourceClosers.closeConnection(conn);
			ResourceClosers.closeStatement(stmt);
			ResourceClosers.closeResultSet(set);
		}

		return user;
	}

	public void createUser(User user){
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			//Establish the connection to the DB
			conn = ConnectionUtil.getConnection();
			final String SQL = "insert into users values(default, ?, ?, ?, ?)";

			stmt = conn.prepareStatement(SQL);

			//Since the SQL statement is parameterized, I need to set the values of
			//the parameters.
			stmt.setString(1, user.getFirstName());
			stmt.setString(2, user.getLastName());
			stmt.setString(3, user.getEmail());
			stmt.setString(4, user.getPassword());

			//And of course, execute the SQL statement once you have set your parameters.
			stmt.execute();

		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ResourceClosers.closeConnection(conn);
			ResourceClosers.closeStatement(stmt);
		}

	}

	
}
