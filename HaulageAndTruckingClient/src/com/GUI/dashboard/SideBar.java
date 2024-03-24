package com.gui.dashboard;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.constants.CustomButton;
import com.gui.createcustomer.CreateCustomer;
import com.gui.report.CustomerReportFrame;
import com.gui.staffsignup.SignUpPage;
import com.constants.ConstantFunctions;


@SuppressWarnings("unused")
public class SideBar extends JPanel implements ActionListener{
	
	
	
	private GridBagConstraints gbc;
	private CustomButton addStaff;
	private CustomButton addCustomer;
	private CustomButton addRoute;
	private CustomButton addRate;
	private CustomButton makeDeliveryRequest;
	private CustomButton makeHaulageRequest;
	private CustomButton checkCustomerBalance;
	private CustomButton generateCustomerReport;
	private JDesktopPane desktopPane;
	private JTabbedPane tabbedPane;



	private int addStaffClick = 0;
	private int addCustomerClick = 0;
	private int addCustomerReportClick = 0;
	
	public SideBar(int width, int height){
		
		setBackground(new Color(0x8B023332, true));
        setPreferredSize(new Dimension(width / 5 , height));
        setBorder(BorderFactory.createEmptyBorder());
        setLayout(new GridBagLayout());
        
       
        addLogoToPanel();
        initializeAddStaffBtn();
        initializeAddCustomerBtn();
        initializeAddRouteBtn();
        initializeAddRateBtn();
        initializeMakeDeliveryRequestBtn();
        initializeMakeHaulageRequestBtn();
        initializeCheckCustomerBalanceBtn();
		initializeGenerateCustomerReportBtn();
        
		
	}
	
