package com.gui.trip;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.client.Client;
import com.constants.DatePicker;

import model.Customer;
import model.Route;
import model.Staff;
import model.Trip;





public class TripMainPanel extends JPanel implements ActionListener{
    

    private Map<Long, Route> routeMap = new HashMap<>();
    private List<Customer> customers;
    private List<Staff> staffs;
    private String[] payStatus = { "", "Paid", "Unpaid" };
   

    private JComboBox<String> billedByComboBox;

    private JComboBox<String> companyComboBox;
 

    private JComboBox<String> routeComboBox;  

    private JComboBox<String> driverComboBox;


    private DatePicker deliveryDateField;

    
    private JComboBox<String> payStatusComboBox;
    
    private JButton submitButton;



    public TripMainPanel() {
        internalPanel();

        getDriver();
        getStaffIds();
        getRoutes();
        getCustomer();
    }

    private void internalPanel() {

        GridBagConstraints constraints = new GridBagConstraints();
        setLayout(new GridBagLayout());

        setRoute(constraints);
        setDriver(constraints);
        setCompany(constraints);
        setBilledBy(constraints);

        setDeliveryDate(constraints);
        setPayStatus(constraints);
        setSubmitButton(constraints);

        setBorder(BorderFactory.createEmptyBorder(20, 15, 0, 0));

    }
    
