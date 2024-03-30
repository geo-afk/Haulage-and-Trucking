package src.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import model.Address;
import model.Rate;
import model.Route;

public class GetRouteFromDB {

    private static final Logger logger = LogManager.getLogger(GetRouteFromDB.class);

    // JDBC URL, username, and password of the database
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/trucking";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "pascal321";

    // SQL query to retrieve all routes
    private static final String GET_ALL_ROUTES_QUERY =
            "SELECT r.id, r.source, a1.address_1 as source_address1, a1.address_2 as source_address2, " +
            "a1.post_office as source_post_office, a1.parish as source_parish, " +
            "a2.address_1 as destination_address1, a2.address_2 as destination_address2, " +
            "a2.post_office as destination_post_office, a2.parish as destination_parish, " +
            "rt.description as rate_description, rt.value as rate_value " +
            "FROM route r " +
            "JOIN address a1 ON r.sourceAddress = a1.id " +
            "JOIN address a2 ON r.destinationAddress = a2.id " +
            "JOIN rate rt ON r.rate_id = rt.id";

    // Method to get all routes from the database and store them in a Map
    public static Map<Long, Route> getAllRoutes() {
        Map<Long, Route> routeMap = new HashMap<>();
        logger.info("Getting all routes from database");

        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(GET_ALL_ROUTES_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                // Retrieve route detailsGet from the result set
                Long id = resultSet.getLong("id");
                String source = resultSet.getString("source");
                Address sourceAddress = new Address(resultSet.getString("source_address1"),
                        resultSet.getString("source_address2"),
                        resultSet.getString("source_post_office"),
                        resultSet.getString("source_parish"));
                Address destinationAddress = new Address(resultSet.getString("destination_address1"),
                        resultSet.getString("destination_address2"),
                        resultSet.getString("destination_post_office"),
                        resultSet.getString("destination_parish"));
                Rate rate = new Rate(resultSet.getString("rate_description"),
                        resultSet.getDouble("rate_value"));

                // Create Route object and add it to the map
                Route route = new Route(source, destinationAddress, sourceAddress, rate);
                route.setId(id);
                routeMap.put(id, route);
            }
        } catch (SQLException e) {
            logger.error("Error occurred while retrieving routes: " + e.getMessage(), e);
        }

        return routeMap;
    }
}
