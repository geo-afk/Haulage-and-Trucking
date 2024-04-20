package constants;

import static java.sql.DriverManager.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JDBCPersistence {

	private static final Logger logger = LogManager.getLogger(JDBCPersistence.class);

	private JDBCPersistence() {
	}

	private static Connection connection;
	private static PreparedStatement statement;

	private static final String CREDENTIALS_URL = "jdbc:mysql://localhost:3306/trucking";
	private static final String USERNAME = "root";
	private static final String DB_PASSWORD = "pascal321";

	public static boolean isValidLogin(String username, String password) {

		final String sql = "SELECT username, password FROM trucking.administrator WHERE username = ? AND password = ?";
		logger.info("user login validating...");

		try {
			connection = getConnection(CREDENTIALS_URL, USERNAME, DB_PASSWORD);
			statement = connection.prepareStatement(sql);
			statement.setString(1, username);
			statement.setString(2, EncryptPassword.encrypt(password));

			try (ResultSet result = statement.executeQuery()) {

				logger.info("User found for user: {}", username);
				return result.next(); // Returns true if result set has at least one row

			}

		} catch (SQLException e) {
			logger.error("ERROR Validating staff: {}", e.getMessage());
			return false; // Return false on any exception
		} finally {
			logger.info("Closing database connection");
			try {
				connection.close();
				statement.close();
			} catch (SQLException e) {
				logger.error("ERROR Validating login: {}", e.getMessage());
			}

		}
	}

	public static boolean isStaffIdPresent(String username) {

		final String sql = "SELECT username FROM trucking.admin WHERE username = ?";

		try {
			connection = getConnection(CREDENTIALS_URL, USERNAME, DB_PASSWORD);
			statement = connection.prepareStatement(sql);
			statement.setString(1, username);

			try (ResultSet result = statement.executeQuery()) {
				logger.info("Admin staff found: {}", username);
				return result.next(); // Returns true if result set has at least one row
			}

		} catch (SQLException e) {
			logger.error("ERROR: {}", e.getMessage());
			return false;
		} finally {
			logger.info("Closing database connection");
			try {
				connection.close();
				statement.close();
			} catch (SQLException e) {
				logger.error("ERROR: {}", e.getMessage());
			}

		}

	}

	public static Map<String, List<String>> getAddress() {

		final String sql = "SELECT address_1, address_2, parish, post_office from trucking.address";

		Map<String, List<String>> address = new HashMap<>();

		try {
			connection = getConnection(CREDENTIALS_URL, USERNAME, DB_PASSWORD);
			statement = connection.prepareStatement(sql);

			try (ResultSet result = statement.executeQuery()) {
				logger.info("Getting address details from database");
				while (result.next()) {

					String address1 = result.getString("address_1");
					String address2 = result.getString("address_2");
					String parish = result.getString("parish");
					String postOffice = result.getString("post_office");

					address.put(address1 + "-" + address2, List.of(parish, postOffice));

				}
			}

		} catch (SQLException e) {
			logger.error("ERROR: {}", e.getMessage());
		} finally {
			try {
				logger.info("Closing database connection");
				connection.close();
				statement.close();
			} catch (SQLException e) {
				logger.error("ERROR: {}", e.getMessage());
			}

		}
		return address;

	}

}
