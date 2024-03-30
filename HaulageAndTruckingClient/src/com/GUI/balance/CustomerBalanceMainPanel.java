package com.gui.balance;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.client.Client;


public class CustomerBalanceMainPanel extends JInternalFrame {
    

    private static Logger logger = LogManager.getLogger(CustomerBalanceMainPanel.class);

    private JTextField customerName;
    private JLabel payStatus;
    private JLabel activeAccount;
    private JLabel customerBalance;

    
    private JPanel panel;


    public CustomerBalanceMainPanel() {

        
        internalPanel();
        add(panel, BorderLayout.CENTER);
    }
    

    private void internalPanel() {

        setLayout(new BorderLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
       
    
        setCustomerName(constraints);

        setPayStatus(constraints);

        setActiveAccount(constraints);

        setCustomerBalance(constraints);
        searchButton(constraints);

        

        panel.setBorder(BorderFactory.createEmptyBorder(20, 15, 0, 0));

    }


    private void setCustomerName(GridBagConstraints constraints) {

        JLabel companyLabel = new JLabel("Company Name: ");
        companyLabel.setFont(new Font(null, Font.ITALIC, 15));

        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(companyLabel, constraints);

        customerName = new JTextField(45);
        customerName.setPreferredSize(new Dimension(0, 30));
        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(customerName, constraints);
    }
    
    private void searchButton(GridBagConstraints constraints){


        JLabel companyLabel = new JLabel("Company Name: ");
        companyLabel.setFont(new Font(null,Font.ITALIC, 15));

        constraints.gridx = 0;
        constraints.gridy = 1;
		panel.add(companyLabel, constraints);
		
		customerName = new JTextField(45);
        customerName.setPreferredSize(new Dimension(0, 30));
		constraints.gridx = 1;
        constraints.gridy = 1;
		panel.add(customerName, constraints);
    }

    private void setPayStatus(GridBagConstraints constraints) {
        
        
        JButton btn = new JButton("Search");
        btn.setFont(new Font(null, Font.ITALIC, 15));
        
        btn.addActionListener(e -> {

            Client client = new Client();
            client.sendAction("check Balance");
            if (!customerName.getText().isEmpty()) {
                client.sendObject(customerName.getText());
                @SuppressWarnings("unchecked")
                Map<String, String> balanceDetails = (Map<String, String>) client.getObject();

                payStatus.setText(balanceDetails.get("pay_status"));
                activeAccount.setText(balanceDetails.get("active_customer"));
                customerBalance.setText(balanceDetails.get("customer_balance"));

                logger.info("Setting JLabels with the information of customer regarding balance ");

                
            }
            else 
            {
                JOptionPane.showMessageDialog(this, "Empty Field", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        constraints.gridx = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridy = 2;
        constraints.insets = new Insets(20, 0, 20, 0);
        panel.add(btn, constraints);
        
       

    }

    private void setActiveAccount(GridBagConstraints constraints) {
        
        
        JLabel label = new JLabel("Account Status: ");
        label.setFont(new Font(null,Font.ITALIC, 15));

        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(label, constraints);
        

        activeAccount = new JLabel("");
        activeAccount.setFont(new Font(null,Font.ITALIC, 15));

        constraints.gridx = 1;
        constraints.gridy = 3;
		panel.add(activeAccount, constraints);

    }

    private void setCustomerBalance(GridBagConstraints constraints) {
        
        JLabel label = new JLabel("Company Balance: ");
        label.setFont(new Font(null,Font.ITALIC, 15));

        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(label, constraints);
        
        customerBalance = new JLabel("");
        customerBalance.setFont(new Font(null,Font.ITALIC, 15));

        constraints.gridx = 1;
        constraints.gridy = 4;
		panel.add(customerBalance, constraints);

    }



}
