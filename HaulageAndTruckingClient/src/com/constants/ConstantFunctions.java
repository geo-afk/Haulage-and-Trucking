package com.constants;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ConstantFunctions {

    public static final String PHOTO_STRING = "HaulageAndTruckingClient/resources/images/";

    private ConstantFunctions() {
        
    }
	
	
    
    public static ImageIcon resizeImage(final String path) {
    	
    	BufferedImage originalImage = null;
    	
        
        try {
            originalImage = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

    	
    	 // Define the new width and height for the resized image
        int newWidth = 20; // Adjust the width as per your requirement
        int newHeight = 20; // Adjust the height as per your requirement

        // Create a new BufferedImage with the new dimensions
        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);

        // Resize the original image to the new dimensions
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, newWidth - 2, newHeight - 2, null);
        g.dispose();
        
        return  new ImageIcon(resizedImage);
    	
    }
    
    
    public static JLabel addLogoToPanel() {
        

    	// Path to your image file
        String path = ConstantFunctions.PHOTO_STRING + "Haulage.png";
        

        // Load the image from the file
        ImageIcon originalIcon = new ImageIcon(path);

        // Get the original image from the ImageIcon
        Image originalImage = originalIcon.getImage();

        // Define the new width and height for the resized image
        int newWidth = 88;
        int newHeight = 85;

        // Resize the original image
        Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        // Create a new ImageIcon with the resized image
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        // Create a JLabel with the resized image
        JLabel logoImg = new JLabel(resizedIcon);
        logoImg.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
       
      return logoImg;  
    	
    }


    public static Image frameIcon(String name){
        ImageIcon icon = new ImageIcon(ConstantFunctions.PHOTO_STRING +name+".png");
        Image image = icon.getImage();
        
        return image.getScaledInstance(24, 24, Image.SCALE_SMOOTH);
     
    }
    
    
    public static Dimension initializeSize(){
    	
    	Toolkit toolkit = Toolkit.getDefaultToolkit();
        int width = (int) (toolkit.getScreenSize().getWidth() * 0.9);
        int height = (int) (toolkit.getScreenSize().getHeight()  * 0.9);

        return new Dimension(width, height);
        
   }

}
