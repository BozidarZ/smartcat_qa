package smartcat.etl.db;

import org.springframework.core.env.Environment;

public class DbFactory {

	Environment environment;

	public DbFactory(Environment environment) {
		this.environment = environment;
	}

	public IDbConnection getDbConnection(DbConnectionType dbConnectionType) throws Exception {
		switch (dbConnectionType) {
		case MYSQL:
			return new MySqlConection(environment);
		default:
			throw new Exception("Unknown Db Connection type.");
		}
	}
}
