package org.example.ui.registration;

import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.swing.FontIcon;

import javax.swing.*;
import java.awt.*;

public class BookingPanel extends JPanel {

    private final BookingItemListener listener;

    public enum Item {Facilities, Schedule, Attendance}

    private JButton facilitiesButton;
    private JButton scheduleButton;
    private JButton attendanceButton;

    public BookingPanel(BookingItemListener listener) {

        this.listener = listener;

        initComponents();

        addComponents();

    }

    private void addComponents() {
        setLayout(new GridLayout(2, 2, 20, 20));

        add(facilitiesButton);
        add(scheduleButton);
        add(attendanceButton);
    }

    private void initComponents() {

        facilitiesButton = new JButton("Facilities");
        facilitiesButton.setIcon(FontIcon.of(FontAwesomeSolid.BUILDING, 48, Color.BLACK));
        facilitiesButton.setHorizontalTextPosition(SwingConstants.CENTER);
        facilitiesButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        facilitiesButton.addActionListener(e -> listener.onBookingItemClicked(Item.Facilities));

        scheduleButton = new JButton("Schedule");
        scheduleButton.setIcon(FontIcon.of(FontAwesomeSolid.CALENDAR_ALT, 48, Color.BLACK));
        scheduleButton.setHorizontalTextPosition(SwingConstants.CENTER);
        scheduleButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        scheduleButton.addActionListener(e -> listener.onBookingItemClicked(Item.Schedule));

        attendanceButton = new JButton("Attendance");
        attendanceButton.setIcon(FontIcon.of(FontAwesomeSolid.CHECK, 48, Color.BLACK));
        attendanceButton.setHorizontalTextPosition(SwingConstants.CENTER);
        attendanceButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        attendanceButton.addActionListener(e -> listener.onBookingItemClicked(Item.Attendance));

    }

    public interface BookingItemListener {

        void onBookingItemClicked(Item item);

    }
}
