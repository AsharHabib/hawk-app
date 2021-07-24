package dbconfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionUtil {

	public static Connection getConnection() throws SQLException {

		return DriverManager.getConnection(
				System.getenv("db_url2"), 
				System.getenv("db_username"),
				System.getenv("db_password"));


	}
}
