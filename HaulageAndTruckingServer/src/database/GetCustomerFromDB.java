package src.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Address;
import model.Customer;

public class GetCustomerFromDB {

    private static final Logger logger = LogManager.getLogger(GetCustomerFromDB.class);

    // JDBC URL, username, and password of the database
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/trucking";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "pascal321";

    // SQL query to retrieve all customers
    private static final String GET_ALL_CUSTOMERS_QUERY =
            "SELECT * FROM customer";

    // Method to get all customers from the database
    public static List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();

        logger.info("Getting all customers details from database");

        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(GET_ALL_CUSTOMERS_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                // Retrieve customer details from the result set
                String companyName = resultSet.getString("company_name");
                String contactPerson = resultSet.getString("contact_person");
                String telephone = resultSet.getString("telephone");
                String email = resultSet.getString("email");
                boolean status = resultSet.getBoolean("status");

                // Retrieve address details using address_id from the result set
                Address address = null;

                // Create Customer object and add it to the list
                Customer customer = new Customer(companyName, contactPerson, address, telephone, email, status);
                customers.add(customer);
            }
        } catch (SQLException e) {
            logger.error("Error occurred while retrieving customers: " + e.getMessage(), e);
        }

        return customers;
    }

    // Method to retrieve address details by address ID
}
