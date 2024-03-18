package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.Helper;
import model.Customer;

public class CustomerForm {
	
	private Customer customer;
	
	private JFrame frame;
	private JLabel customerIdLabel;
	private JTextField customerIdField;
	private JLabel companyLabel;
	private JTextField companyField;
	private JLabel contactPersonLabel;
	private JTextField contactPersonField;
	private JLabel address1Label;
	private JTextField address1Field;
	private JLabel address2Label;
	private JTextField address2Field;
	private JLabel postOfficeLabel;
	private JTextField postOfficeField;
	private JLabel parishLabel;
	private JTextField parishField;
	private JLabel telephoneLabel;
	private JTextField telephoneField;
	private JLabel emailLabel;
	private JTextField emailField;
	
	private JLabel statusLabel;
	private String status[] = {"Active", "Inactive"};
	private JComboBox<String> statusComboBox;
	
	private JButton submitButton;
	private JButton backButton;
	
	
	public CustomerForm() {
		
		frame = new JFrame("Enter a new Customer Record.");
		frame.setSize(400, 400);
		frame.setVisible(true);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		customerIdLabel = new JLabel("Customer ID: ");
		customerIdLabel.setBounds(50, 50, 100, 30);
		frame.add(customerIdLabel);
		
		customerIdField = new JTextField();
		customerIdField.setBounds(190, 50, 150, 30);
		frame.add(customerIdField);
		
		companyLabel = new JLabel("Company: ");
		companyLabel.setBounds(50, 100, 100, 30);
		frame.add(companyLabel);
		
		companyField = new JTextField();
		companyField.setBounds(190, 100, 150, 30);
		frame.add(companyField);
		
		contactPersonLabel = new JLabel("Contact Person: ");
		contactPersonLabel.setBounds(50, 150, 100, 30);
		frame.add(contactPersonLabel);
		
		contactPersonField = new JTextField();
		contactPersonField.setBounds(190, 150, 150, 30);
		frame.add(contactPersonField);
		
		address1Label = new JLabel("Address 1: ");
		address1Label.setBounds(50, 200, 70, 30);
		frame.add(address1Label);
		
		address1Field = new JTextField();
		address1Field.setBounds(190, 200, 150, 30);
		frame.add(address1Field);
		
		address2Label = new JLabel("Address 2: ");
		address2Label.setBounds(50, 250, 70, 30);
		frame.add(address2Label);
		
		address2Field = new JTextField();
		address2Field.setBounds(190, 250, 150, 30);
		frame.add(address2Field);
		
		postOfficeLabel = new JLabel("Post Office: ");
		postOfficeLabel.setBounds(50, 300, 70, 30);
		frame.add(postOfficeLabel);
		
		postOfficeField = new JTextField();
		postOfficeField.setBounds(190, 300, 150, 30);
		frame.add(postOfficeField);
		
		parishLabel = new JLabel("Parish: ");
		parishLabel.setBounds(50, 350, 70, 30);
		frame.add(parishLabel);
		
		parishField = new JTextField();
		parishField.setBounds(190, 350, 150, 30);
		frame.add(parishField);
		
		telephoneLabel = new JLabel("Telephone Number: ");
		telephoneLabel.setBounds(50, 400, 130, 30);
		frame.add(telephoneLabel);
		
		telephoneField = new JTextField();
		telephoneField.setBounds(190, 400, 150, 30);
		frame.add(telephoneField);
		
		emailLabel = new JLabel("Email: ");
		emailLabel.setBounds(50, 450, 70, 30);
		frame.add(emailLabel);
		
		emailField = new JTextField();
		emailField.setBounds(190, 450, 150, 30);
		frame.add(emailField);
		
		statusLabel = new JLabel("Status: ");
		statusLabel.setBounds(50, 500, 70, 30);
		frame.add(statusLabel);
		
		statusComboBox = new JComboBox<String>(status);
		statusComboBox.setBounds(190, 500, 150, 30);
		frame.add(statusComboBox);
		
		backButton = new JButton("Go Back");
		backButton.setBounds(50, 550, 100, 30);
		frame.add(backButton);
		
		submitButton = new JButton("Submit");
		submitButton.setBounds(190, 550, 100, 30);
		frame.add(submitButton);
		
		submitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Get data from form fields
                String customerID = customerIdField.getText();
                String company = companyField.getText();
                String contactPerson = contactPersonField.getText();
                String address1 = address1Field.getText();
                String address2 = address2Field.getText();
                String postOffice = postOfficeField.getText();
                String parish = parishField.getText();
                String telephone = telephoneField.getText();
                String email = emailField.getText();
                String status = statusComboBox.getSelectedItem().toString();
                
                // Use the instance variable footWear
                customer = new Customer(customerID, company, contactPerson, address1, address2, postOffice, parish, telephone, email, status);
                
                // Call Helper's createARecord method
                Helper.createARecord(customer);
                
                // Clear form fields
                clearFormFields();
				
			}});	
	}
	
	// Method to clear form fields
    private void clearFormFields() {
    	customerIdField.setText("");
    	companyField.setText("");
    	contactPersonField.setText("");
    	address1Field.setText("");
    	address2Field.setText("");
    	postOfficeField.setText("");
    	parishField.setText("");
    	telephoneField.setText("");
    	emailField.setText("");
        statusComboBox.setSelectedIndex(0); // Reset size to the first value
    }

	public static void main(String[] args) {
		new CustomerForm();

	}

}
