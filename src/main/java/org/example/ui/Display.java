package org.example.ui;

import org.example.ui.registration.RegistrationPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Display {

    private JFrame frame;
    private final String title;
    private final int width;
    private final int height;
    private JPanel theMainPanel;

    public Display(String title, int width, int height) {

        this.title = title;
        this.width = width;
        this.height = height;

        initFrame();

    }

    private void initFrame() {

        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();

        frame.setLocationRelativeTo(null);

    }

    private void initComponents() {

        initMenuBar();

        initMainPanel();

    }

    private void initMainPanel() {

        frame.setLayout(new BorderLayout());

        theMainPanel = new JPanel();
        theMainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        theMainPanel.setLayout(new BorderLayout());

        Box vBox = Box.createVerticalBox();

        JButton registrationModuleButton = new JButton("Registration Module");
        registrationModuleButton.addActionListener(e -> showRegistrationPanel());
        JButton bookingModuleButton = new JButton("Booking Module");

        JButton paymentsModuleButton = new JButton("Payment Module");

        vBox.add(Box.createVerticalStrut(20));
        vBox.add(registrationModuleButton);

        registrationModuleButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        vBox.add(Box.createVerticalStrut(20));

        vBox.add(bookingModuleButton);
        bookingModuleButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        vBox.add(Box.createVerticalStrut(20));

        vBox.add(paymentsModuleButton);
        paymentsModuleButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        vBox.add(Box.createVerticalStrut(20));

        theMainPanel.add(vBox, BorderLayout.CENTER);

        frame.add(theMainPanel, BorderLayout.CENTER);

    }

    private void showRegistrationPanel() {

        theMainPanel.removeAll();
        theMainPanel.add(new RegistrationPanel());

        theMainPanel.updateUI();

    }

    private void initMenuBar() {

        JMenuBar theMenuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(item -> System.exit(0));
        fileMenu.add(exitMenuItem);
        fileMenu.add(fileMenu);

        theMenuBar.add(fileMenu);

        frame.setJMenuBar(theMenuBar);
    }

    public void show() {
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }
}
