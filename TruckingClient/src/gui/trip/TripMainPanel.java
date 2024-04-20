package gui.trip;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import client.Client;
import constants.DatePicker;
import model.Customer;
import model.CustomerBalance;
import model.Route;
import model.Staff;
import model.Trip;

public class TripMainPanel extends JPanel implements ActionListener {

    private List<Customer> customers;
    private List<Staff> staffs;
    private List<Route> routes;

    private JComboBox<String> billedByComboBox;
    private JComboBox<String> companyComboBox;
    private JComboBox<String> routeComboBox;
    private JComboBox<String> driverComboBox;
    private DatePicker deliveryDateField;
    private JSpinner paidSpinner;
    private JRadioButton paid;
    private JRadioButton unPaid;
    private JButton submitButton;

    public TripMainPanel() {
        initializePanel();
        getStaff();
        getRoutes();
        getCustomers();
    }

    private void initializePanel() {
        GridBagConstraints constraints = new GridBagConstraints();
        setLayout(new GridBagLayout());

        createCompanyField(constraints);
        createRouteField(constraints);
        createDriverField(constraints);
        createBilledByField(constraints);
        createDeliveryDateField(constraints);
        createPaymentFields(constraints);
        createSubmitButton(constraints);

        setBorder(BorderFactory.createEmptyBorder(20, 15, 0, 0));
    }

    @SuppressWarnings("unchecked")
    private void getStaff() {
        Client client = new Client();
        client.sendAction("Get Staff");
        staffs = (List<Staff>) client.getObject();

        billedByComboBox.addItem("");
        driverComboBox.addItem(" ");
        if (staffs != null) {

            staffs.forEach(
                    staff -> {
                        String firstName = staff.getFirstName();
                        String lastName = staff.getLastName();
                        billedByComboBox.addItem(staff.getId() + " - " + firstName + " " + lastName);
                    });

            staffs.stream()
                    .filter(staff -> staff.getPosition().equals("Driver"))
                    .forEach(staff -> driverComboBox.addItem(formatStaffName(staff)));
        }
    }

    @SuppressWarnings("unchecked")
    private void getCustomers() {
        Client client = new Client();
        client.sendAction("Get Customer");
        customers = (List<Customer>) client.getObject();
        companyComboBox.addItem(" ");

        if (customers != null) {
            customers.forEach(customer -> companyComboBox.addItem(customer.getCompanyName()));
        }
    }

    @SuppressWarnings("unchecked")
    private void getRoutes() {
        Client client = new Client();
        client.sendAction("Get Route");
        routes = (List<Route>) client.getObject();
        routeComboBox.addItem(" ");

        if (routes != null) {

            routes.forEach(route -> {

                String routeDetails = formatRouteDetails(route.getId(), route);
                routeComboBox.addItem(routeDetails);
            });

        } else {
            System.out.println("Empty");
        }
    }

    private void createCompanyField(GridBagConstraints constraints) {
        companyComboBox = new JComboBox<>();
        createLabeledComponent(constraints, 0, 0, "Company: ", companyComboBox);
    }

    private void createRouteField(GridBagConstraints constraints) {
        routeComboBox = new JComboBox<>();
        createLabeledComponent(constraints, 0, 1, "Route: ", routeComboBox);
    }

    private void createDriverField(GridBagConstraints constraints) {
        driverComboBox = new JComboBox<>();
        createLabeledComponent(constraints, 0, 2, "Driver: ", driverComboBox);
    }

    private void createBilledByField(GridBagConstraints constraints) {
        billedByComboBox = new JComboBox<>();
        createLabeledComponent(constraints, 0, 3, "Billed By: ", billedByComboBox);
    }

    private void createDeliveryDateField(GridBagConstraints constraints) {
        deliveryDateField = new DatePicker();
        createLabeledComponent(constraints, 0, 4, "Delivery Date: ", deliveryDateField);
    }

    private void createPaymentFields(GridBagConstraints constraints) {
        paid = new JRadioButton("Trip Paid in Full");
        paid.addActionListener(this);

        unPaid = new JRadioButton("Trip Unpaid for");
        unPaid.addActionListener(this);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(unPaid);
        buttonGroup.add(paid);

        constraints.gridx = 0;
        constraints.gridy = 6;
        constraints.insets = new Insets(40, 80, 0, 0);
        add(paid, constraints);

        constraints.insets = new Insets(40, 0, 0, 10);
        constraints.gridx++;
        add(unPaid, constraints);

        JLabel label = new JLabel("Customer Deposit: ");
        label.setFont(new Font(null, Font.ITALIC, 15));
        constraints.gridx = 0;
        constraints.gridy = 7;
        constraints.insets = new Insets(20, 0, 0, 20);
        add(label, constraints);

        paidSpinner = new JSpinner();
        paidSpinner.setPreferredSize(new Dimension(490, 30));
        constraints.gridx = 1;
        constraints.gridy = 7;
        add(paidSpinner, constraints);
    }

