package com.gui.report;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.client.Client;
import com.constants.DatePicker;

import model.Trip;

public class GenerateCustomerReport extends JPanel implements ActionListener {

	private JLabel startDateLabel;
	private DatePicker startDateTextField;

	private JLabel endDateLabel;
	private DatePicker endDateTextField;

	private JLabel driverLabel;
	private JTextField driverTextField;

	private JButton reportButton;

	private GridBagConstraints gbc;
	private JPanel topPanel;
	private JPanel bottomPanel;

	private JScrollPane scrollPane;


	private JTable invoiceTable;

	private DefaultTableModel model;

	public GenerateCustomerReport() {
		


		setLayout(new BorderLayout());
    	

		internalPanel();


		setVisible(true);
	}



	
	private void internalPanel(){

		topPanel = new JPanel();
        topPanel.setLayout(new GridBagLayout());


		gbc = new GridBagConstraints();

		initializeReportButton(gbc);
		initializeStartDateComponents(gbc);
		initializeEndDateComponents(gbc);
		initializeDriverNameComponents(gbc);
		
		
	
		add(topPanel, BorderLayout.NORTH);


		bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridBagLayout());

		gbc = new GridBagConstraints();

		initializeTable(gbc);
		add(bottomPanel, BorderLayout.CENTER);
	}


	private void initializeStartDateComponents(GridBagConstraints gbc) {
		startDateLabel = new JLabel("Start Date: ");

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		topPanel.add(startDateLabel, gbc);


		startDateTextField = new DatePicker();
		startDateTextField.setPreferredSize(new Dimension(520, 30));

		gbc.gridx = 1;
		gbc.gridy = 0;
		topPanel.add(startDateTextField, gbc);
	}

	private void initializeEndDateComponents(GridBagConstraints gbc) {
		
		endDateLabel = new JLabel("End Date: ");

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.WEST;
		topPanel.add(endDateLabel, gbc);

		endDateTextField = new DatePicker();
		endDateTextField.setPreferredSize(new Dimension(520, 30));

		gbc.gridx = 1;
		gbc.gridy = 1;
		topPanel.add(endDateTextField, gbc);
	}

	private void initializeDriverNameComponents(GridBagConstraints gbc) {
		driverLabel = new JLabel("Driver Name: ");

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.WEST;
		topPanel.add(driverLabel, gbc);


		driverTextField = new JTextField();
		driverTextField.setPreferredSize(new Dimension(250, 30));

		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.insets = new Insets(0, 30, 5, 0);
		topPanel.add(driverTextField, gbc);
	}

	private void initializeReportButton(GridBagConstraints gbc) {
		reportButton = new JButton("Generate Report");
		reportButton.setPreferredSize(new Dimension(150, 30));
		reportButton.addActionListener(this);

		gbc.gridx = 2;
		gbc.gridy = 2;
		gbc.insets = new Insets(10, -20, 20, 0); // Add 10px right margin
		topPanel.add(reportButton, gbc);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Client client = new Client();
		if (e.getSource() == reportButton) {

			client.sendAction("Get Invoice");

			List<Trip> trips = client.getTrips();

			if (trips != null) {

				setData(trips);
			}
		}
	}

	private void initializeTable(GridBagConstraints gbc) {

		
		model = new DefaultTableModel();
		invoiceTable = new JTable(model);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH; 
		gbc.weightx = 1.0;  
    	gbc.weighty = 1.0;
		gbc.insets = new Insets(0, 10, 0, 0); 

		String[] columnNames = { "Invoice Number", "Customer Id", "Source Address", "Destination Address", "rate",
				"Driver Id", "Billed By" };

		model.setColumnIdentifiers(columnNames);
		scrollPane = new JScrollPane(invoiceTable);
		bottomPanel.add(scrollPane, gbc);
	}

	


	public void setData(List<Trip> trips) {

		for (Trip trip : trips) {
			int invoiceNumber = trip.getInvoiceNumber();
			Long customerId = trip.getCustomerId().getCustomerId();

			String sourceAddress = trip.getSourceAddress().getAddress1() + " " + trip.getSourceAddress().getAddress2();
			String destinationAddress = trip.getDestinationAddress().getAddress1() + " "
					+ trip.getDestinationAddress().getAddress2();

			Double rate = trip.getRate().getValue();
			Long driverId = trip.getDriverId();
			String billedBy = trip.getBilledBy().getFirstName() + " " + trip.getBilledBy().getLastName();

			Object[] row = { invoiceNumber, customerId, sourceAddress, destinationAddress, rate, driverId, billedBy };
			model.addRow(row);

		}
	}

}

