package com.gui.route;

import java.awt.BorderLayout;
import java.beans.PropertyVetoException;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import com.constants.ConstantFunctions;

public class CreateRoute extends JInternalFrame{

    public CreateRoute() {

        super("Add Route");

        setLayout(new BorderLayout());
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
    RouteMainPanel route = new RouteMainPanel();
    JScrollPane pane = new JScrollPane(route);
    
    pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    pane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    
    
    add(pane, BorderLayout.CENTER);
    setVisible(true);
    
}
	   
	   

}
