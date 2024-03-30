package com.gui.report;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.client.Client;
import com.constants.DatePicker;
import model.TripReport;

public class GenerateCustomerReport extends JPanel implements ActionListener {

	private final static Logger logger = LogManager.getLogger(GenerateCustomerReport.class);

	
	private DatePicker startDateTextField;

	
	private DatePicker endDateTextField;

	
	private JTextField driverTextField;

	private JButton reportButton;

	
	private JPanel topPanel;
	private JPanel bottomPanel;

	
	

	private DefaultTableModel model;

	public GenerateCustomerReport() {
		


		setLayout(new BorderLayout());
    	

		internalPanel();


		setVisible(true);
	}



	
	private void internalPanel() {
		
		

		topPanel = new JPanel();
        topPanel.setLayout(new GridBagLayout());


		GridBagConstraints gbc = new GridBagConstraints();

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
		
		JLabel startDateLabel;
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
		
		JLabel endDateLabel;
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
		
		JLabel driverLabel;
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

			Date startDate = startDateTextField.convertToDate();
			Date endDate = endDateTextField.convertToDate();
			String driverName = driverTextField.getText();

			client.sendAction("Trip Report");
			client.sendObject(driverName);
			client.sendObject(new Timestamp(startDate.getTime()));
			client.sendObject(new Timestamp(endDate.getTime()));
			
			@SuppressWarnings("unchecked")
			List<TripReport> report = (List<TripReport>) client.getObject();
			setData(report);
			
		}
		client.closeConnection();
	}

	private void initializeTable(GridBagConstraints gbc) {
		logger.info("Initializing The table and setting the column names ");
		JTable invoiceTable;
		JScrollPane scrollPane;

		
		model = new DefaultTableModel();
		invoiceTable = new JTable(model);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH; 
		gbc.weightx = 1.0;  
    	gbc.weighty = 1.0;
		gbc.insets = new Insets(0, 10, 0, 0); 

		String[] columnNames = { "Invoice Number", "Company", "Source Address", "Destination Address", "rate",
				"Driver Name", "Billed By" };

		model.setColumnIdentifiers(columnNames);
		scrollPane = new JScrollPane(invoiceTable);
		bottomPanel.add(scrollPane, gbc);

	}

	


	public void setData(List<TripReport> report) {

		logger.info("Loading data into the table");

		for (TripReport report2 : report) {

			
			Long invoiceNumber = report2.getInvoiceNumber();
			String company = report2.getCompany();

			String sourceAddress = report2.getSourceAddress();
			String destinationAddress = report2.getDestinationAddress();

			Double rate = report2.getRate();

			String driverName = report2.getDriverName();
			String billedBy = report2.getBilledBy();


			Object[] row = { invoiceNumber, company, sourceAddress, destinationAddress, rate, driverName, billedBy };
			model.addRow(row);

		}
	}

}

