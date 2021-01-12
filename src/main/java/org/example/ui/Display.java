package org.example.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Display {

    private JFrame frame;
    private String title;
    private int width;
    private int height;

    private JMenuBar theMenuBar;
    private JPanel theMailPanel;

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

        theMailPanel = new JPanel();
        theMailPanel.setBorder(new EmptyBorder(40, 40, 40, 40));
        theMailPanel.setLayout(new BorderLayout());

        Box vBox = Box.createVerticalBox();

        JButton registrationModuleButton = new JButton("Registration Module");

        JButton bookingModuleButton = new JButton("Booking Module");

        JButton paymentsModuleButton = new JButton("Payment Module");

        vBox.add(registrationModuleButton);
        registrationModuleButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        vBox.add(Box.createVerticalStrut(20));

        vBox.add(bookingModuleButton);
        bookingModuleButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        vBox.add(Box.createVerticalStrut(20));

        vBox.add(paymentsModuleButton);
        paymentsModuleButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        vBox.add(Box.createVerticalStrut(20));

        theMailPanel.add(vBox, BorderLayout.CENTER);

        frame.add(theMailPanel, BorderLayout.CENTER);

    }

    private void initMenuBar() {

        theMenuBar = new JMenuBar();

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
