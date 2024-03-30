package com.gui.rate;

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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import com.client.Client;
import com.constants.ConstantFunctions;
import model.Rate;

public class RateMainPanel extends JPanel implements ActionListener{


    private JTextArea rateDescription;
    private JTextField rateValue;


    private JButton addButton;



    public RateMainPanel() {

        internalPanel();
    }

    private void internalPanel() {

        GridBagConstraints constraints = new GridBagConstraints();
        setLayout(new GridBagLayout());


        setRateDescription(constraints);
        setRateValue(constraints);
        initializeAddButton(constraints);

        setBorder(BorderFactory.createEmptyBorder(20, 15, 0, 0));

    }
    

    

    



    
    


    public void setRateDescription(GridBagConstraints constraints) {

        JLabel label = new JLabel("Rate Description: ");
        label.setFont(new Font(null, Font.ITALIC, 15));
        constraints.gridx = 0;
        constraints.gridy = 0;

        constraints.insets = new Insets(20, 0, 0, 0);
        add(label, constraints);

        rateDescription = new JTextArea();
        rateDescription.setPreferredSize(new Dimension(510, 50));
        constraints.gridx = 1;
        constraints.gridy = 0;

        add(rateDescription, constraints);

    }

    
    public void setRateValue(GridBagConstraints constraints) {
        
        JLabel label = new JLabel("Rate Value: ");
        label.setFont(new Font(null,Font.ITALIC, 15));
        constraints.gridx = 0;
        constraints.gridy = 1;

        constraints.insets = new Insets(20,0,0,0);
        add(label, constraints);

        rateValue = new JTextField(45);
        rateValue.setPreferredSize(new Dimension(0, 30));
        constraints.gridx = 1;
        constraints.gridy = 1;

        add(rateValue, constraints);

    }

    public void initializeAddButton(GridBagConstraints constraints) {

        
        addButton = new JButton("Submit");
        addButton.setPreferredSize(new Dimension(200, 30));
        addButton.addActionListener(this);
        

        constraints.insets = new Insets(20,0,0,0);
        constraints.gridx = 1;
        constraints.gridy = 2;


        add(addButton, constraints);

    }


    @Override
    public void actionPerformed(ActionEvent e) {


        Client client = new Client();
        if (e.getSource() == addButton) {

            if (this.validateInputFields()) {

                Rate rate = convertToRate();
                client.sendAction("Add Rate");
                client.sendObject(rate);

            }
        }
        client.closeConnection();

    }

    
    
    private boolean validateInputFields() {


        if (rateDescription.getText().isEmpty() && !ConstantFunctions.isNumeric(rateValue.getText())) {
            JOptionPane.showMessageDialog(null, "Please fill all the fields");
            return false;
        }
        return true;

    }
    
    public Rate convertToRate() {

        double value = Double.parseDouble(rateValue.getText());

        return new Rate(rateDescription.getText(), value);
    }
    

}
