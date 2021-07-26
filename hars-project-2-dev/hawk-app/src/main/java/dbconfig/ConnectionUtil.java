package dbconfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionUtil {

	public static Connection getConnection() throws SQLException {

		return DriverManager.getConnection(
				"jdbc:postgresql://bankingapidb.cyw3vcsho6sv.us-east-2.rds.amazonaws.com:5432/postgresDB", 
				"kentP0DB",
				"kent123q");


	}
}
