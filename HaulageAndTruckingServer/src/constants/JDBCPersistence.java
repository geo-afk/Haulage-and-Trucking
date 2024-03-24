package src.constants;

import static java.sql.DriverManager.getConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCPersistence {
	
	

	private JDBCPersistence() {
	}


	private static Connection connection;
	private static PreparedStatement statement;
	
	
	private static final String CREDENTIALS_URL = "jdbc:mysql://localhost:3306/trucking";
	private static final String USERNAME = "root";
	private static final String DB_PASSWORD = "pascal321";

	
	
	public static boolean isValidLogin(Long id, String password) {

		final String sql = "SELECT staff_id, password FROM trucking.credentials WHERE staff_id = ? AND password = ?";

		try {
			connection = getConnection(CREDENTIALS_URL, USERNAME, DB_PASSWORD);
			statement = connection.prepareStatement(sql);
			statement.setLong(1, id);
			statement.setString(2, password);

			try (ResultSet result = statement.executeQuery()) {
				return result.next(); // Returns true if result set has at least one row
			}
		} catch (SQLException e) {
			e.printStackTrace(); // Consider logging the error instead
			return false; // Return false on any exception
		} finally {
			try {
				connection.close();
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}


	public void createStaffCredentials(Long staffId, Long id, String password) {

	}

	
	
	public static boolean isStaffIdPresent(Long id) {

		final String sql = "SELECT staff_id FROM trucking.credentials WHERE staff_id = ?";

		try {
			connection = DriverManager.getConnection(CREDENTIALS_URL, USERNAME, DB_PASSWORD);
			statement = connection.prepareStatement(sql);
			statement.setLong(1, id);

			try (ResultSet result = statement.executeQuery()) {
				return result.next(); // Returns true if result set has at least one row
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			try {
				connection.close();
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
	
	public static boolean resetPassword(Long id, String password) {
		
		final String sql = "UPDATE trucking.credentials SET password = ? WHERE staff_id = ?";
        
		
		try {
			connection = DriverManager.getConnection(CREDENTIALS_URL, USERNAME, DB_PASSWORD);
			statement = connection.prepareStatement(sql);
			statement.setLong(1, id);
			statement.setString(1, password);
			
			 try (ResultSet result = statement.executeQuery()) 
			 {
				 return result.next(); // Returns true if result set has at least one row
	         }
				
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
        	try {
				connection.close();
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        }
		
		
		
	}
}
