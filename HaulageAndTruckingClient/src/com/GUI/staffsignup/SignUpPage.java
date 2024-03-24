package com.gui.staffsignup;

import java.awt.BorderLayout;
import java.beans.PropertyVetoException;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;

import com.constants.ConstantFunctions;

public class SignUpPage extends JInternalFrame{
	
	
	public SignUpPage() {
		
		super("Add Staff Details");
		
		setLayout(new BorderLayout());
    	setIcon();
    	
    	
    	try {
			setMaximum(true);
		} catch (PropertyVetoException e) {
			
			e.printStackTrace();
		}
    	
    	initializeScrollPane();
    
    }
	
	
	   private void setIcon(){
	        setFrameIcon(new ImageIcon(ConstantFunctions.frameIcon("add")));

	    }
	   
	   
	   private void initializeScrollPane() {
		   SignUpMainPanel signup = new SignUpMainPanel();
		   JScrollPane pane = new JScrollPane(signup);
		   
	       pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	       pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	       
	       
	       add(pane, BorderLayout.CENTER);
	       setVisible(true);
	       
	   }
	   
	   
	   
	   
}
