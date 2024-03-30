package src.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import src.models.TripReport;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GetTripReport {

    private static final Logger logger = LogManager.getLogger(GetTripReport.class);

    public static List<TripReport> generateTripReport(String driverName, Timestamp startDate, Timestamp endDate) {

        List<TripReport> tripReports = new ArrayList<>();

        // Prepare the SQL query
        String sqlQuery = "SELECT t.invoiceNumber, t.company, CONCAT(a_source.address_1, ', ', a_source.address_2, ', ', a_source.parish) AS source_address, "
                + "CONCAT(a_dest.address_1, ', ', a_dest.address_2, ', ', a_dest.parish) AS destination_address, "
                + "ra.value as rate, t.driver_name, CONCAT(s.first_name, ' ', s.last_name) as billed_by "
                + "FROM trucking.trip t "
                + "JOIN trucking.route r ON r.id = t.route_id "
                + "LEFT JOIN trucking.address a_source ON a_source.id = r.sourceAddress "
                + "LEFT JOIN trucking.address a_dest ON a_dest.id = r.destinationAddress "
                + "LEFT JOIN trucking.rate ra on ra.id = r.rate_id "
                + "JOIN trucking.staff as s ON s.id = t.billed_by "
                + "WHERE t.driver_name = ? AND t.delivery_date BETWEEN ? AND ?";


        logger.info("generating query for trip reports: " + driverName);

        // Set parameters for the prepared statement
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/trucking", "root", "pascal321");
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setString(1, driverName);
            statement.setTimestamp(2, startDate);
            statement.setTimestamp(3, endDate);

            // Execute the query and retrieve the result set
            try (ResultSet resultSet = statement.executeQuery()) {
                // Process the result set and populate the list
                while (resultSet.next()) {
                    TripReport tripReport = new TripReport();
                    tripReport.setInvoiceNumber(resultSet.getLong("invoiceNumber"));
                    tripReport.setCompany(resultSet.getString("company"));
                    tripReport.setSourceAddress(resultSet.getString("source_address"));
                    tripReport.setDestinationAddress(resultSet.getString("destination_address"));
                    tripReport.setRate(resultSet.getDouble("rate"));
                    tripReport.setDriverName(resultSet.getString("driver_name"));
                    tripReport.setBilledBy(resultSet.getString("billed_by"));
                    tripReports.add(tripReport);
                }
            }
        } catch (SQLException e) {
            logger.error("Error occurred while generating trip report: " + e.getMessage(), e);
        }

        return tripReports;
    }
}
