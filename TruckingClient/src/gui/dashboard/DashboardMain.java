package gui.dashboard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Window;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import constants.ConstantFunctions;
import gui.chat.Chat;

public class DashboardMain extends JFrame {

    int screenWidth;
    int screenHeight;

    private JDesktopPane desktopPane;
    private SideBar sideBar;
    private TabPane tabScreen;

    public DashboardMain() {

        super("JAVA Haulage and Trucking");

        setIcon();
        setSideBar();
        setDesktopPane();
        initializeTab();
        addMainDisplay();
    }

    private void setIcon() {
        initializeSize();
        ImageIcon icon = new ImageIcon(" HaulageAndTruckingClient/resources/images/Haulage.png");
        setIconImage(icon.getImage());

    }

    public void disposeOfLogin(JInternalFrame frame) {

        JInternalFrame frame2 = frame;
        frame2.remove(frame);

        Window loginWindow = SwingUtilities.getWindowAncestor(frame);
        if (loginWindow != null) {
            loginWindow.dispose();
        }
    }

    private void initializeSize() {

        Dimension d = ConstantFunctions.initializeSize();

        this.screenWidth = (int) d.getWidth();
        this.screenHeight = (int) d.getHeight();

        setSize(this.screenWidth, this.screenHeight);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void setSideBar() {

        sideBar = new SideBar(this.screenWidth, this.screenHeight);
        sideBar.setVisible(true);
        add(sideBar, BorderLayout.WEST);

    }

    private void setDesktopPane() {

        desktopPane = new JDesktopPane();
        desktopPane.setVisible(true);
        desktopPane.setBackground(new Color(120, 34, 45));
        add(desktopPane, BorderLayout.CENTER);

    }

    private void initializeTab() {

        tabScreen = new TabPane();

        add(tabScreen);
        sideBar.setDesktopAndTabbedPane(desktopPane, tabScreen);
    }

    private void addMainDisplay() {
        Chat chat = new Chat();
        desktopPane.add(chat);
        tabScreen.addTab("", chat);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DashboardMain dashboardMain = new DashboardMain();
            dashboardMain.setVisible(true);
        });

    }
}
