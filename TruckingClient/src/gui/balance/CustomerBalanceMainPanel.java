package gui.balance;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import client.Client;

import model.CustomerBalance;

public class CustomerBalanceMainPanel extends JPanel implements ActionListener {

    private static Logger logger = LogManager.getLogger(CustomerBalanceMainPanel.class);

    private JTextField customerName;

    private JPanel topPanel;
    private JPanel bottomPanel;

    private DefaultTableModel model;

    public CustomerBalanceMainPanel() {

        setLayout(new BorderLayout());
        internalPanel();
    }

    private void internalPanel() {

        topPanel = new JPanel();
        topPanel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();

        setCustomerName(constraints);

        add(topPanel, BorderLayout.NORTH);

        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridBagLayout());

        constraints = new GridBagConstraints();

        initializeTable(constraints);

        add(bottomPanel, BorderLayout.CENTER);

        constraints = new GridBagConstraints();

        searchButton(constraints);

        setBorder(BorderFactory.createEmptyBorder(20, 15, 0, 0));

    }

    private void setCustomerName(GridBagConstraints constraints) {

        JLabel companyLabel = new JLabel("Company Name: ");
        companyLabel.setFont(new Font(null, Font.ITALIC, 15));

        constraints.gridx = 0;
        constraints.gridy = 0;
        topPanel.add(companyLabel, constraints);

        customerName = new JTextField(45);
        customerName.setPreferredSize(new Dimension(0, 30));
        constraints.gridx = 1;
        constraints.gridy = 0;
        topPanel.add(customerName, constraints);
    }

    private void searchButton(GridBagConstraints constraints) {

        JButton btn = new JButton("Search");
        btn.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 15));
        btn.setPreferredSize(new Dimension(100, 30));

        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.insets = new Insets(20, 0, 20, 0);
        topPanel.add(btn, constraints);

        btn.addActionListener(this);

    }

    private void initializeTable(GridBagConstraints gbc) {
        logger.info("Initializing The table and setting the column names ");
        JTable invoiceTable;
        JScrollPane scrollPane;

        model = new DefaultTableModel();
        invoiceTable = new JTable(model);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(0, 10, 0, 0);

        String[] columnNames = { "Company", "Source", "Pay Status", "Route Price", "Company Balance", "Billed By" };

        model.setColumnIdentifiers(columnNames);
        scrollPane = new JScrollPane(invoiceTable);
        bottomPanel.add(scrollPane, gbc);

    }

    public void setData(List<CustomerBalance> balances) {

        logger.info("Loading data into the table");

        for (CustomerBalance balance2 : balances) {

            String company = balance2.getCompanyName();

            String source = balance2.getRoute().getSource();

            String payStatus = "Un-Paid";
            double balance = balance2.getRoute().getRate() - balance2.getPaid();

            if (balance2.getPaid() >= balance2.getRoute().getRate()) {
                payStatus = "Paid";
                balance = 0;
            }

            Double rate = balance2.getPaid();

            String billedBy = balance2.getBilledBy();

            Object[] row = { company, source, payStatus, rate, balance, billedBy };
            model.addRow(row);

        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void actionPerformed(ActionEvent e) {
        List<CustomerBalance> customerBalances;

        Client client = new Client();
        client.sendAction("check Balance");

        if (!customerName.getText().isEmpty()) {
            client.sendObject(customerName.getText());

            customerBalances = (List<CustomerBalance>) client.getObject();
            setData(customerBalances);

            clearField();
            logger.info("Setting JLabels with the information of customer regarding balance ");

        } else {
            JOptionPane.showMessageDialog(this, "Empty Field", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void clearField() {
        customerName.setText("");

    }

}
