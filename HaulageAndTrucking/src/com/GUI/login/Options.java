package com.GUI.login;


import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import login.customButton.CustomButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Options extends JPanel implements ActionListener{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	 
	 
	
	 private GridBagConstraints constraints;
	 private SignUpPage signupPage;
	 private LoginPage loginPage;
	 private CustomButton loginButton;
	 private CustomButton signupButton;
	 private JDesktopPane pane;
	 private JLabel LogoImg;



    public Options(int width, int height){
    	
        Border titleBorder = BorderFactory.createTitledBorder("Log in/sign up for an account.");
        Border beveledBorder = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
        setBorder(BorderFactory.createCompoundBorder(titleBorder,beveledBorder));
        setBackground(new Color(0x8B023332, true));
        setPreferredSize(new Dimension(width / 5, height));
        
 
        constraints = new GridBagConstraints();
        signupPage = new SignUpPage();
		loginPage = new LoginPage();
		
		
        setLayout(new GridBagLayout());
        
        addLogoToPanel();
        loginButton();
        signUpButton();
        
    
        setVisible(true);
    }
    
    
    private ImageIcon resizeImage(String path) {
    	
    	BufferedImage originalImage = null;
    	
        
        try {
            originalImage = ImageIO.read(new File(path));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    	
    	 // Define the new width and height for the resized image
        int newWidth = 20; // Adjust the width as per your requirement
        int newHeight = 20; // Adjust the height as per your requirement

        // Create a new BufferedImage with the new dimensions
        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);

        // Resize the original image to the new dimensions
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, newWidth - 2, newHeight - 2, null);
        g.dispose();
        
        return  new ImageIcon(resizedImage);
    	
    }
    
    
    private void addLogoToPanel() {

    	// Path to your image file
        String path = "resources/images/Haulage.png";

        // Load the image from the file
        ImageIcon originalIcon = new ImageIcon(path);

        // Get the original image from the ImageIcon
        Image originalImage = originalIcon.getImage();

        // Define the new width and height for the resized image
        int newWidth = 98;
        int newHeight = 95;

        // Resize the original image
        Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        // Create a new ImageIcon with the resized image
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        // Create a JLabel with the resized image
        LogoImg = new JLabel(resizedIcon);
        
        constraints.anchor = GridBagConstraints.NORTH; // Anchor to the top
        constraints.insets = new Insets(0, 0, 250, 0); // Padding
    	constraints.gridx = 0;
    	constraints.gridy = 0;
        
       add(LogoImg, constraints);
    	
    }
    
    
    private void loginButton() {
    	
    	String path = "resources/images/login.png";
    	loginButton = new CustomButton("Login", resizeImage(path));
    	loginButton.setFocusPainted(false);
    	loginButton.addActionListener(this);
    	
    	
    	
    	constraints.anchor = GridBagConstraints.SOUTH; // Anchor to the top
        constraints.insets = new Insets(0, 0, 20, 0); // Padding
    	constraints.gridx = 0;
    	constraints.gridy = 1;
    	

        add(loginButton,constraints);
    }
    
    
    private void signUpButton() {
    
    	
    	String path = "resources/images/add.png";
    	signupButton = new CustomButton("Signup", resizeImage(path));
    	signupButton.setFocusPainted(false);
    	signupButton.addActionListener(this);
    	
    	
    	constraints.anchor = GridBagConstraints.SOUTH; // Anchor to the top
        constraints.insets = new Insets(20, 0, 0, 0); // Padding
    	constraints.gridx = 0;
    	constraints.gridy = 2;

        add(signupButton,constraints);
    	
    }
    
    public void setDesktopPane(JDesktopPane pane) {
    	this.pane = pane;
    	
    }


	@Override
	public void actionPerformed(ActionEvent e) {


		if(e.getSource() == loginButton) {
			signupPage.setVisible(false);
			loginPage.setVisible(true);
			
			this.pane.add(loginPage);
		}
		if(e.getSource()== signupButton) {
			signupPage.setVisible(true);
			loginPage.setVisible(false);
			
			this.pane.add(signupPage);
			
		}
		
	}


}
