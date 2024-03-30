package com.gui.payslip;

import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JInternalFrame;
import javax.swing.ScrollPaneConstants;
import com.constants.ConstantFunctions;

import java.beans.PropertyVetoException;

public class GeneratePaySlip extends JInternalFrame{


      public GeneratePaySlip() {

        super("Generate Payslip");
		
	
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
        PaySlipMainPanel paySlipPanel = new PaySlipMainPanel();
		JScrollPane pane = new JScrollPane(paySlipPanel);
		   
	    pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		pane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	       
		add(pane);
	       
	    setVisible(true);
	       
	}

}





