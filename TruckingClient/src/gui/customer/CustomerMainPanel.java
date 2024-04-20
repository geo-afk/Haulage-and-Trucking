package gui.customer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import client.Client;

import model.Address;
import model.Customer;

public class CustomerMainPanel extends JPanel implements ActionListener {

    private JTextField companyField;

    private JTextField contactPerson;

    private JTextField address1Field;

    private JTextField address2Field;

    private JTextField postOfficeField;

    private JTextField parishField;

    private JFormattedTextField telephoneField;

    private JTextField emailField;

    private String[] status = { "", "Active", "Inactive" };

    private JComboBox<String> statusComboBox;

    private JPanel panel;

    private JButton submitButton;

    private static final Logger logger = LogManager.getLogger(CustomerMainPanel.class);

    public CustomerMainPanel() {

        setLayout(new BorderLayout());
        internalPanel();
        add(panel, BorderLayout.CENTER);
    }

    private void internalPanel() {

        GridBagConstraints constraints = new GridBagConstraints();
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        setCompanyName(constraints);

        setContactPerson(constraints);

        setAddress(constraints);

        setPostOffice(constraints);

        setParishName(constraints);

        setTelephoneNumber(constraints);

        setEmail(constraints);
        setStatus(constraints);

        initializeSubmitButton(constraints);

        panel.setBorder(BorderFactory.createEmptyBorder(20, 15, 0, 0));

    }

    private void setCompanyName(GridBagConstraints constraints) {

        JLabel companyLabel = new JLabel("Company Name: ");
        companyLabel.setFont(new Font(null, Font.ITALIC, 15));

        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(companyLabel, constraints);

        companyField = new JTextField(45);
        companyField.setPreferredSize(new Dimension(0, 30));
        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(companyField, constraints);

    }

    private void setContactPerson(GridBagConstraints constraints) {

        JLabel contactPersonLabel = new JLabel("Contact Person: ");
        contactPersonLabel.setFont(new Font(null, Font.ITALIC, 15));
        constraints.gridx = 0;
        constraints.gridy = 1;

        constraints.insets = new Insets(20, 0, 0, 0);
        panel.add(contactPersonLabel, constraints);

        contactPerson = new JTextField(45);
        contactPerson.setPreferredSize(new Dimension(0, 30));

        constraints.gridx = 1;
        constraints.gridy = 1;

        panel.add(contactPerson, constraints);

    }

    private void setAddress(GridBagConstraints constraints) {

        JLabel address1Label = new JLabel("Address 1:");
        address1Label.setFont(new Font(null, Font.ITALIC, 15));
        constraints.gridx = 0;
        constraints.gridy = 2;

        constraints.insets = new Insets(20, 0, 0, 0);
        panel.add(address1Label, constraints);

        address1Field = new JTextField(45);
        address1Field.setPreferredSize(new Dimension(520, 30));
        constraints.gridx = 1;
        constraints.gridy = 2;

        panel.add(address1Field, constraints);

        JLabel address2Label = new JLabel("Address 2:");
        address2Label.setFont(new Font(null, Font.ITALIC, 15));
        constraints.gridx = 0;
        constraints.gridy = 3;

        constraints.insets = new Insets(20, 0, 0, 0);
        panel.add(address2Label, constraints);

        address2Field = new JTextField(45);
        address2Field.setPreferredSize(new Dimension(0, 30));
        constraints.gridx = 1;
        constraints.gridy = 3;

        panel.add(address2Field, constraints);

    }

    private void setPostOffice(GridBagConstraints constraints) {

        JLabel postOfficeLabel = new JLabel("Post Office: ");
        postOfficeLabel.setFont(new Font(null, Font.ITALIC, 15));
        constraints.gridx = 0;
        constraints.gridy = 4;

        constraints.insets = new Insets(20, 0, 0, 0);
        panel.add(postOfficeLabel, constraints);

        postOfficeField = new JTextField(45);
        postOfficeField.setPreferredSize(new Dimension(0, 30));
        constraints.gridx = 1;
        constraints.gridy = 4;

        panel.add(postOfficeField, constraints);

    }

