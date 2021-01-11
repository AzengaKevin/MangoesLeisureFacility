package org.example.ui;

import javax.swing.*;

public class Display {

    private JFrame frame;
    private String title;
    private int width;
    private int height;

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
        frame.setLocationRelativeTo(null);
    }

    public void show() {
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }
}
