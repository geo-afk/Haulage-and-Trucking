package login;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.formdev.flatlaf.FlatDarculaLaf;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class MainFrame extends JFrame implements ActionListener {


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

        setVisible(true);
    }


    private void setIcon(){
        initializeSizeAndFeel();
        ImageIcon icon = new ImageIcon("resources/images/Haulage.png");
        setIconImage(icon.getImage());

    }


    private void setOptionsPanel() {
        options=new Options(width, height);
       
        add(options, BorderLayout.WEST);
       
    }


    private void initializeSizeAndFeel(){
        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        width = (int) (toolkit.getScreenSize().getWidth() * 0.7);
        height = (int) (toolkit.getScreenSize().getHeight()  * 0.8);


        setSize(width,height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setResizable(false);

    }



    private void initializeDesktopPane(){
        desktopPane = new JDesktopPane();
        
        options.setDesktopPane(desktopPane);
        
        add(desktopPane);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
    
    
	public static void main(String[] args) {

		SwingUtilities.invokeLater(() -> {
			new MainFrame();
		});
    }
}
