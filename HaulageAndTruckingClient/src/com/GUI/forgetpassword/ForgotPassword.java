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
import com.gui.login.LoginPage;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class ForgotPassword extends JInternalFrame implements ActionListener{

	
	private static final long serialVersionUID = 8509240026274310800L;
	
	
	private GridBagConstraints constraints;
	
	
	
	private JLabel searchLabel;
	private JTextField searchId;
	private JButton searchButton;
	
	
	private LoginPage loginPage;
	
	public JDesktopPane desktopPane;
	
	
	public static JFrame parentFrame;
	
	
	public ForgotPassword() {
		
		super("Forgot Password?");
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
		
		searchLabel = new JLabel("Enter Your ID number To Search");
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
		searchId = new JTextField(40);
		
		constraints = new GridBagConstraints();
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.ipady = 4;
		constraints.anchor = GridBagConstraints.SOUTH;
		constraints.insets = new Insets(0,0,50,0);
		
		
		
		add(searchId, constraints);
	}
	
	private void initializeSearchButton() 
	{
		searchButton = new JButton("Search");
		searchButton.setPreferredSize(new Dimension(100,30));
		searchButton.addActionListener(this);
		
		
		constraints = new GridBagConstraints();
		
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.anchor = GridBagConstraints.SOUTH;
		constraints.insets = new Insets(50,0,0,0);
		
		
		add(searchButton, constraints);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		

		Client client = new Client();

		if (e.getSource() == searchButton) {

			if (!searchId.getText().isEmpty()) {
				Long id = Long.parseLong(searchId.getText());
				boolean staffIdInSystem = client.staffExists(id);

				if (staffIdInSystem) {

					String newPassword = "";

					while (newPassword.isEmpty() && newPassword.length() < 4) {
						
						newPassword = JOptionPane.showInputDialog(this, "Enter New Password", "Account Found", JOptionPane.PLAIN_MESSAGE);

						if (newPassword != null && !newPassword.isEmpty()) {
							client.sendAction("Reset Password");
							client.resetPassword(id, newPassword);
						} else {
							JOptionPane.showMessageDialog(this, "Please enter a valid Password", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}

					

					dispose();
					loginPage = new LoginPage();
					loginPage.setVisible(true);
					desktopPane.add(loginPage);

				}
			}

		}
		client.closeConnection();
		
		
	}

}
