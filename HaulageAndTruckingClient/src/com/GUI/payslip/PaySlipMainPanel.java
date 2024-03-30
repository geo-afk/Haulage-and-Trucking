package com.gui.payslip;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.client.Client;
import com.constants.DatePicker;

import model.PaySlip;
import model.Staff;

public class PaySlipMainPanel extends JPanel implements ActionListener {

  
    private DefaultTableModel paySlipTableModel;
    private List<PaySlip> paySlipList;
    private Map<String, List<Double>> driverMap;


    private JButton addButton;


    private JComboBox<String> staffComboBox;
    private JComboBox<String> adminComboBox;



    private List<Staff> staffList;

    public PaySlipMainPanel() {

        
        paySlipList = new ArrayList<>();
        internalPanel();
        getStaff();
        getDriverMap();
    }


    @SuppressWarnings("unchecked")
    private void getStaff() {

        Client client = new Client();
        client.sendAction("Get Staff");
        staffList = (List<Staff>) client.getObject();


        adminComboBox.addItem("");
        staffComboBox.addItem(" ");

        if (staffList.isEmpty()) {
            staffComboBox.addItem("NULL");
        }

        else {
            for (Staff staff : staffList) {
                String staffName = staff.getStaffId() + " -  " + staff.getFirstName() + " " + staff.getLastName();

               
                if (staff.getPosition().equalsIgnoreCase("Admin")) {

                    adminComboBox.addItem(staffName);
                    staffComboBox.addItem(staffName);
                }
                else 
                {
                    staffComboBox.addItem(staffName);
                }
            }

        }
    }
    

    @SuppressWarnings("unchecked")
    private void getDriverMap() {
        Client client = new Client();
        client.sendAction("Get Driver Map");
        driverMap = (Map<String, List<Double>>) client.getObject();
    }
    
    private void internalPanel() {


        staffComboBox = new JComboBox<>();
        adminComboBox = new JComboBox<>();
        setLayout(new BorderLayout());
        JPanel tablePanel = new JPanel();

        tablePanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Create the table model and populate with column names
        paySlipTableModel = new DefaultTableModel();
        paySlipTableModel.addColumn("Staff Type");
        paySlipTableModel.addColumn("Staff Name");
        paySlipTableModel.addColumn("Start Date");
        paySlipTableModel.addColumn("End Date");
        paySlipTableModel.addColumn("Salary");
        paySlipTableModel.addColumn("Admin");

        // Create the table
        JTable paySlipTable = new JTable(paySlipTableModel);

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(paySlipTable);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(0, 10, 0, 0);

        tablePanel.add(scrollPane, gbc);

        add(tablePanel, BorderLayout.CENTER);
        
        // Create a button to add new payslips
        addButton = new JButton("Add PaySlip");
        addButton.setPreferredSize(new Dimension(260, 35));
        addButton.addActionListener(this);
        add(addButton, BorderLayout.SOUTH);
        

        setBorder(BorderFactory.createEmptyBorder(20, 15, 0, 0));
    }

    
    
    
    
    
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == addButton) {
            showPaySlipDialog();
        }
    }
    
    private void showPaySlipDialog() {

        DatePicker startDateField = new DatePicker();
        DatePicker endDateField = new DatePicker();
        Staff staff1 = new Staff();

        JPanel panel = new JPanel(new GridLayout(4, 2));

        panel.add(new JLabel("Staff:"));
        panel.add(staffComboBox);

        panel.add(new JLabel("Start Date (yyyy-mm-dd):"));
        panel.add(startDateField);

        panel.add(new JLabel("End Date (yyyy-mm-dd):"));
        panel.add(endDateField);

        panel.add(new JLabel("Admin:"));
        panel.add(adminComboBox);

        int result = JOptionPane.showConfirmDialog(this, panel, "Add PaySlip", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String staffName = staffComboBox.getSelectedItem().toString();
            Date startDate = startDateField.convertToDate();
            Date endDate = endDateField.convertToDate();
            String admin = adminComboBox.getSelectedItem().toString();

            Double salary = 55.4;

            for (Staff staff : staffList) {

                staff1.setFirstName(staff.getFirstName());
                staff1.setPosition(staff.getPosition());

                Long id = Long.parseLong(staffName.split(" -  ")[0]);

                if (staff.getStaffId() == id) {

                    if (staff.getPosition().equalsIgnoreCase("Driver")) {

                        for (Map.Entry<String, List<Double>> driver : driverMap.entrySet()) {

                            if (driver.getKey().equalsIgnoreCase(staffName)) {

                                double sum = driver.getValue().stream().mapToDouble(Double::doubleValue).sum();
                                salary = sum * 0.3;
                            }
                        }

                    } else {
                        salary = calculateSalaryFor30Days(60000, endDate, startDate);
                    }
                }
            }

            // Create a new PaySlip object
            PaySlip paySlip = new PaySlip(staff1, startDate, endDate, salary, admin);

            // Add the payslip to the list
            paySlipList.add(paySlip);

            // Update the table model
            updatePaySlipTable();
        }
    }

    // Method to calculate salary for every 30 days
    public double calculateSalaryFor30Days(double fixedSalary, Date endDate, Date startDate) {
        // Calculate the number of days since hire date
        long millisecondsPerDay = 24 * 60 * 60 * 1000; // Number of milliseconds in a day
        long daysSinceHire = (endDate.getTime() - startDate.getTime()) / millisecondsPerDay;
        
        // Calculate the number of 30-day periods passed
        int periodsPassed = (int) (daysSinceHire / 30);
        
        // Calculate the total salary
        return fixedSalary * periodsPassed;
    }
    
    private void updatePaySlipTable() {
        // paySlipTableModel.setRowCount(0); // Clear existing rows

        for (PaySlip paySlip : paySlipList) {
            Object[] rowData = {
                    paySlip.getStaff().getPosition(),
                    paySlip.getStaff().getFirstName(),
                    paySlip.getStartDate(),
                    paySlip.getEndDate(),
                    paySlip.getSalary(),
                    paySlip.getAdmin()
            };
            paySlipTableModel.addRow(rowData);
        }
    }
}