	public void addLogoToPanel() {
		
		JLabel logoImg = ConstantFunctions.addLogoToPanel();
        
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0,0,0,0);
        add(logoImg, gbc);
		
	}
	 

    
    
    private void initializeAddStaffBtn() {
    	
    	
    	String path = ConstantFunctions.PHOTO_STRING + "add.png";
    	addStaff = new CustomButton("Add Staff", ConstantFunctions.resizeImage(path));
    	addStaff.setPreferredSize(new Dimension(150, 30));
    	addStaff.addActionListener(this);
    	
    
    	
    	gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(100,0,0,0);
        add(addStaff, gbc);
    }
    
	 private void initializeAddCustomerBtn() {
	    	
	    	
	    	String path = ConstantFunctions.PHOTO_STRING + "queue.png";
	    	addCustomer = new CustomButton("Add Customer", ConstantFunctions.resizeImage(path));
	    	addCustomer.addActionListener(this);
	    	addCustomer.setPreferredSize(new Dimension(150, 30));
	    	
	    
	    	
	    	gbc = new GridBagConstraints();
	        gbc.gridx = 0;
	        gbc.gridy = 2;
	        gbc.insets = new Insets(20,0,0,0);
	        add(addCustomer, gbc);
	 }
	 
	 private void initializeAddRouteBtn() {
		 
		 
		 String path = ConstantFunctions.PHOTO_STRING + "circle.png";
		 addRoute = new CustomButton("Add Route", ConstantFunctions.resizeImage(path));
		 addRoute.addActionListener(this);
		 addRoute.setPreferredSize(new Dimension(150, 30));
		 
		 
		 
		 gbc = new GridBagConstraints();
		 gbc.gridx = 0;
		 gbc.gridy = 3;
		 gbc.insets = new Insets(20,0,0,0);
		 add(addRoute, gbc);
	 }
	 
	 private void initializeAddRateBtn() {
		 
		 
		 String path = ConstantFunctions.PHOTO_STRING + "rate.png";
		 addRate = new CustomButton("Add Rate", ConstantFunctions.resizeImage(path));
		 addRate.addActionListener(this); 
		 addRate.setPreferredSize(new Dimension(150, 30));
		 
		 
		 
		 gbc = new GridBagConstraints();
		 gbc.gridx = 0;
		 gbc.gridy = 4;
		 gbc.insets = new Insets(20,0,0,0);
		 add(addRate, gbc);
	 }
	 
	 private void initializeMakeDeliveryRequestBtn() {
		 
		 
		 String path = ConstantFunctions.PHOTO_STRING + "delivery.png";
		 makeDeliveryRequest = new CustomButton("Request Delivery", ConstantFunctions.resizeImage(path));
		 makeDeliveryRequest.addActionListener(this); 
		 makeDeliveryRequest.setPreferredSize(new Dimension(150, 30));
		 
		 
		 gbc = new GridBagConstraints();
		 gbc.gridx = 0;
		 gbc.gridy = 5;
		 gbc.insets = new Insets(20,0,0,0);
		 add(makeDeliveryRequest, gbc);
	 }
	 
	 
	 private void initializeMakeHaulageRequestBtn() {
		 
		 
		 String path = ConstantFunctions.PHOTO_STRING + "truck.png";
		 makeHaulageRequest = new CustomButton("Request Haulage", ConstantFunctions.resizeImage(path));
		 makeHaulageRequest.addActionListener(this);
		 makeHaulageRequest.setPreferredSize(new Dimension(150, 30));
		 
		 gbc = new GridBagConstraints();
		 gbc.gridx = 0;
		 gbc.gridy = 6;
		 gbc.insets = new Insets(20,0,0,0);
		 add(makeHaulageRequest, gbc);
	 }
	 
		private void initializeCheckCustomerBalanceBtn() {

			String path = ConstantFunctions.PHOTO_STRING + "truck.png";
			checkCustomerBalance = new CustomButton("Customer Balance", ConstantFunctions.resizeImage(path));
			checkCustomerBalance.addActionListener(this);
			checkCustomerBalance.setPreferredSize(new Dimension(150, 30));

			gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 7;
			gbc.insets = new Insets(20, 0, 0, 0);
			add(checkCustomerBalance, gbc);
		}
    
		private void initializeGenerateCustomerReportBtn() {

			String path = ConstantFunctions.PHOTO_STRING + "report.png";
			generateCustomerReport = new CustomButton("Customer Report", ConstantFunctions.resizeImage(path));
			generateCustomerReport.addActionListener(this);
			generateCustomerReport.setPreferredSize(new Dimension(150, 30));

			gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 8;
			gbc.insets = new Insets(20, 0, 0, 0);
			add(generateCustomerReport, gbc);
		}
    
		
		public void setDesktopAndTabbedPane(JDesktopPane desktopPane, JTabbedPane tabbedPane) {
			this.desktopPane = desktopPane;
			this.tabbedPane = tabbedPane;
		}
		

		private void addTab(JInternalFrame internalFrame) {

			desktopPane.add(internalFrame);
			internalFrame.setVisible(true);

			tabbedPane.addTab(internalFrame.getTitle(), internalFrame.getContentPane());
			tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);

			String title = internalFrame.getTitle();
			tabbedPane.addTab(title, internalFrame.getContentPane());
		}

	

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == addStaff) {
			addStaffClick++;
			addMoreTab(addStaffClick, new SignUpPage());

		}
		if (e.getSource() == addCustomer) {
			addCustomerClick++;
			addMoreTab(addCustomerClick, new CreateCustomer());

		}

		if (e.getSource() == generateCustomerReport) {
			addCustomerReportClick++;
			addMoreTab(addCustomerClick, new CustomerReportFrame());

		}

	}
	
    
	private void addMoreTab(int tabNumber, JInternalFrame internalPane) {

		if (tabNumber < 2) {
			addTab(internalPane);
		}
		else {
			int shouldAdd = JOptionPane.showConfirmDialog(this, "Would you like to add another tab?", "Add Tab",
					JOptionPane.YES_NO_OPTION);
			
			if (shouldAdd == JOptionPane.YES_OPTION) {
				addTab(internalPane);
			}
		}
		
	}

}
