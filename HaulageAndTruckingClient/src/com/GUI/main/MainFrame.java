package com.gui.main;



import com.constants.ConstantFunctions;
import com.formdev.flatlaf.FlatDarculaLaf;
import com.gui.forgetpassword.ForgotPassword;
import com.gui.login.LoginPage;
import javax.swing.*;
import java.awt.*;



public class MainFrame extends JFrame{


    /**
	 * 
	 */
	private static final long serialVersionUID = 6997650226727883582L;
	
	int width;
    int height;

    private JDesktopPane desktopPane;
    private Options options;




    public MainFrame(){
        super("JAVA Haulage and Trucking");
        setIcon();
        setOptionsPanel();
        initializeDesktopPane();
        
        LoginPage.parentFrame = this;
        ForgotPassword.parentFrame = this;

        setVisible(true);
    }


    private void setIcon(){
        initializeSizeAndFeel();
        ImageIcon icon = new ImageIcon(ConstantFunctions.PHOTO_STRING + "Haulage.png");
        setIconImage(icon.getImage());

    }


    private void setOptionsPanel() {
        options=new Options(this.width, this.height);
       
        add(options, BorderLayout.WEST);
       
    }


    private void initializeSizeAndFeel(){
        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        this.width = (int) (toolkit.getScreenSize().getWidth() * 0.6);
        this.height = (int) (toolkit.getScreenSize().getHeight()  * 0.7);


        setSize(this.width,this.height);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setResizable(false);

    }



    private void initializeDesktopPane(){
        desktopPane = new JDesktopPane();
        
        options.setDesktopPane(desktopPane);
        
        add(desktopPane);
    }

    
    
	public static void main(String[] args) {

		SwingUtilities.invokeLater(MainFrame::new);
    }
}