    private void setParishName(GridBagConstraints constraints) {

        JLabel parishLabel = new JLabel("Parish: ");
        parishLabel.setFont(new Font(null, Font.ITALIC, 15));
        constraints.gridx = 0;
        constraints.gridy = 5;

        constraints.insets = new Insets(20, 0, 0, 0);
        panel.add(parishLabel, constraints);

        parishField = new JTextField(45);
        parishField.setPreferredSize(new Dimension(0, 30));
        constraints.gridx = 1;
        constraints.gridy = 5;

        panel.add(parishField, constraints);

    }

    private void setTelephoneNumber(GridBagConstraints constraints) {

        JLabel telephoneLabel = new JLabel("Telephone Number: ");
        telephoneLabel.setFont(new Font(null, Font.ITALIC, 15));
        constraints.gridx = 0;
        constraints.gridy = 6;

        constraints.insets = new Insets(20, 0, 0, 0);
        panel.add(telephoneLabel, constraints);

        MaskFormatter phoneMask;
        try {
            phoneMask = new MaskFormatter("(###)-###-####");
            phoneMask.setPlaceholderCharacter('_');
            telephoneField = new JFormattedTextField(phoneMask);
            telephoneField.setPreferredSize(new Dimension(100, 30));

        } catch (ParseException e) {
            logger.error("Error in setting up phone mask {}", e.getMessage());
        }

        constraints.gridx = 1;
        constraints.gridy = 6;

        panel.add(telephoneField, constraints);

    }

    private void setEmail(GridBagConstraints constraints) {

        JLabel emailLabel = new JLabel("Email: ");
        emailLabel.setFont(new Font(null, Font.ITALIC, 15));
        constraints.gridx = 0;
        constraints.gridy = 7;

        constraints.insets = new Insets(20, 0, 0, 0);
        panel.add(emailLabel, constraints);

        emailField = new JTextField(45);
        emailField.setPreferredSize(new Dimension(0, 30));
        constraints.gridx = 1;
        constraints.gridy = 7;

        panel.add(emailField, constraints);

    }

    private void setStatus(GridBagConstraints constraints) {

        JLabel statusLabel = new JLabel("Status: ");
        statusLabel.setFont(new Font(null, Font.ITALIC, 15));
        constraints.gridx = 0;
        constraints.gridy = 8;

        constraints.insets = new Insets(20, 0, 0, 0);
        panel.add(statusLabel, constraints);

        statusComboBox = new JComboBox<>(status);
        statusComboBox.setPreferredSize(new Dimension(510, 30));
        constraints.gridx = 1;
        constraints.gridy = 8;

        panel.add(statusComboBox, constraints);

    }

    private void initializeSubmitButton(GridBagConstraints constraints) {

        submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(200, 30));
        submitButton.addActionListener(this);

        constraints.insets = new Insets(20, 0, 0, 0);
        constraints.gridx = 1;
        constraints.gridy = 9;
        panel.add(submitButton, constraints);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Client client = new Client();
        if (e.getSource() == submitButton) {

            if (validateInputFields()) {

                Customer customer = convertToCustomer();
                clearFields();

                client.sendAction("Add Customer");
                client.sendObject(customer);

                logger.info(
                        "validating required input fields if empty, creating customer object and sending to server");

            }
            client.closeConnection();

        }
    }

    private boolean validateInputFields() {

        if (companyField.getText().isEmpty() && contactPerson.getText().isEmpty()
                && address1Field.getText().isEmpty() && address2Field.getText().isEmpty()
                && postOfficeField.getText().isEmpty()
                && parishField.getText().isEmpty() && telephoneField.getText().isEmpty()
                && emailField.getText().isEmpty()
                && statusComboBox.getSelectedItem().toString().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill all the fields");
            logger.info("required fields are empty");

            return false;
        }
        return true;

    }

    public Customer convertToCustomer() {

        Address address = new Address(address1Field.getText(),
                address2Field.getText(), postOfficeField.getText(), parishField.getText());

        logger.info("Creating customer object from inout fields");

        boolean accountStatus = false;
        accountStatus = statusComboBox.getSelectedItem().toString().equalsIgnoreCase("Active");

        return new Customer(companyField.getText(), contactPerson.getText(),
                address, telephoneField.getText(), emailField.getText(), accountStatus);
    }

    public void clearFields() {

        companyField.setText("");
        contactPerson.setText("");
        address1Field.setText("");
        address2Field.setText("");
        postOfficeField.setText("");
        parishField.setText("");
        telephoneField.setText("");
        emailField.setText("");
        statusComboBox.setSelectedIndex(0);

    }

}
