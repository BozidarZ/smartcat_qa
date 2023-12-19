package smartcat.etl.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.core.env.Environment;

public class MySqlConnectionSingleton {

	private static Connection instance = null;

	private MySqlConnectionSingleton() {

	}

	synchronized public static Connection getInstance(Environment env) throws SQLException, ClassNotFoundException {
		if (instance == null) {
			instance = DriverManager.getConnection(env.getProperty("mysql.url"), env.getProperty("mysql.username"),
					env.getProperty("mysql.password"));
		}

		return instance;
	}
}
