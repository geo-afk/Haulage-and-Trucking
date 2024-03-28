package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Trip;

public class Helper {
	
	public static Connection connection;

    // Method to create a trip record in the database
    public static void createARecord(Trip trip) {
        connection = DbConn.getConnection();
        String sql = "INSERT INTO trip (invoiceNo, staffID, company, sourceAddress, destinationAddress, route, rate, driver, deliveryDate, PayStatus) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, trip.getInvoiceNo());
            statement.setString(2, trip.getStaffID());
            statement.setString(3, trip.getCompany());
            statement.setString(4, trip.getSourceAddress());
            statement.setString(5, trip.getDestinationAddress());
            statement.setString(6, trip.getRoute());
            statement.setDouble(7, trip.getRate());
            statement.setString(8, trip.getDriver());
            statement.setDate(9, new java.sql.Date(trip.getDeliveryDate().getTime())); // Convert util.Date to sql.Date
            statement.setString(10, trip.getPayStatus());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Trip record inserted successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}





