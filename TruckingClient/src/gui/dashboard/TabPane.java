package gui.dashboard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

public class TabPane extends JTabbedPane {

    public TabPane() {

        addMouseListener(new TabMouseListener(this));
        setFocusable(false);

    }

    private class TabMouseListener implements MouseListener {

        private final TabPane mainScreen;

        TabMouseListener(TabPane mainScreen) {
            this.mainScreen = mainScreen;
        }

        @Override
        public void mousePressed(MouseEvent e) {

            int index = getSelectedIndex();
            if (SwingUtilities.isRightMouseButton(e)) {
                JPopupMenu popup = new JPopupMenu();
                JMenuItem deleteMenuItem = new JMenuItem("Close");
                deleteMenuItem.addActionListener(new DeleteTabActionListener(index));
                popup.add(deleteMenuItem);
                popup.show(mainScreen, e.getX(), e.getY() + 25);

            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            // No action required
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            // No action required
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // No action required
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // No action required
        }
    }

    private class DeleteTabActionListener implements ActionListener {

        private final int tabIndex;

        DeleteTabActionListener(int tabIndex) {
            this.tabIndex = tabIndex;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            remove(tabIndex);
        }
    }

}
