package constants;

import javax.swing.*;
import java.awt.*;

public class PasswordPanel extends JPanel {
    private JPasswordField passwordField;
    private GridBagConstraints constraints;

    public PasswordPanel(String strLabel, int left) {
        // Set layout
        setLayout(new GridBagLayout());
        // Create labels
        JLabel label = new JLabel(strLabel);
        label.setFont(new Font(null, Font.ITALIC, 15));

        constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridy = 0;

        constraints.insets = new Insets(0, 0, 0, 0);
        add(label, constraints);

        // Create password fields
        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(480, 30));

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.insets = new Insets(0, left, 0, 0);

        // Add components to panel
        add(passwordField, constraints);

    }

    // Getter methods for accessing password fields
    public String getPasswordField() {
        return new String(passwordField.getPassword()); // Convert char array to String
    }
}
