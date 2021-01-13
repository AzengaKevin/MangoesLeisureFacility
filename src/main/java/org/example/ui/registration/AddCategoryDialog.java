package org.example.ui.registration;

import org.example.data.entities.Category;
import org.example.data.repositories.CategoryRepository;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.awt.*;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddCategoryDialog extends JDialog {

    private JLabel nameLabel;
    private JTextField nameField;
    private JPanel mainPanel;
    private JButton addCategoryButton;
    private JFrame frame;
    private EntityManager em;

    public AddCategoryDialog(JFrame frame, String title, EntityManager em) {

        super(frame, title, true);
        this.frame = frame;
        this.em = em;

        initComponents();
    }

    private void initComponents() {

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());

        nameLabel = new JLabel("Name: ");
        addComp(nameLabel, 0, 0, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);

        nameField = new JTextField("", 15);
        addComp(nameField, 1, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.BOTH);

        addCategoryButton = new JButton("Submit");
        addCategoryButton.addActionListener(e -> {

            String name = nameField.getText();

            if (name.isBlank() || name.isEmpty()) {
                Logger.getLogger(this.getName()).log(Level.WARNING, "Category Name is required");
                return;
            }

            CategoryRepository categoryRepository = new CategoryRepository(em);

            SwingWorker<Optional<Category>, Void> addCategoryWorker = new SwingWorker<>() {
                @Override
                protected Optional<Category> doInBackground() throws Exception {
                    return categoryRepository.save(new Category(name));
                }

                @Override
                protected void done() {
                    super.done();

                    try {

                        Optional<Category> maybeCategory = get();

                        maybeCategory.ifPresent(category -> dispose());

                    } catch (InterruptedException | ExecutionException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                }
            };

            addCategoryWorker.execute();
        });

        addComp(addCategoryButton, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
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
