package com.gui.customer;

import java.awt.BorderLayout;
import java.beans.PropertyVetoException;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.constants.ConstantFunctions;

public class CreateCustomer extends JInternalFrame {
	
	private static Logger logger = LogManager.getLogger(CreateCustomer.class);

    public CreateCustomer(){

        		
		super("Add Customer Details");
		logger.info("Instantiating Generate Payslip Internal Frame");
		
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
        CustomerMainPanel customerPanel = new CustomerMainPanel();
		JScrollPane pane = new JScrollPane(customerPanel);
		   
	    pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	       
	       
	    add(pane, BorderLayout.CENTER);
	    setVisible(true);
	       
	}

}
