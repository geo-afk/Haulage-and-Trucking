package src.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class GetCustomerBalance {

    private static final Logger logger = LogManager.getLogger(GetCustomerBalance.class);

    public static Map<String, String> getCustomerBalance(String customerName) {

        logger.info("getting Customer Balance From Database");

        String url = "jdbc:mysql://localhost:3306/trucking";
        String user = "root";
        String password = "pascal321";

        Map<String, String> balanceDetails = new HashMap<>();

        String query = "SELECT t.company, t.pay_status, c.status as active_customer, r.value as customer_balance " +
                "FROM trucking.trip as t " +
                "JOIN trucking.customer as c on c.company_name = t.company " +
                "JOIN trucking.route as ro on ro.id = t.route_id " +
                "LEFT JOIN trucking.rate as r on r.id = ro.rate_id " +
                "WHERE company = ?"; // Use PreparedStatement to avoid SQL injection

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, customerName); // Set parameter for prepared statement

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String payStatus = rs.getString("pay_status");
                    String activeCustomer = rs.getString("active_customer");
                    double customerBalance = rs.getDouble("customer_balance");

                    balanceDetails.put("pay_status", payStatus);
                    balanceDetails.put("active_customer", activeCustomer);
                    balanceDetails.put("customer_balance", String.valueOf(customerBalance));
                }
            }

        } catch (SQLException e) {
            logger.error("Error occurred while fetching customer balance: " + e.getMessage(), e);
        }

        return balanceDetails;
    }
}
