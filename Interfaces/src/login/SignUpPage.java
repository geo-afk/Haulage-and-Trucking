package login;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.beans.PropertyVetoException;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class SignUpPage extends JInternalFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4914055393864945464L;

		
	
	
	public SignUpPage() {
		
		super("Sign-Up For An Account");
		
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
	        ImageIcon icon = new ImageIcon("resources/images/add.png");
	        Image image = icon.getImage();
	        Image resizedImage = image.getScaledInstance(24, 24, Image.SCALE_SMOOTH);
	        setFrameIcon(new ImageIcon(resizedImage));

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
