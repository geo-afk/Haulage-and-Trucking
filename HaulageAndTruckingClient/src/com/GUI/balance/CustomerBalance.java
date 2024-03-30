package com.gui.balance;


import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JInternalFrame;
import javax.swing.ScrollPaneConstants;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.constants.ConstantFunctions;

import java.beans.PropertyVetoException;


public class CustomerBalance extends JInternalFrame {

	private final static Logger logger = LogManager.getLogger(CustomerBalance.class);

      public CustomerBalance() {

		super("Customer Balance");
		logger.info("Instantiating Customer Balance Internal Frame");
		
	
    	setIcon();
    	
    	
    	try {
			setMaximum(true);
		} catch (PropertyVetoException e) {
			
			e.printStackTrace();
		}
    	
    	initializeScrollPane();
    }

    private void setIcon() {
        setFrameIcon(new ImageIcon(ConstantFunctions.frameIcon("add")));

    }
    

     private void initializeScrollPane() {
        CustomerBalanceMainPanel customerBalancePanel = new CustomerBalanceMainPanel();
		JScrollPane pane = new JScrollPane(customerBalancePanel);
		   
	    pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		pane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	       
		add(pane);
	       
	    setVisible(true);
	       
	}

}





