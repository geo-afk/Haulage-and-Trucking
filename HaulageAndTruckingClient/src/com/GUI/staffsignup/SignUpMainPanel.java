package com.gui.staffsignup;

import javax.swing.*;

import com.client.Client;
import com.constants.DatePicker;

import model.Address;
import model.Staff;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SignUpMainPanel extends JPanel implements ActionListener{

   
	private JTextField firstName;
    private JTextField lastName;

    private DatePicker dateOfBirth;

    private JTextField address1;
    private JTextField address2;


    private JTextField postOffice;

    private JTextField parish;
    private JTextField telephone;
    private JTextField email;
    private JComboBox<String> position;
    private JComboBox<String> status;

    private JPanel panel;
    private JButton signupButton;

    public SignUpMainPanel() {

        setLayout(new BorderLayout());
        internalPanel();
        add(panel, BorderLayout.CENTER);
    }

    private void internalPanel(){

        GridBagConstraints constraints = new GridBagConstraints();
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

    private void setPosition(GridBagConstraints constraints){

        JLabel label = new JLabel("Staff Position: ");
        label.setFont(new Font(null,Font.ITALIC, 15));
        constraints.gridx = 0;
        constraints.gridy = 9;

        constraints.insets = new Insets(20,0,0,0);
        panel.add(label, constraints);

        String[] p =  {"","Driver", "Maintenance","Admin"};
        position = new JComboBox<String>(p);


        position.setPreferredSize(new Dimension(510, 30));
        constraints.gridx = 1;
        constraints.gridy = 9;

        panel.add(position, constraints);



    }

    private void setStatus(GridBagConstraints constraints){

        JLabel label = new JLabel("Staff Status: ");
        label.setFont(new Font(null,Font.ITALIC, 15));
        constraints.gridx = 0;
        constraints.gridy = 10;

        constraints.insets = new Insets(20,0,0,0);
        panel.add(label, constraints);

        String[] st = {"","ACTIVE", "INACTIVE"};
        status = new JComboBox<String>(st);

        status.setPreferredSize(new Dimension(510, 30));
        constraints.gridx = 1;
        constraints.gridy = 10;

        panel.add(status, constraints);
    }



    private void initializeSignUpButton(GridBagConstraints constraints) {
    	signupButton = new JButton("Sign Up");
    	signupButton.setPreferredSize(new Dimension(90, 30));
    	signupButton.addActionListener(this);
    	
    	
    	 constraints.insets = new Insets(80,0,0,0);
        
        
         constraints.gridx = 2;
         constraints.gridy = 11;
         

         panel.add(signupButton, constraints);
        
    }

	@Override
    public void actionPerformed(ActionEvent e) {

        Client client = new Client();
        if (e.getSource() == signupButton) {

            if (validateInputFields()) {

                if (!isAfter2004(dateOfBirth.convertToDate())) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid date of birth");
                    return;
                } else {

                    Staff staff = convertToStaff();

                    client.sendAction("Add Staff");
                    client.sendObject(staff);

                }
            }
            client.closeConnection();
            
        }
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

       boolean staffStatus = status.getSelectedItem().equals("ACTIVE") ? true : false;

        
       return new Staff(firstName.getText(), lastName.getText(), dateOfBirth.convertToDate(),
         address,email.getText(), position.getSelectedItem().toString(), staffStatus);
    }


    @SuppressWarnings("deprecation")
    private  static boolean isAfter2004(Date bDate) {
        Date date = new Date();

        try {
            SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
            date = format.parse(bDate.toString());
        } catch (java.text.ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date.before(new Date(2004 - 1900, 0, 1)); 
  }


}