    private void createSubmitButton(GridBagConstraints constraints) {
        submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(150, 30));
        submitButton.addActionListener(this);
        constraints.gridx = 1;
        constraints.gridy = 8;
        add(submitButton, constraints);
    }

    private void createLabeledComponent(GridBagConstraints constraints, int gridx, int gridy, String label,
            JComponent component) {
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font(null, Font.ITALIC, 15));
        constraints.gridx = gridx;
        constraints.gridy = gridy;
        constraints.insets = new Insets(20, 0, 0, 20);
        add(lbl, constraints);

        component.setPreferredSize(new Dimension(500, 30));
        constraints.gridx = gridx + 1;
        constraints.gridy = gridy;
        add(component, constraints);
    }

    private String formatStaffName(Staff staff) {
        return staff.getFirstName() + " " + staff.getLastName();
    }

    private String formatRouteDetails(long id, Route route) {

        String sourceAddress = "";
        String destinationAddress = "";

        if (route.getSourceAddress() != null) {
            sourceAddress = route.getSourceAddress().getAddress1() + " " + route.getSourceAddress().getAddress2();
        } else {
            sourceAddress = "NULL";
        }

        if (route.getDestinationAddress() != null) {
            destinationAddress = route.getDestinationAddress().getAddress1() + " "
                    + route.getDestinationAddress().getAddress2();
        } else {

            sourceAddress = "NULL";

        }

        return id + " - [" + sourceAddress + ", " + destinationAddress + "]";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean customerPaid = false;
        String payStatus = "Un-Paid";

        if (e.getSource() == paid) {
            customerPaid = true;
            payStatus = "Paid";
            paidSpinner.setValue(0);
            paidSpinner.setEnabled(false);
        } else if (e.getSource() == unPaid) {
            paidSpinner.setEnabled(true);
        }

        if (e.getSource() == submitButton) {
            Client client = new Client();
            double totalPaid;

            if (validateInputFields()) {

                Trip trip = convertToTrip(payStatus);

                if (customerPaid) {
                    totalPaid = trip.getRoute().getRate();
                } else {
                    totalPaid = Double.valueOf(paidSpinner.getValue().toString());
                }

                client.sendAction("Add Trip");
                client.sendObject(trip);
                client.sendAction("Add Customer Balance");
                CustomerBalance customerBalance = convertToCustomerBalance(trip.getRoute(), totalPaid);
                client.sendObject(customerBalance);
            }

            client.closeConnection();
        }

        if (e.getSource() == companyComboBox) {
            String companyName = companyComboBox.getSelectedItem().toString();
            boolean isCustomerActive = customers.stream()
                    .anyMatch(customer -> customer.getCompanyName().equalsIgnoreCase(companyName)
                            && !customer.getStatus());

            setComponentsEnabled(!isCustomerActive);
        }
    }

    private boolean validateInputFields() {
        if (companyComboBox.getSelectedItem() == null) {
            System.out.println("ComboBox -> NULL NULL NULL");
        }

        if (companyComboBox.getSelectedItem().toString().isEmpty()
                || billedByComboBox.getSelectedItem().toString().isEmpty()
                || routeComboBox.getSelectedItem().toString().isEmpty()
                || driverComboBox.getSelectedItem().toString().isEmpty()
                || paidSpinner.getValue().toString().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill all the fields");
            return false;
        }
        return true;
    }

    private Trip convertToTrip(String payStatus) {
        String routeAddress = routeComboBox.getSelectedItem().toString();
        Long routeId = Long.valueOf(routeAddress.split(" - ")[0]);

        Route route = routes.stream().filter(e -> e.getId().equals(routeId)).toList().get(0);

        String fullName = billedByComboBox.getSelectedItem().toString().split(" - ")[1];

        return new Trip(companyComboBox.getSelectedItem().toString(), route,
                driverComboBox.getSelectedItem().toString(), fullName,
                deliveryDateField.convertToDate(), payStatus);
    }

    private CustomerBalance convertToCustomerBalance(Route route, double paidByCustomer) {

        String admin = billedByComboBox.getSelectedItem().toString();
        Long billedBy = Long.valueOf(admin.split(" - ")[0]);

        Staff staff = staffs.stream().filter(e -> e.getId().equals(billedBy)).toList().get(0);

        return new CustomerBalance(
                companyComboBox.getSelectedItem().toString(),
                route,
                paidByCustomer, // Convert Integer to Double
                driverComboBox.getSelectedItem().toString(),
                staff.getFirstName() + " " + staff.getLastName());
    }

    private void setComponentsEnabled(boolean enabled) {
        billedByComboBox.setEnabled(enabled);
        routeComboBox.setEnabled(enabled);
        submitButton.setEnabled(enabled);
        driverComboBox.setEnabled(enabled);
        deliveryDateField.setEnabled(enabled);
        paidSpinner.setEnabled(enabled);
        paidSpinner.setEnabled(enabled);
    }
}