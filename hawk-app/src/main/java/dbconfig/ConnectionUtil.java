package dbconfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import io.github.cdimascio.dotenv.Dotenv;

public class ConnectionUtil {

	public static Connection getConnection() throws SQLException {

		Dotenv dotenv = Dotenv.load();
		return DriverManager.getConnection(
				dotenv.get("DB_URL"), 
				dotenv.get("DB_USERNAME"),
				dotenv.get("DB_PASSWORD"));

	}
}
