package com.gui.dashboard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Window;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import com.constants.ConstantFunctions;

public class DashboardMain extends JFrame{

	
	int width;
    int height;

    private JDesktopPane desktopPane;
    private SideBar sideBar;
    private MainTab tabScreen;
    
    
    public DashboardMain(JInternalFrame frame) {

        super("JAVA Haulage and Trucking");
        
        

        setIcon();
        setSideBar();
        initializeTab();
    }
    
    private void setIcon() {
        initializeSize();
        ImageIcon icon = new ImageIcon(" HaulageAndTruckingClient/resources/images/Haulage.png");
        setIconImage(icon.getImage());

    }
    
    public void disposeOfLogin(JInternalFrame frame) {

        JInternalFrame frame2 = frame;
        frame2.remove(frame);

        Window loginWindow = SwingUtilities.getWindowAncestor(frame);
        if (loginWindow != null) {
            loginWindow.dispose();
        }
    }
    
    private void initializeSize(){
    	
    

        Dimension d = ConstantFunctions.initializeSize();

        this.width = (int)d.getWidth();
        this.height = (int)d.getHeight();

        setSize(this.width,this.height);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
         
        setLayout(new BorderLayout());
         
        setLocationRelativeTo(null);
        setResizable(false);
    }
    
    
    private void setSideBar() {

    	desktopPane = new JDesktopPane();
        setDesktopPane();
        

    	
    	
    	sideBar = new SideBar(this.width, this.height);
    	sideBar.setVisible(true);
    	add(sideBar, BorderLayout.WEST);
    	
    	
    	
    }
    
    private void setDesktopPane() {

        desktopPane.setVisible(true);
        desktopPane.setBackground(new Color(120, 34, 45));
        add(desktopPane, BorderLayout.CENTER);

    }
    
    private void initializeTab() {
        tabScreen = new MainTab();

        add(tabScreen);
        sideBar.setDesktopAndTabbedPane(desktopPane, tabScreen);
    }


    // public static void main(String[] args) {

    //     FlatDarkLaf.setup();
    //     new Main().setVisible(true);
    // }
    
}
