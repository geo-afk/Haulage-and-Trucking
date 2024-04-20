package constants;

import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;

public class DatePicker extends JPanel {

    private JTextField yearField;
    private JComboBox<String> monthComboBox;
    private JSpinner daySpinner;
    private SpinnerNumberModel daySpinnerModel;

    public DatePicker() {
        setLayout(new FlowLayout());

        // Year field
        yearField = new JTextField(4);
        yearField.setPreferredSize(new Dimension(60, 25));
        yearField.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                updateDaySpinner();
            }
        });

        // Month combo box
        String[] months = { "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December" };
        monthComboBox = new JComboBox<>(months);
        monthComboBox.addActionListener(e -> updateDaySpinner());
        add(new JLabel("Month:"));
        add(monthComboBox);

        // Day spinner
        daySpinnerModel = new SpinnerNumberModel(1, 1, 31, 1);
        daySpinner = new JSpinner(daySpinnerModel);
        daySpinner.setPreferredSize(new Dimension(60, 25));
        add(new JLabel("Day:"));
        add(daySpinner);

        add(new JLabel("Year:"));
        add(yearField);

        setVisible(true);
    }

    private void updateDaySpinner() {
        int year = getYear();
        int month = getMonth();
        int maxDays = getMaxDaysForMonth(year, month);
        daySpinnerModel.setMaximum(maxDays);
        daySpinnerModel.setValue(Math.min((int) daySpinner.getValue(), maxDays));
    }

    private int getYear() {
        try {
            return Integer.parseInt(yearField.getText());
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    private int getMonth() {
        return monthComboBox.getSelectedIndex() + 1;
    }

    private int getMaxDaysForMonth(int year, int month) {
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                return isLeapYear(year) ? 29 : 28;
            default:
                return 0;
        }
    }

    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }

    public Date convertToDate() {
        int year = getYear();
        int month = getMonth();
        int day = (int) daySpinner.getValue();

        if (day > getMaxDaysForMonth(year, month)) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
        return calendar.getTime();
    }
}