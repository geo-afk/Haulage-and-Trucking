package src.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import model.Address;
import model.Staff;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GetStaffFromDB {

    private static final Logger logger = LogManager.getLogger(GetStaffFromDB.class);

    // JDBC URL, username, and password of the database
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/trucking";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "pascal321";

    // SQL query to retrieve all staff members
    private static final String GET_ALL_STAFF_QUERY =
            "SELECT * FROM staff";

    // Method to get all staff members from the database
    public static List<Staff> getAllStaff() {
        List<Staff> staffList = new ArrayList<>();

        logger.info("getting all staff details from database");

        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(GET_ALL_STAFF_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                // Retrieve staff details from the result set
                Long id = resultSet.getLong("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                Date dateOfBirth = resultSet.getDate("d_o_b");
                String email = resultSet.getString("email");
                String position = resultSet.getString("position");
                boolean status = resultSet.getBoolean("status");

                // Retrieve address details using address_id from the result set
                Address address = null;

                // Create Staff object and add it to the list
                Staff staff = new Staff(firstName, lastName, dateOfBirth, address, email, position, status);
                staff.setStaffId(id);
                staffList.add(staff);
            }
        } catch (SQLException e) {
            logger.error("Error occurred while retrieving staff members: " + e.getMessage(), e);
        }

        return staffList;
    }
}
