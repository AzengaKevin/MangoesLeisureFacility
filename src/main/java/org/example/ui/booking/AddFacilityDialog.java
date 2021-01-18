package org.example.ui.booking;

import org.example.data.entities.Facility;
import org.example.data.repositories.FacilityRepository;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.awt.*;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddFacilityDialog extends JDialog {

    private final JFrame frame;
    private final EntityManager entityManager;

    private JPanel mainPanel;
    private JTextField nameField, activitiesField;

    public AddFacilityDialog(JFrame frame, String title, EntityManager entityManager) {
        super(frame, title, true);
        this.frame = frame;
        this.entityManager = entityManager;

        initComponents();

    }

    private void initComponents() {

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());

        JLabel nameLabel = new JLabel("Name: ");
        addComp(nameLabel, 0, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);

        nameField = new JTextField("", 15);
        addComp(nameField, 1, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.BOTH);

        JLabel activitiesLabel = new JLabel("Activities: ");
        addComp(activitiesLabel, 0, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);

        activitiesField = new JTextField("", 15);
        addComp(activitiesField, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.BOTH);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            String name = nameField.getText();
            String activities = activitiesField.getText();

            if (name.isBlank() || name.isEmpty()) {
                Logger.getLogger(this.getName()).log(Level.WARNING, "Facility Name is required");
                return;
            }


            if (activities.isBlank() || activities.isEmpty()) {
                Logger.getLogger(this.getName()).log(Level.WARNING, "Facility Activities are required");
                return;
            }

            SwingWorker<Optional<Facility>, Void> addFacilityWorker = new SwingWorker<>() {
                @Override
                protected Optional<Facility> doInBackground() throws Exception {

                    FacilityRepository facilityRepository = new FacilityRepository(entityManager);

                    return facilityRepository.save(new Facility(name, activities));
                }

                @Override
                protected void done() {

                    try {

                        Optional<Facility> maybeFacility = get();

                        maybeFacility.ifPresent(facility -> {
                            JOptionPane.showMessageDialog(AddFacilityDialog.this, "Facility Successfully Added");
                            dispose();
                        });


                    } catch (InterruptedException | ExecutionException exception) {
                        exception.printStackTrace();
                    }
                }
            };

            addFacilityWorker.execute();
        });
        addComp(submitButton, 1, 2, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);

        add(mainPanel);

        pack();

        setLocationRelativeTo(frame);
        setVisible(true);
    }


    private void addComp(JComponent comp, int xPos, int yPos, int compWidth, int compHeight, int place, int stretch) {


        GridBagConstraints gridConstraints = new GridBagConstraints();

        gridConstraints.gridx = xPos;
        gridConstraints.gridy = yPos;
        gridConstraints.gridwidth = compWidth;
        gridConstraints.gridheight = compHeight;
        gridConstraints.weightx = 100;
        gridConstraints.weighty = 100;
        gridConstraints.insets = new Insets(12, 12, 12, 12);
        gridConstraints.anchor = place;
        gridConstraints.fill = stretch;

        mainPanel.add(comp, gridConstraints);

    }
}
