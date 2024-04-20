package gui.dashboard;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JTabbedPane;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import constants.CustomButton;
import java.awt.event.ActionEvent;
import javax.swing.JInternalFrame;
import gui.trip.TripMainPanel;
import constants.InternalPanel;
import java.awt.GridBagConstraints;
import gui.route.RouteMainPanel;
import gui.staff.StaffMainPanel;
import java.awt.event.ActionListener;
import org.apache.logging.log4j.Logger;
import constants.ConstantFunctions;
import gui.payslip.PaySlipMainPanel;
import gui.balance.CustomerBalanceMainPanel;
import gui.customer.CustomerMainPanel;
import org.apache.logging.log4j.LogManager;
import gui.report.GenerateCustomerReport;

public class SideBar extends JPanel implements ActionListener {

	private static final Logger logger = LogManager.getLogger(SideBar.class);

	private GridBagConstraints gbc;

	private CustomButton addStaff;
	private CustomButton addCustomer;
	private CustomButton addRoute;
	private CustomButton bookTrip;
	private CustomButton checkCustomerBalance;
	private CustomButton generateCustomerReport;
	private CustomButton generatePaySlipButton;
	private JDesktopPane desktopPane;
	private JTabbedPane tabbedPane;

	private int addStaffClick = 0;
	private int addCustomerClick = 0;
	private int addCustomerReportClick = 0;
	private int addRouteClick = 0;
	private int bookTripClick = 0;
	private int generatePaySlipClick = 0;
	private int checkCustomerBalanceClick = 0;

	public SideBar(int width, int height) {

		setBackground(new Color(0x8B023332, true));
		setPreferredSize(new Dimension(width / 5, height));
		setBorder(BorderFactory.createEmptyBorder());
		setLayout(new GridBagLayout());

		addLogoToPanel();
		initializeAddStaffBtn();
		initializeAddCustomerBtn();
		initializeAddRouteBtn();
		initializeMakeHaulageRequestBtn();
		initializeCheckCustomerBalanceBtn();
		initializeGenerateCustomerReportBtn();
		initializeGeneratePaySlipBtn();

	}

