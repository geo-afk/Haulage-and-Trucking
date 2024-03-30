package com.gui.staff;

import javax.swing.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.client.Client;
import com.constants.DatePicker;
import com.constants.EncryptPassword;
import com.constants.PasswordPanel;

import model.Address;
import model.Admin;
import model.Staff;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class StaffMainPanel extends JPanel implements ActionListener{

   
	private JTextField firstName;
    private JTextField lastName;

    private DatePicker dateOfBirth;

    private JTextField address1;
    private JTextField address2;


    private JTextField postOffice;

    private JTextField parish;
    private JTextField telephone;
    private JTextField email;
    private DatePicker startDate;
    private JComboBox<String> position;
    private JComboBox<String> status;

    private JPanel panel;
    private JButton signupButton;
    private GridBagConstraints constraints;

    private PasswordPanel passwordPanel;
    private PasswordPanel confirmPasswordPanel;


    private final String[] p = {"", "Driver", "Maintenance", "Admin"};

    private final static Logger logger = LogManager.getLogger(StaffMainPanel.class);


    public StaffMainPanel() {


        setLayout(new BorderLayout());
        internalPanel();
        add(panel, BorderLayout.CENTER);
    }

    private void internalPanel(){

        constraints = new GridBagConstraints();
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());


        setFirstName(constraints);
        setLastName(constraints);
        setDateOfBirth(constraints);
        setAddress1(constraints);
        setAddress2(constraints);
        setPostOffice(constraints);
        setParish(constraints);
        setTelephone(constraints);
        setEmail(constraints);
        setPosition(constraints);
        setStatus(constraints);
        setStartDate(constraints);
        initializeSignUpButton(constraints);

        panel.setBorder(BorderFactory.createEmptyBorder(20, 15, 0, 0));

    }

    private void setFirstName(GridBagConstraints constraints){

        JLabel label = new JLabel("First Name: ");
        label.setFont(new Font(null,Font.ITALIC, 15));
        constraints.gridx = 0;
        constraints.gridy = 0;


        panel.add(label, constraints);

        firstName = new JTextField(45);
        firstName.setPreferredSize(new Dimension(0, 30));
        constraints.gridx = 1;
        constraints.gridy = 0;

        panel.add(firstName, constraints);

    }

    private void setLastName(GridBagConstraints constraints){

        JLabel label = new JLabel("Last Name: ");
        label.setFont(new Font(null,Font.ITALIC, 15));
        constraints.gridx = 0;
        constraints.gridy = 1;

        constraints.insets = new Insets(20,0,0,0);
        panel.add(label, constraints);

        lastName = new JTextField(45);
        lastName.setPreferredSize(new Dimension(0, 30));
        constraints.gridx = 1;
        constraints.gridy = 1;

        panel.add(lastName, constraints);
    }

    private void setDateOfBirth(GridBagConstraints constraints){

        JLabel label = new JLabel("Date Of Birth: ");
        label.setFont(new Font(null,Font.ITALIC, 15));
        constraints.gridx = 0;
        constraints.gridy = 2;

        constraints.insets = new Insets(20,0,0,0);
        panel.add(label, constraints);

        
        dateOfBirth = new DatePicker();
        dateOfBirth.setPreferredSize(new Dimension(520, 30));
        constraints.gridx = 1;
        constraints.gridy = 2;

        panel.add(dateOfBirth, constraints);

    }

    private void setAddress1(GridBagConstraints constraints){

        JLabel label = new JLabel("Address 1: ");
        label.setFont(new Font(null,Font.ITALIC, 15));
        constraints.gridx = 0;
        constraints.gridy = 3;

        constraints.insets = new Insets(20,0,0,0);
        panel.add(label, constraints);

        address1 = new JTextField(45);
        address1.setPreferredSize(new Dimension(0, 30));
        constraints.gridx = 1;
        constraints.gridy = 3;

        panel.add(address1, constraints);

    }

    private void setAddress2(GridBagConstraints constraints){

        JLabel label = new JLabel("Address 2: ");
        label.setFont(new Font(null,Font.ITALIC, 15));
        constraints.gridx = 0;
        constraints.gridy = 4;

        constraints.insets = new Insets(20,0,0,0);
        panel.add(label, constraints);

        address2 = new JTextField(45);
        address2.setPreferredSize(new Dimension(0, 30));
        constraints.gridx = 1;
        constraints.gridy = 4;

        panel.add(address2, constraints);

    }

    private void setPostOffice(GridBagConstraints constraints){
        
        JLabel label = new JLabel("Post Office: ");
        label.setFont(new Font(null,Font.ITALIC, 15));
        constraints.gridx = 0;
        constraints.gridy = 5;

        constraints.insets = new Insets(20,0,0,0);
        panel.add(label, constraints);

        postOffice = new JTextField(45);
        postOffice.setPreferredSize(new Dimension(0, 30));
        constraints.gridx = 1;
        constraints.gridy = 5;

        panel.add(postOffice, constraints);

    }

    private void setParish(GridBagConstraints constraints){

        JLabel label = new JLabel("Parish: ");
        label.setFont(new Font(null,Font.ITALIC, 15));
        constraints.gridx = 0;
        constraints.gridy = 6;

        constraints.insets = new Insets(20,0,0,0);
        panel.add(label, constraints);

        parish = new JTextField(45);
        parish.setPreferredSize(new Dimension(0, 30));
        constraints.gridx = 1;
        constraints.gridy = 6;

        panel.add(parish, constraints);

    }

    private void setTelephone(GridBagConstraints constraints){

        JLabel label = new JLabel("Telephone: ");
        label.setFont(new Font(null,Font.ITALIC, 15));
        constraints.gridx = 0;
        constraints.gridy = 7;

        constraints.insets = new Insets(20,0,0,0);
        panel.add(label, constraints);

        telephone = new JTextField(45);
        telephone.setPreferredSize(new Dimension(0, 30));
        constraints.gridx = 1;
        constraints.gridy = 7;

        panel.add(telephone, constraints);

    }


    private void setEmail(GridBagConstraints constraints){

        JLabel label = new JLabel("Email: ");
        label.setFont(new Font(null,Font.ITALIC, 15));
        constraints.gridx = 0;
        constraints.gridy = 8;

        constraints.insets = new Insets(20,0,0,0);
        panel.add(label, constraints);

        email = new JTextField(45);
        email.setPreferredSize(new Dimension(0, 30));
        constraints.gridx = 1;
        constraints.gridy = 8;

        panel.add(email, constraints);

    }
    private void setPosition(GridBagConstraints constraints) {
        JLabel label = new JLabel("Staff Position: ");
        label.setFont(new Font(null, Font.ITALIC, 15));
        constraints.gridx = 0;
        constraints.gridy = 9;
        constraints.insets = new Insets(20, 0, 0, 0);
        panel.add(label, constraints);

        position = new JComboBox<>(p);
        position.setPreferredSize(new Dimension(510, 30));
        position.addActionListener(e -> {
            String selectedItem = (String) position.getSelectedItem();
            if (selectedItem.equals("Admin")) {
                addAdminFields();
            } else {
                removeAdminFields(constraints);
            }
        });

        constraints.gridx = 1;
        constraints.gridy = 9;
        panel.add(position, constraints);
    }

    private void addAdminFields() {

        constraints = new GridBagConstraints();

        passwordPanel = new PasswordPanel("Password: ", 100);
        passwordPanel.setPreferredSize(new Dimension(760, 50));
        constraints.gridx = 1;
        constraints.gridy = 12;
        constraints.insets = new Insets(20, -140, 0, 0);
        constraints.anchor = GridBagConstraints.WEST;
        panel.add(passwordPanel, constraints);

        confirmPasswordPanel = new PasswordPanel("Confirm Password: ", 50);
        confirmPasswordPanel.setPreferredSize(new Dimension(760, 30));
        constraints.gridx = 1;
        constraints.gridy = 13;
        constraints.insets = new Insets(20, -144, 0, 0);
        constraints.anchor = GridBagConstraints.WEST;
        panel.add(confirmPasswordPanel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 14;
        panel.add(signupButton, constraints);
    }

    private void removeAdminFields(GridBagConstraints constraints) {
        panel.remove(signupButton);
        for (Component component : panel.getComponents()) {
            if (component instanceof PasswordPanel) {
                panel.remove(component);
            }
        }
        constraints.gridx = 1;
        constraints.gridy = 12;
        panel.add(signupButton, constraints);
        panel.revalidate();
        panel.repaint();
    }
    private void setStatus(GridBagConstraints constraints){

        JLabel label = new JLabel("Staff Status: ");
        label.setFont(new Font(null,Font.ITALIC, 15));
        constraints.gridx = 0;
        constraints.gridy = 10;

        constraints.insets = new Insets(20,0,0,0);
        panel.add(label, constraints);

        String[] st = {"","ACTIVE", "INACTIVE"};
        status = new JComboBox<>(st);

        status.setPreferredSize(new Dimension(510, 30));
        constraints.gridx = 1;
        constraints.gridy = 10;

        panel.add(status, constraints);
    }


     private void setStartDate(GridBagConstraints constraints){

        JLabel label = new JLabel("Start Date: ");
        label.setFont(new Font(null,Font.ITALIC, 15));
        constraints.gridx = 0;
        constraints.gridy = 11;

        constraints.insets = new Insets(20,0,0,0);
        panel.add(label, constraints);

        
        startDate = new DatePicker();
        startDate.setPreferredSize(new Dimension(520, 30));
        constraints.gridx = 1;
        constraints.gridy = 11;

        panel.add(startDate, constraints);
    }


   



    private void initializeSignUpButton(GridBagConstraints constraints) {
    	signupButton = new JButton("Submit");
    	signupButton.setPreferredSize(new Dimension(200, 30));
    	signupButton.addActionListener(this);
    	
    	
    	constraints.insets = new Insets(20,0,0,0);
        
        
        constraints.gridx = 1;
        constraints.gridy = 12;
         

        panel.add(signupButton, constraints);
        
    }

	@Override
    public void actionPerformed(ActionEvent e) {

        Client client = new Client();
        if (e.getSource() == signupButton) {

            if (validateInputFields()) {

                logger.info("Validating staff input fields");
                if (!isAfter2004(dateOfBirth.convertToDate()) && !isAfter2023(startDate.convertToDate())) {
                    
                    logger.info("Is Date after converting from inout Field after 2004 and after 2023");
                    JOptionPane.showMessageDialog(null, "Please enter a valid date of birth");
                    return;
                } else {

                    Staff staff = convertToStaff();


                    if (isAdmin()) {


                        Admin admin = new Admin();
                        admin.setUsername(firstName.getText().charAt(0) + lastName.getText());
                        admin.setPassword(EncryptPassword.encrypt(passwordPanel.getPasswordField()));

                        client.sendAction("Add Admin");
                        client.sendObject(staff);
                        client.sendObject(admin);
                    }
                    else {

                        client.sendAction("Add Staff");
                        client.sendObject(staff);
                        
                    }

                    

                }
            }
            client.closeConnection();

        }  
    }


    private boolean isAdmin() {
        
        if (position.getSelectedItem().toString().equalsIgnoreCase("Admin")) {

            if (passwordPanel.getPasswordField().equals(confirmPasswordPanel.getPasswordField())) {

                return true;
            }
        }

        return false;
    }
    

    private boolean validateInputFields() {

        if (firstName.getText().isEmpty() && lastName.getText().isEmpty() 
                && address1.getText().isEmpty() && parish.getText().isEmpty()
                && telephone.getText().isEmpty() && email.getText().isEmpty() && position.getSelectedItem().toString().isEmpty()
                && status.getSelectedItem().toString().isEmpty()) 
        {
            JOptionPane.showMessageDialog(null, "Please fill all the fields");
            return false;
        }
        return true;

    }
    
    public Staff convertToStaff() {

        Address address = new Address(address1.getText(),
                address2.getText(), postOffice.getText(), parish.getText());

       boolean staffStatus = status.getSelectedItem().equals("ACTIVE");

        
       return new Staff(firstName.getText(), lastName.getText(), dateOfBirth.convertToDate(),
         address,email.getText(), position.getSelectedItem().toString(), staffStatus);
    }


    @SuppressWarnings("deprecation")
    private static boolean isAfter2004(Date bDate) {
        Date date = new Date();

        try {
            SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
            date = format.parse(bDate.toString());
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return date.before(new Date(2004 - 1900, 0, 1));
    }
  

    @SuppressWarnings("deprecation")
    private  static boolean isAfter2023(Date bDate) {
        Date date = new Date();

        try {
            SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
            date = format.parse(bDate.toString());
        } catch (java.text.ParseException e) {
              logger.error("Error {} ", e.getMessage(), e);
        }
        return date.before(new Date(2023 - 1900, 0, 1)); 
  }


}
