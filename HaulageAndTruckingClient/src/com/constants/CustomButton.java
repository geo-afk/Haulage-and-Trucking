package com.constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomButton extends JButton {

	

    /**
	 * 
	 */
	private static final long serialVersionUID = -5591974162915114158L;
	private boolean over;
    private Color color;
    private Color colorOver;
    private Color colorClicked;
    private Color borderColor;



    public CustomButton(String title, ImageIcon icon){

        super(title,icon);
        
      
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        color= Color.GRAY;
        colorOver = new Color(255, 255, 255);
        colorClicked = new Color(117, 117, 117);
        borderColor = new Color(35, 35, 35, 123);
        setContentAreaFilled(false);

        //add mouseListener

        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                setBackground(colorClicked);

            }

            @Override
            public void mouseReleased(MouseEvent e) {

                if(over){

                    setBackground(colorOver);

                }else{

                    setBackground(color);
                }

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(colorOver);
                over = true;
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(color);
                over = false;

            }
        });
    }


    @Override
    protected void paintComponent(Graphics graphic){

        Graphics2D g2 = (Graphics2D) graphic;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


        //Paint Border
        g2.setColor(borderColor);
        g2.fillRoundRect(0,0,getWidth() + 120, getHeight(), 5, 5);
        g2.setColor(getBackground());
        g2.fillRoundRect(2,2,getWidth() - 4, getHeight() - 4, 5, 5);
        super.paintComponent(graphic);
    }
}
