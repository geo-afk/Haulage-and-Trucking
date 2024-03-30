package com.gui.trip;

import java.awt.BorderLayout;
import java.beans.PropertyVetoException;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;


public class BookTrip extends JInternalFrame{
	
	
	public BookTrip() {
		
		super("Book A Trip");
		
		setLayout(new BorderLayout());

    	
    	
    	try {
			setMaximum(true);
		} catch (PropertyVetoException e) {
			
			e.printStackTrace();
		}
    	
    	initializeScrollPane();
    
    }
	
	
	   private void initializeScrollPane() {
		   TripMainPanel trip = new TripMainPanel();
		   JScrollPane pane = new JScrollPane(trip);
		   
	       pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	       pane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	       
	       
	       add(pane, BorderLayout.CENTER);
	       setVisible(true);
	       
	   }
	   
	   
	   
	   
}
