package org.example.ui.registration;

import org.example.data.entities.Membership;
import org.example.data.repositories.MembershipRepository;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.awt.*;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddMembershipDialog extends JDialog {

    private final JFrame frame;
    private JPanel mainPanel;
    private JTextField nameField, feeField;

    private final EntityManager entityManager;

    public AddMembershipDialog(JFrame frame, String title, EntityManager entityManager) {

        super(frame, title, true);
        this.frame = frame;
        this.entityManager = entityManager;

        initComponents();
    }

    private void initComponents() {

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());

        JLabel nameLabel = new JLabel("Name:");
        addComp(nameLabel, 0, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);

        nameField = new JTextField("", 15);
        addComp(nameField, 1, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.BOTH);

        JLabel feeLabel = new JLabel("Fee:");
        addComp(feeLabel, 0, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);

        feeField = new JTextField("", 15);
        addComp(feeField, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.BOTH);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {

            try {
                String name = nameField.getText();
                String fee = feeField.getText();


                if (name.isBlank() || name.isEmpty()) {
                    Logger.getLogger(this.getName()).log(Level.WARNING, "Membership Name is required");
                    return;
                }


                if (fee.isBlank() || fee.isEmpty()) {
                    Logger.getLogger(this.getName()).log(Level.WARNING, "Membership Fee is required");
                    return;
                }

                Membership membership = new Membership(name, Double.parseDouble(fee));

                MembershipRepository membershipRepository = new MembershipRepository(entityManager);

                SwingWorker<Optional<Membership>, Void> addMembershipWorker = new SwingWorker<>() {
                    @Override
                    protected Optional<Membership> doInBackground() throws Exception {
                        return Optional.empty();
                    }

                    @Override
                    protected void done() {

                        Optional<Membership> maybeMembership = membershipRepository.save(membership);

                        maybeMembership.ifPresent(membershipFromDb -> dispose());
                    }
                };

                addMembershipWorker.execute();

            } catch (NumberFormatException exception) {

                Logger.getLogger(this.getName()).log(Level.WARNING, "Membership Fee is Invalid");
            }
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
