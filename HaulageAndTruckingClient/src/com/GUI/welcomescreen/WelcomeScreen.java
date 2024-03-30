package com.gui.welcomescreen;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.beans.PropertyVetoException;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

import com.constants.ConstantFunctions;

public class WelcomeScreen extends JInternalFrame{

	
	private GridBagConstraints constraints;
	
	public WelcomeScreen() {
		
		
		super("WELCOME");
    	setIcon();
    	setLayout(new GridBagLayout());
    	
    	try {
			setMaximum(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
    	
    	constraints = new GridBagConstraints();
    	initializeWelcomeTo(constraints);
    	initializeCompanyName(constraints);
    	initializeCompanySlogan(constraints);
    	setVisible(true);
	}
	
	
	private void setIcon(){
        setFrameIcon(new ImageIcon(ConstantFunctions.frameIcon("welcome")));

    }
	
	
	private void initializeWelcomeTo(GridBagConstraints constraints) {
		
		JLabel welcomeTo = new JLabel("Welcome");
		welcomeTo.setFont(new Font(null, Font.BOLD, 60));
		welcomeTo.setForeground(new Color(143, 94, 51));
		
		
		constraints.anchor = GridBagConstraints.NORTHWEST;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(0,0,0,0);
		
		
		add(welcomeTo, constraints);
	}
	
	
	private void initializeCompanyName(GridBagConstraints constraints) {
		
		JLabel companyName = new JLabel("JAVA Haulage and Trucking");
		companyName.setFont(new Font(null, Font.BOLD, 20));
		companyName.setForeground(new Color(143, 94, 51));
		
	
		
		
		constraints.anchor = GridBagConstraints.NORTH;
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.insets = new Insets(5,0,120,0);
		
		
		add(companyName, constraints);
	}
	
	
	private void initializeCompanySlogan(GridBagConstraints constraints) {
		JLabel aSlogan;
		String slogan = "Journeying Together, Hauling with Heart, Connecting Communities!";
		aSlogan = new JLabel(slogan);
		aSlogan.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
		aSlogan.setForeground(new Color(143, 94, 51));
		
		
		
		constraints.anchor = GridBagConstraints.SOUTH;
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.insets = new Insets(50,35,0,0);
		
		
		add(aSlogan, constraints);
		
	}
	
}
