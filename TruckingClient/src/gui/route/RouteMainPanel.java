package gui.route;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import client.Client;
import constants.ConstantFunctions;

import model.Address;
import model.Route;

public class RouteMainPanel extends JPanel implements ActionListener {

    private JTextField source;

    private JTextField sourceAddress1;
    private JTextField sourceAddress2;
    private JTextField sourcePostOffice;
    private JTextField sourceParish;

    private JTextField destinationAddress1;
    private JTextField destinationAddress2;
    private JTextField destinationPostOffice;
    private JTextField destinationParish;

    private JTextField rateValue;

    private JButton addButton;

    public RouteMainPanel() {

        internalPanel();
    }

    private void internalPanel() {

        GridBagConstraints constraints = new GridBagConstraints();
        setLayout(new GridBagLayout());

        sourceText(constraints);
        source(constraints);

        setSourceAddress1(constraints);
        setSourceAddress2(constraints);
        setSourcePostOffice(constraints);
        setSourceParish(constraints);

        destination(constraints);

        setDestinationAddress1(constraints);
        setDestinationAddress2(constraints);
        setDestinationPostOffice(constraints);
        setDestinationParish(constraints);

        rate(constraints);

        setRateValue(constraints);
        initializeAddButton(constraints);

        setBorder(BorderFactory.createEmptyBorder(20, 15, 0, 0));

    }

    public void sourceText(GridBagConstraints constraints) {

        JLabel label = new JLabel("Source: ");
        label.setFont(new Font(null, Font.ITALIC, 15));
        constraints.gridx = 0;
        constraints.gridy = 0;

        constraints.insets = new Insets(20, 0, 0, 0);
        add(label, constraints);

        source = new JTextField(45);
        source.setPreferredSize(new Dimension(0, 30));
        constraints.gridx = 1;
        constraints.gridy = 0;

        add(source, constraints);

    }

    public void source(GridBagConstraints constraints) {

        JLabel label = new JLabel("Source Address");
        label.setFont(new Font(null, Font.ITALIC, 15));
        constraints.gridx = 1;
        constraints.gridy = 1;

        constraints.insets = new Insets(20, 0, 0, 0);
        add(label, constraints);
    }

    public void setSourceAddress1(GridBagConstraints constraints) {

        JLabel label = new JLabel("Address 1: ");
        label.setFont(new Font(null, Font.ITALIC, 15));
        constraints.gridx = 0;
        constraints.gridy = 2;

        constraints.insets = new Insets(20, 0, 0, 0);
        add(label, constraints);

        sourceAddress1 = new JTextField(45);
        sourceAddress1.setPreferredSize(new Dimension(0, 30));
        constraints.gridx = 1;
        constraints.gridy = 2;

        add(sourceAddress1, constraints);

    }

    public void setSourceAddress2(GridBagConstraints constraints) {

        JLabel label = new JLabel("Address 2: ");
        label.setFont(new Font(null, Font.ITALIC, 15));
        constraints.gridx = 0;
        constraints.gridy = 3;

        constraints.insets = new Insets(20, 0, 0, 0);
        add(label, constraints);

        sourceAddress2 = new JTextField(45);
        sourceAddress2.setPreferredSize(new Dimension(0, 30));
        constraints.gridx = 1;
        constraints.gridy = 3;

        add(sourceAddress2, constraints);

    }

    public void setSourcePostOffice(GridBagConstraints constraints) {

        JLabel label = new JLabel("Post Office: ");
        label.setFont(new Font(null, Font.ITALIC, 15));
        constraints.gridx = 0;
        constraints.gridy = 4;

        constraints.insets = new Insets(20, 0, 0, 0);
        add(label, constraints);

        sourcePostOffice = new JTextField(45);
        sourcePostOffice.setPreferredSize(new Dimension(0, 30));
        constraints.gridx = 1;
        constraints.gridy = 4;

        add(sourcePostOffice, constraints);

    }

    public void setSourceParish(GridBagConstraints constraints) {

        JLabel label = new JLabel("Parish: ");
        label.setFont(new Font(null, Font.ITALIC, 15));
        constraints.gridx = 0;
        constraints.gridy = 5;

        constraints.insets = new Insets(20, 0, 0, 0);
        add(label, constraints);

        sourceParish = new JTextField(45);
        sourceParish.setPreferredSize(new Dimension(0, 30));
        constraints.gridx = 1;
        constraints.gridy = 5;

        add(sourceParish, constraints);

    }

    public void destination(GridBagConstraints constraints) {

        JLabel label = new JLabel("Destination Address");
        label.setFont(new Font(null, Font.ITALIC, 15));
        constraints.gridx = 1;
        constraints.gridy = 6;

        constraints.insets = new Insets(20, 0, 10, 0);
        add(label, constraints);
    }

