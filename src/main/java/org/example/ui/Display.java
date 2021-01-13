package org.example.ui;

import org.example.data.entities.Category;
import org.example.data.entities.Membership;
import org.example.data.repositories.CategoryRepository;
import org.example.data.repositories.MembershipRepository;
import org.example.ui.registration.RegistrationPanel;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Display implements RegistrationPanel.RegistrationButtonListener {

    private JFrame frame;
    private final String title;
    private final int width;
    private final int height;
    private JPanel theMainPanel;


    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("MLF");
    private final EntityManager em = emf.createEntityManager();

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
        theMainPanel.add(new RegistrationPanel(this));

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

    @Override
    public void onButtonItemClicked(RegistrationPanel.Item item) {

        if (item.equals(RegistrationPanel.Item.Categories)) showCategoriesTable();
        if (item.equals(RegistrationPanel.Item.Memberships)) showMemberShipsTable();
    }

    private void showMemberShipsTable() {

        MembershipRepository membershipRepository = new MembershipRepository(em);

        SwingWorker<List<Membership>, Void> getMembershipsWorker = new SwingWorker<>() {
            @Override
            protected List<Membership> doInBackground() throws Exception {
                return membershipRepository.findAll();
            }

            @Override
            protected void done() {

                try {

                    List<Membership> memberships = get();

                    String[] tableHeaders = {"ID", "Name", "Fee"};

                    Object[][] tableData = new Object[memberships.size()][tableHeaders.length];

                    for (int i = 0; i < memberships.size(); i++) {
                        Membership membership = memberships.get(i);
                        tableData[i][0] = membership.getId();
                        tableData[i][1] = membership.getName();
                        tableData[i][2] = membership.getFormattedFee();
                    }

                    theMainPanel.removeAll();

                    JTable categoriesTable = new JTable(tableData, tableHeaders);

                    theMainPanel.add(new JScrollPane(categoriesTable), BorderLayout.CENTER);

                    theMainPanel.updateUI();


                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        };


        getMembershipsWorker.execute();
    }

    private void showCategoriesTable() {

        CategoryRepository categoryRepository = new CategoryRepository(em);

        SwingWorker<List<Category>, Void> getCategoriesWorker = new SwingWorker<>() {
            @Override
            protected List<Category> doInBackground() throws Exception {
                return categoryRepository.findAll();
            }

            @Override
            protected void done() {

                try {

                    List<Category> categories = get();

                    String[] tableHeaders = {"ID", "Name"};

                    Object[][] tableData = new Object[categories.size()][tableHeaders.length];

                    for (int i = 0; i < categories.size(); i++) {
                        Category category = categories.get(i);
                        tableData[i][0] = category.getId();
                        tableData[i][1] = category.getName();
                    }

                    theMainPanel.removeAll();

                    JTable categoriesTable = new JTable(tableData, tableHeaders);

                    theMainPanel.add(new JScrollPane(categoriesTable), BorderLayout.CENTER);

                    theMainPanel.updateUI();


                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        };


        getCategoriesWorker.execute();

    }
}
