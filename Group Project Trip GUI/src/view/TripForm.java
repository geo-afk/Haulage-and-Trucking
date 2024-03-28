package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.DbConn;
import controller.Helper;
import model.Trip;

public class TripForm {
	
	private Trip trip;

    private JFrame frame;
    private JLabel invoiceNoLabel;
    private JTextField invoiceNoField;
    private JLabel staffIDLabel;
    private JComboBox<String> staffIDComboBox;
    private JLabel companyLabel;
    private JTextField companyField;
    private JLabel sourceAddressLabel;
    private JTextField sourceAddressField;
    private JLabel destinationAddressLabel;
    private JTextField destinationAddressField;

    private JLabel routeLabel;
    private JComboBox<String> routeComboBox;

    private JLabel rateLabel;
    private JTextField rateField;

    private JLabel driverLabel;
    private JTextField driverField;
    private JLabel deliveryDateLabel;
    private JTextField deliveryDateField;

    private JLabel payStatusLabel;
    private String payStatus[] = {"Paid", "Unpaid"};
    private JComboBox<String> payStatusComboBox;
    
    private JButton backButton;
    private JButton submitButton;

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public TripForm() {
        initialize();
        fetchRoutesFromDatabase();
        addRouteComboBoxListener();
        fetchStaffIDsFromDatabase();
    }

    private void initialize() {
        frame = new JFrame("Book a trip");
        frame.setSize(400, 400);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        invoiceNoLabel = new JLabel("Invoice Number: ");
        invoiceNoLabel.setBounds(50, 50, 100, 30);
        frame.add(invoiceNoLabel);

        invoiceNoField = new JTextField();
        invoiceNoField.setBounds(200, 50, 150, 30);
        frame.add(invoiceNoField);

        staffIDLabel = new JLabel("Staff ID: ");
        staffIDLabel.setBounds(50, 100, 100, 30);
        frame.add(staffIDLabel);

        staffIDComboBox = new JComboBox<>(); 
        staffIDComboBox.setBounds(200, 100, 150, 30);
        frame.add(staffIDComboBox);

        companyLabel = new JLabel("Company: ");
        companyLabel.setBounds(50, 150, 100, 30);
        frame.add(companyLabel);

        companyField = new JTextField();
        companyField.setBounds(200, 150, 150, 30);
        frame.add(companyField);

        sourceAddressLabel = new JLabel("Source Address: ");
        sourceAddressLabel.setBounds(50, 200, 120, 30);
        frame.add(sourceAddressLabel);

        sourceAddressField = new JTextField();
        sourceAddressField.setBounds(200, 200, 150, 30);
        frame.add(sourceAddressField);

        destinationAddressLabel = new JLabel("Destination Address: ");
        destinationAddressLabel.setBounds(50, 250, 130, 30);
        frame.add(destinationAddressLabel);

        destinationAddressField = new JTextField();
        destinationAddressField.setBounds(200, 250, 150, 30);
        frame.add(destinationAddressField);

        routeLabel = new JLabel("Route: ");
        routeLabel.setBounds(50, 300, 150, 30);
        frame.add(routeLabel);

        routeComboBox = new JComboBox<>();
        routeComboBox.setBounds(200, 300, 170, 30);
        frame.add(routeComboBox);

        rateLabel = new JLabel("Rate: ");
        rateLabel.setBounds(50, 350, 70, 30);
        frame.add(rateLabel);

        rateField = new JTextField();
        rateField.setBounds(200, 350, 150, 30);
        rateField.setEditable(false);
        frame.add(rateField);
        
        driverLabel = new JLabel("Driver: ");
        driverLabel.setBounds(50, 400, 70, 30);
        frame.add(driverLabel);

        driverField = new JTextField();
        driverField.setBounds(200, 400, 150, 30);
        frame.add(driverField);
        
        deliveryDateLabel = new JLabel("Delivery Date: ");
        deliveryDateLabel.setBounds(50, 450, 130, 30);
        frame.add(deliveryDateLabel);

        deliveryDateField = new JTextField();
        deliveryDateField.setBounds(200, 450, 150, 30);
        deliveryDateField.setToolTipText("Enter Delivery Date (YYYY-MM-DD)");
        frame.add(deliveryDateField);
        
        payStatusLabel = new JLabel("Pay Status: ");
        payStatusLabel.setBounds(50, 500, 100, 30);
        frame.add(payStatusLabel);

        payStatusComboBox = new JComboBox<String>(payStatus);
        payStatusComboBox.setBounds(200, 500, 150, 30);
		frame.add(payStatusComboBox);
		
		backButton = new JButton("Go Back");
		backButton.setBounds(50, 550, 100, 30);
		frame.add(backButton);
		
		submitButton = new JButton("Submit");
		submitButton.setBounds(205, 550, 100, 30);
		frame.add(submitButton);
		
		submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                trip = new Trip();
                trip.setInvoiceNo(invoiceNoField.getText());
                trip.setStaffID((String) staffIDComboBox.getSelectedItem());
                trip.setCompany(companyField.getText());
                trip.setSourceAddress(sourceAddressField.getText());
                trip.setDestinationAddress(destinationAddressField.getText());
                trip.setRoute((String) routeComboBox.getSelectedItem());
                trip.setRate(Double.parseDouble(rateField.getText()));
                trip.setDriver(driverField.getText());
                trip.setPayStatus((String) payStatusComboBox.getSelectedItem());

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date deliveryDate = dateFormat.parse(deliveryDateField.getText());
                    trip.setDeliveryDate(deliveryDate);
                } catch (Exception ex) {
                    System.out.println("Error parsing delivery date: " + ex.getMessage());
                    return;
                }

