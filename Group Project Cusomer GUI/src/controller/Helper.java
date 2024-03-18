package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import model.Customer;


public class Helper {
	
	public static Statement statement;
	public static Connection connection;
	
		// Method to create a customer record in the database
		public static void createARecord(Customer customer) {
			connection = DbConn.getConnection();
	        String sql = "INSERT INTO customer (CustomerID, Company, ContactPerson, Address1, Address2, PostOffice, Parish, TelephoneNo, Email, Status) VALUES ('" + customer.getCustomerId() 
	        + "', '" + customer.getCompanyName() + "', '" + customer.getContactPerson() + "', '" + customer.getAddress1() + "', '" + customer.getAddress2() 
	        + "', '" + customer.getPostOffice() + "', '" + customer.getParish() + "', '" + customer.getTelephone() 
	        + "', '" + customer.getEmail() + "', " + customer.getStatus() + ")";
	        try {
	            statement = connection.createStatement();
	            int rowsInserted = statement.executeUpdate(sql);
	            if (rowsInserted > 0) {
	                System.out.println("Customer record inserted successfully.");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (statement != null) {
	                    statement.close();
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
		}

}
