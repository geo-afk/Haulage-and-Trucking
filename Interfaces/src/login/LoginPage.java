package login;

import javax.swing.*;

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
	
	
	

    LoginPage(){
    	super("Login to your Account");
    	setIcon();
    	setLayout(null);
    	
    	try {
			setMaximum(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
    	
    	initializeStaffIdLabel();
    	initializeStaffIdTxtField();
    	
    	initializePasswordLabel();
    	initializeStaffPasswordField();
    	
    	initializeLoginButton();
    	initializeForgetPasswordButton();

    }
    
    private void setIcon(){
        ImageIcon icon = new ImageIcon("resources/images/login.png");
        Image image = icon.getImage();
        Image resizedImage = image.getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        setFrameIcon(new ImageIcon(resizedImage));

    }
    
    
    
    private void initializeStaffIdLabel() {
    	
    	JLabel staffIdLabel;
    	staffIdLabel= new JLabel("Staff ID");
    	staffIdLabel.setFont(new Font(null,Font.BOLD, 25));
    	staffIdLabel.setBounds(100, 90, 90, 40);

    	
    	add(staffIdLabel);
    }
    
    
    private void initializeStaffIdTxtField() {
    	JTextField staffId;
    	
    	staffId = new JTextField();
    	staffId.setBounds(100, 140, 550, 35);
    	
    	add(staffId);
    }
    
    
    
    private void initializePasswordLabel() {
    	JLabel passwordLabel;
  	  
    	passwordLabel = new JLabel("Password");
    	passwordLabel.setFont(new Font(null,Font.BOLD, 25));
    	passwordLabel.setBounds(100, 250, 150, 40);
      	
      	add(passwordLabel);
      }
    
    
    private void initializeStaffPasswordField() {
    	JPasswordField staffPassword;
    	
    	staffPassword = new JPasswordField();
    	staffPassword.setBounds(100, 300, 550, 35);
    	
    	add(staffPassword);
    }
    
    
    private void initializeLoginButton() {
    	login = new JButton("Login");
    	login.addActionListener(this);
    	login.setBounds(350, 400, 80, 35);
    	add(login);
    	
    	
    }
    
    
    private void initializeForgetPasswordButton() {
    	forgotPassword = new JButton("Forget Password");
    	forgotPassword.addActionListener(this);
    	forgotPassword.setBounds(600, 500, 130, 35);
    	add(forgotPassword);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()== login) {
			
			System.out.println("Login");
		}
		if(e.getSource() == forgotPassword) {
			
			System.out.println("forget Password");
		}
		
	}

}