                Helper.createARecord(trip);

                clearFields();
            }
        });
    }

    
    // Method to clear all input fields after submission
    private void clearFields() {
        invoiceNoField.setText("");
        staffIDComboBox.setSelectedIndex(0);
        companyField.setText("");
        sourceAddressField.setText("");
        destinationAddressField.setText("");
        routeComboBox.setSelectedIndex(0);
        rateField.setText("");
        driverField.setText("");
        deliveryDateField.setText("");
        payStatusComboBox.setSelectedIndex(0);
    }

    private void fetchRoutesFromDatabase() {
        try {
            connection = DbConn.getConnection();
            String query = "SELECT route FROM charge";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String route = resultSet.getString("route");
                routeComboBox.addItem(route);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void fetchStaffIDsFromDatabase() {
        try {
            connection = DbConn.getConnection();
            String query = "SELECT staffID FROM staff";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            ArrayList<String> staffIDs = new ArrayList<>();

            while (resultSet.next()) {
                String staffID = resultSet.getString("staffID");
                staffIDs.add(staffID);
            }

            for (String id : staffIDs) {
                staffIDComboBox.addItem(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    private void addRouteComboBoxListener() {
        routeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateRateField();
            }
        });
    }

    private void updateRateField() {
        String selectedRoute = (String) routeComboBox.getSelectedItem();
        if (selectedRoute != null) {
            try {
                String query = "SELECT rate FROM charge WHERE route = ?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, selectedRoute);
                resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    double rate = resultSet.getDouble("rate");
                    rateField.setText(String.valueOf(rate));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (resultSet != null) resultSet.close();
                    if (preparedStatement != null) preparedStatement.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        new TripForm();
    }
}



//Old code:
/*
package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.DbConn;
import controller.Helper;
import model.Trip;

public class TripForm {
	
	private Trip trip;

    private JFrame frame;
    private JLabel invoiceNoLabel;
    private JTextField invoiceNoField;
    private JLabel staffIDLabel;
    private JTextField staffIDField;
    private JLabel companyLabel;
    private JTextField companyField;
    private JLabel sourceAddressLabel;
    private JTextField sourceAddressField;
    private JLabel destinationAddressLabel;
    private JTextField destinationAddressField;

    private JLabel routeLabel;
    private JComboBox<String> routeComboBox;

    private JLabel rateLabel;
    private JTextField rateField;

    private JLabel driverLabel;
    private JTextField driverField;
    private JLabel deliveryDateLabel;
    private JTextField deliveryDateField;

    private JLabel payStatusLabel;
    private String payStatus[] = {"Paid", "Unpaid"};
    private JComboBox<String> payStatusComboBox;
    
    private JButton backButton;
    private JButton submitButton;

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public TripForm() {
        initialize();
        fetchRoutesFromDatabase();
        addRouteComboBoxListener();
    }

    private void initialize() {
        frame = new JFrame("Book a trip");
        frame.setSize(400, 400);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        invoiceNoLabel = new JLabel("Invoice Number: ");
        invoiceNoLabel.setBounds(50, 50, 100, 30);
        frame.add(invoiceNoLabel);

        invoiceNoField = new JTextField();
        invoiceNoField.setBounds(200, 50, 150, 30);
        frame.add(invoiceNoField);

        staffIDLabel = new JLabel("Staff ID: ");
        staffIDLabel.setBounds(50, 100, 100, 30);
        frame.add(staffIDLabel);

        staffIDField = new JTextField();
        staffIDField.setBounds(200, 100, 150, 30);
        frame.add(staffIDField);

        companyLabel = new JLabel("Company: ");
        companyLabel.setBounds(50, 150, 100, 30);
        frame.add(companyLabel);

        companyField = new JTextField();
        companyField.setBounds(200, 150, 150, 30);
        frame.add(companyField);

        sourceAddressLabel = new JLabel("Source Address: ");
        sourceAddressLabel.setBounds(50, 200, 120, 30);
        frame.add(sourceAddressLabel);

        sourceAddressField = new JTextField();
        sourceAddressField.setBounds(200, 200, 150, 30);
        frame.add(sourceAddressField);

        destinationAddressLabel = new JLabel("Destination Address: ");
        destinationAddressLabel.setBounds(50, 250, 130, 30);
        frame.add(destinationAddressLabel);

        destinationAddressField = new JTextField();
        destinationAddressField.setBounds(200, 250, 150, 30);
        frame.add(destinationAddressField);

        routeLabel = new JLabel("Route: ");
        routeLabel.setBounds(50, 300, 150, 30);
        frame.add(routeLabel);

        routeComboBox = new JComboBox<>();
        routeComboBox.setBounds(200, 300, 170, 30);
        frame.add(routeComboBox);

        rateLabel = new JLabel("Rate: ");
        rateLabel.setBounds(50, 350, 70, 30);
        frame.add(rateLabel);

        rateField = new JTextField();
        rateField.setBounds(200, 350, 150, 30);
        rateField.setEditable(false);
        frame.add(rateField);
        
        driverLabel = new JLabel("Driver: ");
        driverLabel.setBounds(50, 400, 70, 30);
        frame.add(driverLabel);

        driverField = new JTextField();
        driverField.setBounds(200, 400, 150, 30);
        frame.add(driverField);
        
        deliveryDateLabel = new JLabel("Delivery Date: ");
        deliveryDateLabel.setBounds(50, 450, 130, 30);
        frame.add(deliveryDateLabel);

        deliveryDateField = new JTextField();
        deliveryDateField.setBounds(200, 450, 150, 30);
        deliveryDateField.setToolTipText("Enter Delivery Date (YYYY-MM-DD)");
        frame.add(deliveryDateField);
        
        payStatusLabel = new JLabel("Pay Status: ");
        payStatusLabel.setBounds(50, 500, 100, 30);
        frame.add(payStatusLabel);

        payStatusComboBox = new JComboBox<String>(payStatus);
        payStatusComboBox.setBounds(200, 500, 150, 30);
		frame.add(payStatusComboBox);
		
		backButton = new JButton("Go Back");
		backButton.setBounds(50, 550, 100, 30);
		frame.add(backButton);
		
		submitButton = new JButton("Submit");
		submitButton.setBounds(205, 550, 100, 30);
		frame.add(submitButton);
		
		submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a new Trip object with the entered information
                trip = new Trip();
                trip.setInvoiceNo(invoiceNoField.getText());
                trip.setStaffID(staffIDField.getText());
                trip.setCompany(companyField.getText());
                trip.setSourceAddress(sourceAddressField.getText());
                trip.setDestinationAddress(destinationAddressField.getText());
                trip.setRoute((String) routeComboBox.getSelectedItem());
                trip.setRate(Double.parseDouble(rateField.getText())); // Assuming rate is always entered
                trip.setDriver(driverField.getText());
                trip.setPayStatus((String) payStatusComboBox.getSelectedItem());

                // Parse the delivery date
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date deliveryDate = dateFormat.parse(deliveryDateField.getText());
                    trip.setDeliveryDate(deliveryDate);
                } catch (Exception ex) {
                    System.out.println("Error parsing delivery date: " + ex.getMessage());
                    return; // Don't proceed if date is invalid
                }

                //Call the Helper method to insert the trip into the database
                Helper.createARecord(trip);

                //Clear fields after submission
                clearFields();
            }
        });
    }
    
    // Method to clear all input fields after submission
    private void clearFields() {
        invoiceNoField.setText("");
        staffIDField.setText("");
        companyField.setText("");
        sourceAddressField.setText("");
        destinationAddressField.setText("");
        routeComboBox.setSelectedIndex(0);
        rateField.setText("");
        driverField.setText("");
        deliveryDateField.setText("");
        payStatusComboBox.setSelectedIndex(0);
    }

    private void fetchRoutesFromDatabase() {
        try {
            connection = DbConn.getConnection();
            String query = "SELECT route FROM charge";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String route = resultSet.getString("route");
                routeComboBox.addItem(route);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addRouteComboBoxListener() {
        routeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateRateField();
            }
        });
    }

    private void updateRateField() {
        String selectedRoute = (String) routeComboBox.getSelectedItem();
        if (selectedRoute != null) {
            try {
                String query = "SELECT rate FROM charge WHERE route = ?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, selectedRoute);
                resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    double rate = resultSet.getDouble("rate");
                    rateField.setText(String.valueOf(rate));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (resultSet != null) resultSet.close();
                    if (preparedStatement != null) preparedStatement.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        new TripForm();
    }
}
*/



