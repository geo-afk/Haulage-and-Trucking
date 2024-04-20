package gui.payslip;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import client.Client;
import constants.DatePicker;
import model.PaySlip;
import model.Staff;

public class PaySlipMainPanel extends JPanel implements ActionListener {

    private static final String ADMIN = "Admin";

    private DefaultTableModel paySlipTableModel;
    private List<PaySlip> paySlipList;
    private Map<String, List<Double>> driverMap;

    private JButton addButton;

    private JComboBox<String> staffComboBox;
    private JComboBox<String> adminComboBox;

    private List<Staff> staffList;

    public PaySlipMainPanel() {
        paySlipList = new ArrayList<>();
        initializePanel();
        getStaff();
        getDriverMap();
    }

    private void initializePanel() {
        setLayout(new BorderLayout());
        JPanel tablePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Create the table model and populate with column names
        paySlipTableModel = new DefaultTableModel();
        paySlipTableModel.addColumn("Staff Type");
        paySlipTableModel.addColumn("Staff Name");
        paySlipTableModel.addColumn("Start Date");
        paySlipTableModel.addColumn("End Date");
        paySlipTableModel.addColumn("Salary");
        paySlipTableModel.addColumn(ADMIN);

        // Create the table
        JTable paySlipTable = new JTable(paySlipTableModel);
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

    @SuppressWarnings("unchecked")
    private void getStaff() {
        Client client = new Client();
        client.sendAction("Get Staff");
        staffList = (List<Staff>) client.getObject();

        adminComboBox = new JComboBox<>();
        staffComboBox = new JComboBox<>();

        adminComboBox.addItem("");
        staffComboBox.addItem(" ");

        if (staffList.isEmpty()) {
            staffComboBox.addItem("NULL");
        } else {
            staffList.stream()
                    .filter(staff -> staff.getPosition().equalsIgnoreCase(ADMIN))
                    .forEach(staff -> {
                        String staffName = formatStaffName(staff);
                        adminComboBox.addItem(staffName);
                        staffComboBox.addItem(staffName);
                    });
            staffList.stream()
                    .filter(staff -> !staff.getPosition().equalsIgnoreCase(
                            ADMIN))
                    .forEach(staff -> {
                        String staffName = formatStaffName(staff);
                        staffComboBox.addItem(staffName);
                    });
        }
    }

    @SuppressWarnings("unchecked")
    private void getDriverMap() {
        Client client = new Client();
        client.sendAction("Get Driver Map");
        driverMap = (Map<String, List<Double>>) client.getObject();
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

            Double salary = calculateSalary(staffName, startDate, endDate);

            Staff staff = getStaffByName(staffName);
            PaySlip paySlip = new PaySlip(staff, startDate, endDate, salary, admin);
            paySlipList.add(paySlip);

            updatePaySlipTable();
        }
    }

    private double calculateSalary(String staffName, Date startDate, Date endDate) {
        Staff staff = getStaffByName(staffName);
        if (staff.getPosition().equalsIgnoreCase("Driver")) {
            return calculateDriverSalary(staffName);
        } else {
            return calculateSalaryFor30Days(60000, endDate, startDate);
        }
    }

    private double calculateDriverSalary(String staffName) {
        String firstName = staffName.split(" - ")[1].split(" ")[0];

        // Iterate through the entries in the driverMap
        for (Map.Entry<String, List<Double>> entry : driverMap.entrySet()) {
            // Check if the key of the map contains the fullName
            if (entry.getKey().contains(firstName)) {
                // If found, get the corresponding list of earnings
                List<Double> driverEarnings = entry.getValue();
                double sum = driverEarnings.stream().mapToDouble(Double::doubleValue).sum();
                return sum * 0.3;
            }
        }

        // If no matching key is found, return a default value
        return 4;
    }

    private double calculateSalaryFor30Days(double fixedSalary, Date endDate, Date startDate) {
        long millisecondsPerDay = (long) 24 * 60 * 60 * 1000; // Number of milliseconds in a day
        long daysSinceHire = (endDate.getTime() - startDate.getTime()) / millisecondsPerDay;
        int periodsPassed = (int) (daysSinceHire / 30);
        return fixedSalary * periodsPassed;
    }

    private Staff getStaffByName(String staffName) {
        long id = Long.parseLong(staffName.split(" - ")[0]);
        return staffList.stream()
                .filter(staff -> staff.getId().equals(id))
                .findFirst()
                .orElse(new Staff());
    }

    private String formatStaffName(Staff staff) {
        return staff.getId() + " - " + staff.getFirstName() + " " + staff.getLastName();
    }

    private void updatePaySlipTable() {
        paySlipTableModel.setRowCount(0);
        for (PaySlip paySlip : paySlipList) {
            Object[] rowData = {
                    paySlip.getStaff().getPosition(),
                    formatStaffName(paySlip.getStaff()),
                    paySlip.getStartDate(),
                    paySlip.getEndDate(),
                    paySlip.getSalary(),
                    paySlip.getAdmin()
            };
            paySlipTableModel.addRow(rowData);
        }
    }
}