package gui.chat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import client.Client;

public class ChatMainPanel extends JPanel implements ActionListener {

    private static final Logger logger = LogManager.getLogger(ChatMainPanel.class);

    private JPanel panel;

    private JPanel topPanel;

    private JTextField textField;
    private JButton sendButton;
    static Box vertical = Box.createVerticalBox();

    private MessageHandler messageHandler;

    public ChatMainPanel() {

        setLayout(new BorderLayout());
        internalPanel();
        add(panel, BorderLayout.CENTER);
    }

    private void internalPanel() {

        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        initializeTopPanel();
        initializeBottomPanel();

        messageHandler = new MessageHandler();
        new Thread(messageHandler).start();

    }

    private void initializeTopPanel() {

        topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(7, 94, 84));
        ScrollPane sp = new ScrollPane();
        sp.add(topPanel);
        panel.add(sp, BorderLayout.CENTER);

    }

    private void initializeBottomPanel() {
        JPanel bottomPanel;

        bottomPanel = new JPanel(new BorderLayout());

        textField = new JTextField();
        bottomPanel.add(textField, BorderLayout.CENTER);

        sendButton = new JButton("Send");
        sendButton.setBackground(new Color(7, 94, 84));
        sendButton.addActionListener(this);
        bottomPanel.add(sendButton, BorderLayout.EAST);

        panel.add(bottomPanel, BorderLayout.SOUTH);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        String out = "<html><h1>Name</h1><h4>" + textField.getText()
                + "</h4><div style=\"text-align: right;\"> <small>" + sdf.format(cal.getTime())
                + " </small></div></html>";

        if (e.getSource() == sendButton && !textField.getText().isEmpty()) {

            logger.info("Sending message: {}", textField.getText());

            JPanel p2 = formatLabel(out);

            JPanel right = new JPanel(new BorderLayout());
            right.setBackground(new Color(7, 94, 84));
            right.add(p2, BorderLayout.LINE_END);
            vertical.add(right);

            vertical.add(Box.createVerticalStrut(15));
            topPanel.add(vertical, BorderLayout.PAGE_START);

            // Revalidate and repaint topPanel
            topPanel.revalidate();
            topPanel.repaint();

            messageHandler.sendObject(out);
            textField.setText("");

        }

    }

    public static JPanel formatLabel(String out) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Concatenate the HTML strings properly
        String htmlContent = "<html><p style=\"width: 150px\">" + out + "</p> </html>";

        // Create the JLabel with the HTML content
        JLabel output = new JLabel(htmlContent);
        output.setFont(new Font("Tahoma", Font.PLAIN, 16));
        output.setBackground(new Color(37, 211, 112));
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(0, 15, 0, 50));

        // Add the JLabel to the panel
        panel.add(output);

        return panel;
    }

    private class MessageHandler extends Client implements Runnable {

        MessageHandler() {
            super();
        }

        @Override
        public void run() {

            String message;
            try {
                while ((message = (String) inputStream.readObject()) != null) {

                    JPanel p2 = formatLabel(message);

                    JPanel left = new JPanel(new BorderLayout());
                    left.add(p2, BorderLayout.LINE_START);
                    left.setBackground(new Color(7, 94, 84));
                    vertical.add(left);

                    vertical.add(Box.createVerticalStrut(15));
                    topPanel.add(vertical, BorderLayout.PAGE_START);

                    // Revalidate and repaint topPanel
                    topPanel.revalidate();
                    topPanel.repaint();
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ChatMainPanel::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        // Create JFrame
        JFrame frame = new JFrame("Chat Application");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Create instance of ChatMainPanel
        ChatMainPanel chatPanel = new ChatMainPanel();

        // Add ChatMainPanel to the JFrame
        frame.getContentPane().add(chatPanel);

        // Set frame size and visibility
        frame.pack();
        frame.setSize(600, 400); // Set your desired size
        frame.setVisible(true);
    }

}
