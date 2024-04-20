package constants;

import java.awt.BorderLayout;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import org.apache.logging.log4j.Logger;

import java.beans.PropertyVetoException;
import org.apache.logging.log4j.LogManager;

public class InternalPanel extends JInternalFrame {
    private static Logger logger = LogManager.getLogger(InternalPanel.class);

    private JPanel panel;

    public InternalPanel(String panelName, JPanel panel) {
        super(panelName);
        this.panel = panel;
        logger.info("Instantiating {} Internal Frame", panelName);

        setLayout(new BorderLayout());

        try {
            setMaximum(true);
        } catch (PropertyVetoException e) {

            e.printStackTrace();
        }
        initializeScrollPane();

    }

    private void initializeScrollPane() {
        add(panel, BorderLayout.CENTER);

        JScrollPane pane = new JScrollPane(panel);

        pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        pane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        add(pane, BorderLayout.CENTER);
        setVisible(true);

    }

}
