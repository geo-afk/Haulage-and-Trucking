package src.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;
import java.util.*;

public class GetDriverMap {

    private static final Logger logger = LogManager.getLogger(GetDriverMap.class);

    private static final String url = "jdbc:mysql://localhost:3306/trucking";
    private static final String user = "root";
    private static final String password = "pascal321";

    public static Map<String, List<Double>> DriverAndValueMap() {
        // Create a map to store results
        Map<String, List<Double>> driverRateMap = new HashMap<>();

        logger.info("Getting driver details from database and the balance the owe");

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT t.driver_name, ra.value AS rate_value " +
                         "FROM trucking.trip AS t " +
                         "JOIN trucking.route AS r ON t.route_id = r.id " +
                         "JOIN trucking.rate AS ra ON r.rate_id = ra.id";

            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    String driverName = rs.getString("driver_name");
                    double rateValue = rs.getDouble("rate_value");

                    // Check if the driver already exists in the map
                    if (driverRateMap.containsKey(driverName)) {
                        // If yes, add the rate value to the existing list
                        driverRateMap.get(driverName).add(rateValue);
                    } else {
                        // If not, create a new list with the rate value and put it in the map
                        List<Double> rateList = new ArrayList<>();
                        rateList.add(rateValue);
                        driverRateMap.put(driverName, rateList);
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("Error occurred while fetching driver rates: " + e.getMessage(), e);
        }

        return driverRateMap;
    }
}
