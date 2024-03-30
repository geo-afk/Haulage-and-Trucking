package com.gui.report;

import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JInternalFrame;
import javax.swing.ScrollPaneConstants;
import com.constants.ConstantFunctions;
import java.beans.PropertyVetoException;

public class CustomerReportFrame extends JInternalFrame {

    public CustomerReportFrame() {

        super("View Customer Invoice");
		
	
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
        GenerateCustomerReport customerPanel = new GenerateCustomerReport();
		JScrollPane pane = new JScrollPane(customerPanel);
		   
	    pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		pane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	       
		add(pane);
	       
	    setVisible(true);
	       
	}

}
