package payslip;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class SalaryCalculator {

	  private static final String URL = "jdbc:mysql://localhost:3306/your_database";
	  private static final String USER = "your_username";
	  private static final String PASSWORD = "your_password";
	  
	  
	  public static void calculateSalaries(LocalDate startDate, LocalDate endDate) {
	        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {

	            // Query to fetch administrative and maintenance workers
	            String adminQuery = "SELECT Staff_ID, Position FROM Staff WHERE Position = 'Administrative' OR Position = 'Maintenance'";

	            // Query to fetch trips and their corresponding drivers
	            String tripQuery = "SELECT Driver, SUM(Rate) AS TotalEarnings FROM Trip WHERE Delivery_date BETWEEN ? AND ? GROUP BY Driver";

	            // Calculate salaries for administrative and maintenance workers
	            double fixedSalary = 100000.0;
	            PreparedStatement adminStmt = conn.prepareStatement(adminQuery);
	            ResultSet adminResult = adminStmt.executeQuery();
	            while (adminResult.next()) {//getting data from result set
	                int staffID = adminResult.getInt("Staff_ID");
	                String position = adminResult.getString("Position");
	                double salary = fixedSalary;

	                // Check if the employee has been present for at least 30 days
	                if (isEmployeePresentFor30Days(conn, staffID, startDate)) {
	                    System.out.println("Salary for Staff ID " + staffID + " (" + position + "): $" + salary);
	                } else {
	                    System.out.println("Staff ID " + staffID + " (" + position + ") has not been employed for over 30 days.");
	                }
	            }

	            // Calculate salaries for drivers
	            PreparedStatement tripStmt = conn.prepareStatement(tripQuery);
	            //setting parameters for prepared statement
	            tripStmt.setDate(1, java.sql.Date.valueOf(startDate));
	            tripStmt.setDate(2, java.sql.Date.valueOf(endDate));
	            ResultSet tripResult = tripStmt.executeQuery();
	            while (tripResult.next()) { //getting data from result set
	                String driver = tripResult.getString("Driver");
	                int staffID = tripResult.getInt("Staff_ID");
	                double totalEarnings = tripResult.getDouble("TotalEarnings");
	                double salary = totalEarnings * 0.3; // Driver earns 30% of total earnings
	               
	                System.out.println("Salary for Driver ( " + driver + ")ID#"+ staffID +": $" + salary);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    // Function to check if employee has been present for at least 30 days
	    private static boolean isEmployeePresentFor30Days(Connection conn, int staffID, LocalDate currentDate) throws SQLException {
	        String joinDateQuery = "SELECT Join_Date FROM Staff WHERE Staff_ID = ?";
	        try (PreparedStatement pstmt = conn.prepareStatement(joinDateQuery)) {
	            pstmt.setInt(1, staffID);
	            ResultSet joinDateResult = pstmt.executeQuery();

	            if (joinDateResult.next()) {
	                LocalDate joinDate = joinDateResult.getDate("Join_Date").toLocalDate();
	                long daysBetween = ChronoUnit.DAYS.between(joinDate, currentDate);
	                return daysBetween >= 30;
	            } else {
	                return false; // Employee not found
	            }
	        }
	    }
	
}
