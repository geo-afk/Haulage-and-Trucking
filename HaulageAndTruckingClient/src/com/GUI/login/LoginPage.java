package com.gui.login;

import javax.swing.*;

import com.gui.dashboard.DashboardMain;
import com.gui.forgetpassword.*;
import com.client.Client;
import com.constants.ConstantFunctions;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

public class LoginPage extends JInternalFrame implements ActionListener{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6792377022952352986L;
	private JButton login;
	private JButton forgotPassword;
	private GridBagConstraints constraints;
	
	
	private JTextField staffId;
	private JPasswordField staffPassword;
	
	
	private JDesktopPane desktopPane;
	
	public static JFrame parentFrame;
	
	public ForgotPassword forgotPasswordFrame;

    public LoginPage(){
    	super("Login to your Account");
    	setIcon();
    	setLayout(new GridBagLayout());
    	
    	try {
			setMaximum(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
    	
    	forgotPasswordFrame = new ForgotPassword();
    	constraints = new GridBagConstraints();
    	
    	initializeStaffIdLabel(constraints);
    	initializeStaffIdTxtField(constraints);
    	
    	initializePasswordLabel(constraints);
    	initializeStaffPasswordField(constraints);
    	
    	initializeLoginButton(constraints);
    	initializeForgetPasswordButton(constraints);

    }
    
    
	private void setIcon(){
			
			setFrameIcon(new ImageIcon(ConstantFunctions.frameIcon("login")));
	 }
    
    
    
    private void initializeStaffIdLabel(GridBagConstraints constraints) {
    	
    	JLabel staffIdLabel;
    	staffIdLabel= new JLabel("Staff ID");
    	staffIdLabel.setFont(new Font(null,Font.ITALIC, 20));
    	
    	
    	constraints.anchor = GridBagConstraints.NORTHWEST;
    	constraints.gridx = 0;
    	constraints.gridy = 0;
    	constraints.insets = new Insets(0,0,0,0);
    	

    	
    	add(staffIdLabel, constraints);
    }
    
    
    private void initializeStaffIdTxtField(GridBagConstraints constraints) {
    	
    	staffId = new JTextField();
    	staffId.setPreferredSize(new Dimension(450, 30));
    	
    	constraints.anchor = GridBagConstraints.NORTHWEST;
    	constraints.gridx = 1;
    	constraints.gridy = 0;
    	constraints.insets = new Insets(0,0,40,0);
    	
    	add(staffId, constraints);
    }
    
    
    
    private void initializePasswordLabel(GridBagConstraints constraints) {
    	JLabel passwordLabel;
  	  
    	passwordLabel = new JLabel("Password");
    	passwordLabel.setFont(new Font(null,Font.ITALIC, 20));
    	passwordLabel.setPreferredSize(new Dimension(90, 30));


    	constraints.anchor = GridBagConstraints.NORTHWEST;
    	constraints.gridx = 0;
    	constraints.gridy = 1;
    	constraints.insets = new Insets(50,0,0,10);
      	
      	add(passwordLabel, constraints);
      }
    
    
    private void initializeStaffPasswordField(GridBagConstraints constraints) {
    	
    	staffPassword = new JPasswordField();
    	staffPassword.setPreferredSize(new Dimension(450, 30));


    	constraints.anchor = GridBagConstraints.NORTHWEST;
    	constraints.gridx = 1;
    	constraints.gridy = 1;
    	constraints.insets = new Insets(50,0,0,0);
    	
    	add(staffPassword, constraints);
    }
    
    
    private void initializeLoginButton(GridBagConstraints constraints) {
    	login = new JButton("Login");
    	login.addActionListener(this);
    	login.setPreferredSize(new Dimension(90,30));


    	constraints.anchor = GridBagConstraints.NORTHWEST;
    	constraints.gridx = 0;
    	constraints.gridy = 2;
    	constraints.insets = new Insets(150,0,0,0);
    	
    	add(login, constraints);
    	
    	
    }
    
    
    private void initializeForgetPasswordButton(GridBagConstraints constraints) {
    	forgotPassword = new JButton("Forget Password");
    	forgotPassword.addActionListener(this);
    	forgotPassword.setPreferredSize(new Dimension(130,30));


    	constraints.anchor = GridBagConstraints.NORTHEAST;
    	constraints.gridx = 1;
    	constraints.gridy = 2;
    	constraints.insets = new Insets(150,0,0,00);
    	
    	add(forgotPassword, constraints);
    }
    
	    
	public void getDesktopAndSignupPanel(JDesktopPane desktopPane) {

		this.desktopPane = desktopPane;

	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Client client = new Client();
		
		if (e.getSource() == login) {

			if ((!staffId.getText().isEmpty()) && staffPassword.getPassword().length > 3) {

				Long id = Long.parseLong(staffId.getText().trim());
				String password = String.copyValueOf(staffPassword.getPassword());
				staffPassword.setText("");

				
				client.sendAction("Staff Login");

				boolean found = client.validate(id, password);

				if (found) {
					

					DashboardMain dashboard = new DashboardMain(this);
					dashboard.setVisible(true);
					dashboard.disposeOfLogin(this);
					

				} else {

					JOptionPane.showMessageDialog(this, "Login Failed", "Login Failed", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(this, "Field/s are blank must contain data", "Blank Field/s",
						JOptionPane.ERROR_MESSAGE);
			}
			client.closeConnection();
		}

		
		if(e.getSource() == forgotPassword) {
			
			try {
	
				int choice = JOptionPane.showConfirmDialog(this, "Continue?");
				
				
				if(choice == 0) 
				{
					this.setVisible(false);
					forgotPasswordFrame.setVisible(true);
					desktopPane.add(forgotPasswordFrame);
					
				}else if(choice == 1) {
					dispose(); 
					parentFrame.dispose();
			          
				}
				
				
			}catch(HeadlessException e1) {
				e1.printStackTrace();
			}
		}
		
	}

}