    @SuppressWarnings("unchecked")
    private void getStaffIds() {

        Client client = new Client();
        client.sendAction("Get Staff Ids");

        List<String> ids = (List<String>) client.getObject();

        billedByComboBox.addItem("");

        if (ids.isEmpty()) {
            billedByComboBox.addItem("NULL");
        } else {
            for (String id : ids) {
                billedByComboBox.addItem(id);
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    private void getCustomer() {

        Client client = new Client();
        client.sendAction("Get Customer");

        customers = (List<Customer>) client.getObject();
        companyComboBox.addItem(" ");

        if (!customers.isEmpty()) {
      
            for (Customer customer : customers) {
                companyComboBox.addItem(customer.getCompanyName());
            }
        }
    }
    

    @SuppressWarnings("unchecked")
    private void getDriver() {
        
        Client client = new Client();
        client.sendAction("Get Staff");
        
        staffs = (List<Staff>) client.getObject();
        driverComboBox.addItem(" ");

        if (!staffs.isEmpty()) {

            for (Staff staff : staffs) {

                if (staff.getPosition().equals("Driver")) {
        
                    String staffName = staff.getFirstName() + " " + staff.getLastName();
                    
                    driverComboBox.addItem(staffName);
                }
            }
        }
    }


    @SuppressWarnings("unchecked")
    private void getRoutes() {
        Client client = new Client();
        client.sendAction("Get Route");
        routeMap = (Map<Long, Route>) client.getObject();
        routeComboBox.addItem(" ");

        if (!routeMap.isEmpty()) {

            for (Map.Entry<Long, Route> entry : routeMap.entrySet()) {

                String routeId = String.valueOf(entry.getKey());

                String sourceAddress = entry.getValue().getSourceAddress().getAddress1() + " "
                        + entry.getValue().getSourceAddress().getAddress2();

                String destinationAddress = entry.getValue().getDestinationAddress().getAddress1() + " "
                        + entry.getValue().getDestinationAddress().getAddress2();
                routeComboBox.addItem(routeId + " - " + sourceAddress + " To " + destinationAddress);

            }
        }
    }





    public void setCompany(GridBagConstraints constraints) {


        JLabel label = new JLabel("Company: ");
        label.setFont(new Font(null, Font.ITALIC, 15));
        constraints.gridx = 0;
        constraints.gridy = 0;

        constraints.insets = new Insets(20, 0, 0, 20);
        add(label, constraints);

        companyComboBox = new JComboBox<>();
        companyComboBox.setPreferredSize(new Dimension(500, 30));

        companyComboBox.addActionListener(this);
        constraints.gridx = 1;
        constraints.gridy = 0;

        add(companyComboBox, constraints);

    }


    public void setRoute(GridBagConstraints constraints) {

        JLabel label = new JLabel("Route: ");
        label.setFont(new Font(null, Font.ITALIC, 15));
        constraints.gridx = 0;
        constraints.gridy = 1;

        constraints.insets = new Insets(20, 0, 0, 20);
        add(label, constraints);

        routeComboBox = new JComboBox<>();
        routeComboBox.setPreferredSize(new Dimension(500, 30));
        constraints.gridx = 1;
        constraints.gridy = 1;

        add(routeComboBox, constraints);

    }
    
    public void setDriver(GridBagConstraints constraints) {
        
        JLabel label = new JLabel("Driver: ");
        label.setFont(new Font(null, Font.ITALIC, 15));
        constraints.gridx = 0;
        constraints.gridy = 2;

        constraints.insets = new Insets(20, 0, 0, 20);
        add(label, constraints);

        driverComboBox = new JComboBox<>();
        driverComboBox.setPreferredSize(new Dimension(500, 30));
        constraints.gridx = 1;
        constraints.gridy = 2;

        add(driverComboBox, constraints);

    }

    public void setBilledBy(GridBagConstraints constraints) {

        JLabel label = new JLabel("Billed By: ");
        label.setFont(new Font(null, Font.ITALIC, 15));
        constraints.gridx = 0;
        constraints.gridy = 3;

        constraints.insets = new Insets(20, 0, 0, 20);
        add(label, constraints);

        billedByComboBox = new JComboBox<>();
        billedByComboBox.setPreferredSize(new Dimension(500, 30));
        constraints.gridx = 1;
        constraints.gridy = 3;

        add(billedByComboBox, constraints);


    }
    
    public void setDeliveryDate(GridBagConstraints constraints) {

        JLabel label = new JLabel("Delivery Date: ");
        label.setFont(new Font(null, Font.ITALIC, 15));
        constraints.gridx = 0;
        constraints.gridy = 4;

        constraints.insets = new Insets(20, 0, 0, 20);
        add(label, constraints);

        deliveryDateField = new DatePicker();
        deliveryDateField.setPreferredSize(new Dimension(520, 30));
        constraints.gridx = 1;
        constraints.gridy = 4;

        add(deliveryDateField, constraints);

    }
    
    
    public void setPayStatus(GridBagConstraints constraints) {

        JLabel label = new JLabel("Pay Status: ");
        label.setFont(new Font(null, Font.ITALIC, 15));
        constraints.gridx = 0;
        constraints.gridy = 5;

        constraints.insets = new Insets(20, 0, 0, 20);
        add(label, constraints);

        payStatusComboBox = new JComboBox<>(payStatus);
        payStatusComboBox.setPreferredSize(new Dimension(500, 30));
        constraints.gridx = 1;
        constraints.gridy = 5;

        add(payStatusComboBox, constraints);

    }

    public void setSubmitButton(GridBagConstraints constraints) {

        submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(150, 30));
        submitButton.addActionListener(this);
        constraints.gridx = 1;
        constraints.gridy = 6;

        add(submitButton, constraints);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {

       

        if (e.getSource() == submitButton) {

            Client client = new Client();

            if (validateInputFields()) {
                Trip trip = convertToTrip();
                client.sendAction("Add Trip");
                client.sendObject(trip);

            }

            client.closeConnection();
        }
        if (e.getSource() == companyComboBox) {
            
            String companyName = companyComboBox.getSelectedItem().toString();

            for (Customer customer : customers) {
                if (customer.getCompanyName().equalsIgnoreCase(companyName) && (!customer.getStatus())) {
                     
                        
                    billedByComboBox.setEditable(false);
                    routeComboBox.setEditable(false);
                    submitButton.setEnabled(false);
                    driverComboBox.setEditable(false);
                    deliveryDateField.setEnabled(false);
                    payStatusComboBox.setEditable(false);

                    
                }
            }
        }
    }
    
    private boolean validateInputFields() {

        if (companyComboBox.getSelectedItem() == null) {
            System.out.println("ComboBox -> NULL NULL NULL");
        }

        
         if (companyComboBox.getSelectedItem().toString().isEmpty() && billedByComboBox.getSelectedItem().toString().isEmpty()
                && routeComboBox.getSelectedItem().toString().isEmpty() && driverComboBox.getSelectedItem().toString().isEmpty()
                && payStatusComboBox.getSelectedItem().toString().isEmpty() ) 
        {
            JOptionPane.showMessageDialog(null, "Please fill all the fields");
            return false;
        }
        return true;

    }
    
   public Trip convertToTrip() {
    String routeAddress = routeComboBox.getSelectedItem().toString();

    Long routeId = Long.valueOf(routeAddress.split(" - ")[0]);

    Route route = routeMap.get(routeId);

   
    return new Trip(companyComboBox.getSelectedItem().toString(), route, driverComboBox.getSelectedItem().toString(), billedByComboBox.getSelectedItem().toString(), deliveryDateField.convertToDate(), payStatusComboBox.getSelectedItem().toString());
}



}
