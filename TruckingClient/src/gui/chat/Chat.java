package gui.chat;

import java.awt.BorderLayout;
import java.beans.PropertyVetoException;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import constants.ConstantFunctions;

public class Chat extends JInternalFrame {

    private static Logger logger = LogManager.getLogger(Chat.class);

    public Chat() {

        super("Trucking Chat");
        logger.info("Instantiating main Menu Chat");

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
        ChatMainPanel chatPanel = new ChatMainPanel();

        add(chatPanel, BorderLayout.CENTER);
        setVisible(true);

    }

}
