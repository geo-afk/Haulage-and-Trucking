package com.gui.forgetpassword;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import javax.swing.ImageIcon;

import com.client.Client;
import com.constants.ConstantFunctions;
import com.constants.EncryptPassword;
import com.gui.login.LoginPage;

import model.Admin;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ForgotPassword extends JInternalFrame implements ActionListener {

	
	private final static Logger logger = LogManager.getLogger(ForgotPassword.class);
	
	private static final long serialVersionUID = 8509240026274310800L;
	
	
	private GridBagConstraints constraints;
	
	
	
	
	private JTextField searchUsername;
	private JButton searchButton;
	
	
	public JDesktopPane desktopPane;
	
	
	public static JFrame parentFrame;
	
	
	public ForgotPassword() {
		
		super("Forgot Password?");
		logger.info("Forget password frame is instantiated");
		setIcon();
		setLayout(new GridBagLayout());
		
		
		
		try {
			setMaximum(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		
		initializeSearchIdLabel();
		initializeSearchIdField();
		initializeSearchButton();
		
		
		
	}
	
	
	
	public void initializeForNewAccount(JDesktopPane desktopPane) {
		this.desktopPane = desktopPane;
	}
	
	
	
	private void setIcon(){
		
		setFrameIcon(new ImageIcon(ConstantFunctions.frameIcon("forgot-password")));
    }
	
	
	
	private void initializeSearchIdLabel(){
		
		JLabel searchLabel = new JLabel("Enter Staff username To Search");
		searchLabel.setFocusable(false);
		searchLabel.setFont(new Font(null, Font.BOLD, 15));
		
		
		constraints = new GridBagConstraints();
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.NORTH;
		constraints.insets = new Insets(0,0,90,0);
		
		add(searchLabel, constraints);
		
	}
	
	private void initializeSearchIdField() 
	{
		searchUsername = new JTextField(40);
		
		constraints = new GridBagConstraints();
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.ipady = 4;
		constraints.anchor = GridBagConstraints.SOUTH;
		constraints.insets = new Insets(0,0,50,0);
		
		
		
		add(searchUsername, constraints);
	}
	
	private void initializeSearchButton() 
	{
		searchButton = new JButton("Search");
		searchButton.setPreferredSize(new Dimension(100, 30));
		searchButton.addActionListener(this);

		constraints = new GridBagConstraints();

		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.anchor = GridBagConstraints.SOUTH;
		constraints.insets = new Insets(50, 0, 0, 0);

		add(searchButton, constraints);

	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		

		Client client = new Client();

		if (e.getSource() == searchButton && (!searchUsername.getText().isEmpty())) {

	
			boolean staffIdInSystem = client.staffExists(searchUsername.getText());

			if (staffIdInSystem) {

				logger.info("Found Admin account in database");
				String newPassword = " ";


				boolean validInputs = (newPassword.isEmpty() && newPassword.length() > 4);
				while (validInputs) {

					newPassword = JOptionPane.showInputDialog(this, "Enter New Password", "Account Found",
							JOptionPane.PLAIN_MESSAGE);

					if (newPassword.length() > 4) {

						logger.info("Sending request to reset Password.");
						client.sendAction("Reset Password");
						Admin admin = new Admin();
						admin.setUsername(searchUsername.getText());
						admin.setPassword(EncryptPassword.encrypt(newPassword));
						client.sendObject(admin);
						
						break;
					} else {
						JOptionPane.showMessageDialog(this, "Please enter a valid Password", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}

				dispose();
				LoginPage loginPage = new LoginPage();
				loginPage.setVisible(true);
				desktopPane.add(loginPage);

			}

		}
		
		client.closeConnection();
		
		
	}

}
