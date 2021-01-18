package org.example.ui.registration;

import javax.swing.*;
import java.awt.*;

public class AddMemberDialog extends JDialog {

    private JFrame frame;
    private JPanel thePanel;

    public AddMemberDialog(JFrame frame, String title) {
        super(frame, title, true);
        this.frame = frame;

        initComponents();

        showDialog();
    }

    private void showDialog() {
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        add(thePanel);
        pack();
        setLocationRelativeTo(frame);
        setVisible(true);
    }

    private void initComponents() {
        thePanel = new JPanel(new GridBagLayout(), true);

        JLabel nameLabel = new JLabel("Name");
        addComponent(nameLabel, 0, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);

        JTextField nameField = new JTextField("", 16);
        addComponent(nameField, 1, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.BOTH);

        JLabel emailLabel = new JLabel("Email");
        addComponent(emailLabel, 0, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);

        JTextField emailField = new JTextField("", 16);
        addComponent(emailField, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.BOTH);
    }

    private void addComponent(JComponent component, int x, int y, int width, int height, int anchor, int fill) {
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = width;
        constraints.gridheight = height;
        constraints.weightx = 100;
        constraints.weighty = 100;
        constraints.insets = new Insets(12, 12, 12, 12);
        constraints.anchor = anchor;
        constraints.fill = fill;

        thePanel.add(component, constraints);

    }
}
