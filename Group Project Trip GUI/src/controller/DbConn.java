package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class DbConn {
	
	private static Connection connection = null;
	
	public static Connection getConnection(){
        try {
            if (connection == null) {
                
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/groupdatabase", "root", "");
                // Message to confirm that database connection was successful
                JOptionPane.showMessageDialog(null, "Database connection successful",
                        "JDBC Connection Status", JOptionPane.INFORMATION_MESSAGE);    
            }
        } catch (SQLException e) {
            e.printStackTrace(); // This handles the exception appropriately
        }
        return connection;
    }

}