	public void addLogoToPanel() {

		JLabel logoImg = ConstantFunctions.addLogoToPanel();

		gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 0, 0, 0);
		add(logoImg, gbc);

	}

	private void initializeAddStaffBtn() {

		String path = ConstantFunctions.PHOTO_STRING + "add.png";
		addStaff = new CustomButton("Add Staff", ConstantFunctions.resizeImage(path));
		addStaff.setPreferredSize(new Dimension(170, 30));
		addStaff.addActionListener(this);

		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets = new Insets(100, 0, 0, 0);
		add(addStaff, gbc);
	}

	private void initializeAddCustomerBtn() {

		String path = ConstantFunctions.PHOTO_STRING + "queue.png";
		addCustomer = new CustomButton("Add Customer", ConstantFunctions.resizeImage(path));
		addCustomer.addActionListener(this);
		addCustomer.setPreferredSize(new Dimension(170, 30));

		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.insets = new Insets(20, 0, 0, 0);
		add(addCustomer, gbc);
	}

	private void initializeAddRouteBtn() {

		String path = ConstantFunctions.PHOTO_STRING + "circle.png";
		addRoute = new CustomButton("Add Route", ConstantFunctions.resizeImage(path));
		addRoute.addActionListener(this);
		addRoute.setPreferredSize(new Dimension(170, 30));

		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.insets = new Insets(20, 0, 0, 0);
		add(addRoute, gbc);
	}

	private void initializeMakeHaulageRequestBtn() {

		String path = ConstantFunctions.PHOTO_STRING + "truck.png";
		bookTrip = new CustomButton("Book Trip", ConstantFunctions.resizeImage(path));
		bookTrip.addActionListener(this);
		bookTrip.setPreferredSize(new Dimension(170, 30));

		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.insets = new Insets(20, 0, 0, 0);
		add(bookTrip, gbc);
	}

	private void initializeCheckCustomerBalanceBtn() {

		String path = ConstantFunctions.PHOTO_STRING + "truck.png";
		checkCustomerBalance = new CustomButton("Customer Balance", ConstantFunctions.resizeImage(path));
		checkCustomerBalance.addActionListener(this);
		checkCustomerBalance.setPreferredSize(new Dimension(170, 30));

		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.insets = new Insets(20, 0, 0, 0);
		add(checkCustomerBalance, gbc);
	}

	private void initializeGenerateCustomerReportBtn() {

		String path = ConstantFunctions.PHOTO_STRING + "report.png";
		generateCustomerReport = new CustomButton("Customer Report", ConstantFunctions.resizeImage(path));
		generateCustomerReport.addActionListener(this);
		generateCustomerReport.setPreferredSize(new Dimension(170, 30));

		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.insets = new Insets(20, 0, 0, 0);
		add(generateCustomerReport, gbc);
	}

	private void initializeGeneratePaySlipBtn() {

		String path = ConstantFunctions.PHOTO_STRING + "payslip.png";
		generatePaySlipButton = new CustomButton("Generate PaySlip", ConstantFunctions.resizeImage(path));
		generatePaySlipButton.addActionListener(this);
		generatePaySlipButton.setPreferredSize(new Dimension(170, 30));

		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 8;
		gbc.insets = new Insets(20, 0, 0, 0);
		add(generatePaySlipButton, gbc);
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
			logger.info("Add Staff Frame is opened: {}", addStaffClick);
			addMoreTab(addStaffClick, new InternalPanel("Add Staff Record", new StaffMainPanel()));

		}
		if (e.getSource() == addCustomer) {
			addCustomerClick++;
			logger.info("Add Customer Frame is opened: {}", addCustomerClick);
			addMoreTab(addCustomerClick, new InternalPanel("Add Customer Record", new CustomerMainPanel()));

		}

		if (e.getSource() == generateCustomerReport) {
			addCustomerReportClick++;
			logger.info("View Customer Report Frame is opened: {}", addCustomerReportClick);
			addMoreTab(addCustomerReportClick,
					new InternalPanel("View Customer Invoice", new GenerateCustomerReport()));

		}
		if (e.getSource() == addRoute) {
			addRouteClick++;
			logger.info("Add Route Frame is opened: {}", addRouteClick);
			addMoreTab(addRouteClick, new InternalPanel("Add Route to System", new RouteMainPanel()));

		}
		if (e.getSource() == bookTrip) {
			bookTripClick++;
			logger.info("Add Book Trip Frame is opened: {}", bookTripClick);
			addMoreTab(bookTripClick, new InternalPanel("Book A Trip", new TripMainPanel()));

		}
		if (e.getSource() == generatePaySlipButton) {
			generatePaySlipClick++;
			logger.info("Add generate payslip Frame is opened: {}", generatePaySlipClick);
			addMoreTab(generatePaySlipClick, new InternalPanel("Generate Staff PaySlip", new PaySlipMainPanel()));

		}
		if (e.getSource() == checkCustomerBalance) {
			checkCustomerBalanceClick++;
			logger.info("Add Check Customer balance Frame is opened: {}", checkCustomerBalanceClick);
			addMoreTab(checkCustomerBalanceClick,
					new InternalPanel("Check Customer Balance", new CustomerBalanceMainPanel()));

		}

	}

	private void addMoreTab(int tabNumber, JInternalFrame internalPane) {

		if (tabNumber < 2) {
			addTab(internalPane);
		} else {
			int shouldAdd = JOptionPane.showConfirmDialog(this, "Would you like to add another tab?", "Add Tab",
					JOptionPane.YES_NO_OPTION);

			if (shouldAdd == JOptionPane.YES_OPTION) {
				addTab(internalPane);
			}
		}

	}

}