    public void setDestinationAddress1(GridBagConstraints constraints) {

        JLabel label = new JLabel("Address 1: ");
        label.setFont(new Font(null, Font.ITALIC, 15));
        constraints.gridx = 0;
        constraints.gridy = 7;

        constraints.insets = new Insets(20, 0, 0, 0);
        add(label, constraints);

        destinationAddress1 = new JTextField(45);
        destinationAddress1.setPreferredSize(new Dimension(0, 30));
        constraints.gridx = 1;
        constraints.gridy = 7;

        add(destinationAddress1, constraints);

    }

    public void setDestinationAddress2(GridBagConstraints constraints) {

        JLabel label = new JLabel("Address 2: ");
        label.setFont(new Font(null, Font.ITALIC, 15));
        constraints.gridx = 0;
        constraints.gridy = 8;

        constraints.insets = new Insets(20, 0, 0, 0);
        add(label, constraints);

        destinationAddress2 = new JTextField(45);
        destinationAddress2.setPreferredSize(new Dimension(0, 30));
        constraints.gridx = 1;
        constraints.gridy = 8;

        add(destinationAddress2, constraints);

    }

    public void setDestinationPostOffice(GridBagConstraints constraints) {

        JLabel label = new JLabel("Post Office: ");
        label.setFont(new Font(null, Font.ITALIC, 15));
        constraints.gridx = 0;
        constraints.gridy = 9;

        constraints.insets = new Insets(20, 0, 0, 0);
        add(label, constraints);

        destinationPostOffice = new JTextField(45);
        destinationPostOffice.setPreferredSize(new Dimension(0, 30));
        constraints.gridx = 1;
        constraints.gridy = 9;

        add(destinationPostOffice, constraints);

    }

    public void setDestinationParish(GridBagConstraints constraints) {

        JLabel label = new JLabel("Parish: ");
        label.setFont(new Font(null, Font.ITALIC, 15));
        constraints.gridx = 0;
        constraints.gridy = 10;

        constraints.insets = new Insets(20, 0, 0, 0);
        add(label, constraints);

        destinationParish = new JTextField(45);
        destinationParish.setPreferredSize(new Dimension(0, 30));
        constraints.gridx = 1;
        constraints.gridy = 10;

        add(destinationParish, constraints);

    }

    public void rate(GridBagConstraints constraints) {

        JLabel label = new JLabel("RATE");
        label.setFont(new Font(null, Font.ITALIC, 15));
        constraints.gridx = 1;
        constraints.gridy = 11;

        constraints.insets = new Insets(20, 0, 10, 0);
        add(label, constraints);
    }

    public void setRateValue(GridBagConstraints constraints) {

        JLabel label = new JLabel("Rate Value: ");
        label.setFont(new Font(null, Font.ITALIC, 15));
        constraints.gridx = 0;
        constraints.gridy = 12;

        constraints.insets = new Insets(20, 0, 0, 0);
        add(label, constraints);

        rateValue = new JTextField(45);
        rateValue.setPreferredSize(new Dimension(0, 30));
        constraints.gridx = 1;
        constraints.gridy = 12;

        add(rateValue, constraints);

    }

    public void initializeAddButton(GridBagConstraints constraints) {

        addButton = new JButton("Submit");
        addButton.setPreferredSize(new Dimension(200, 30));
        addButton.addActionListener(this);

        constraints.insets = new Insets(20, 0, 0, 0);
        constraints.gridx = 1;
        constraints.gridy = 13;

        add(addButton, constraints);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Client client = new Client();
        if (e.getSource() == addButton && this.validateInputFields()) {

            Route route = convertToRoute();
            clearFields();
            client.sendAction("Add Route");
            client.sendObject(route);

        }
        client.closeConnection();

    }

    private boolean validateInputFields() {

        if (source.getText().isEmpty()
                && sourceAddress1.getText().isEmpty() && sourceAddress2.getText().isEmpty()
                && destinationAddress1.getText().isEmpty() && destinationAddress2.getText().isEmpty()
                && !ConstantFunctions.isNumeric(rateValue.getText())) {
            JOptionPane.showMessageDialog(null, "Please fill all the fields");
            return false;
        }
        return true;

    }

    public Route convertToRoute() {

        Address sourceAddress = new Address(sourceAddress1.getText(),
                sourceAddress2.getText(), sourcePostOffice.getText(), sourceParish.getText());

        Address destinationAddress = new Address(destinationAddress1.getText(),
                destinationAddress2.getText(), destinationPostOffice.getText(), destinationParish.getText());

        Double rate = Double.parseDouble(rateValue.getText());

        return new Route(source.getText(), sourceAddress, destinationAddress, rate);
    }

    public void clearFields() {

        source.setText("");

        sourceAddress1.setText("");
        sourceAddress2.setText("");
        sourcePostOffice.setText("");
        sourceParish.setText("");

        destinationAddress1.setText("");
        destinationAddress2.setText("");
        destinationPostOffice.setText("");
        destinationParish.setText("");
        rateValue.setText("");

    }

}
