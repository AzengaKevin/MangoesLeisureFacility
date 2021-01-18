package org.example.ui;

import org.example.data.entities.*;
import org.example.data.repositories.*;
import org.example.ui.booking.AddFacilityDialog;
import org.example.ui.booking.BookingPanel;
import org.example.ui.registration.*;

import javax.persistence.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Display implements RegistrationPanel.RegistrationButtonListener, BookingPanel.BookingItemListener {

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

        showWelcomeBox();

        frame.add(theMainPanel, BorderLayout.CENTER);
    }

    private void showWelcomeBox() {

        Box vBox = Box.createVerticalBox();

        JButton registrationModuleButton = new JButton("Registration Module");
        registrationModuleButton.addActionListener(e -> showRegistrationPanel());

        JButton bookingModuleButton = new JButton("Booking Module");
        bookingModuleButton.addActionListener(e -> showBookingPanel());

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

        theMainPanel.removeAll();

        theMainPanel.add(vBox, BorderLayout.CENTER);

        theMainPanel.updateUI();
    }

    private void showBookingPanel() {

        theMainPanel.removeAll();
        theMainPanel.add(new BookingPanel(this));

        theMainPanel.updateUI();
    }

    private void showRegistrationPanel() {

        theMainPanel.removeAll();
        theMainPanel.add(new RegistrationPanel(this));

        theMainPanel.updateUI();

    }

    private void initMenuBar() {

        JMenuBar theMenuBar = new JMenuBar();

        //File Menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(item -> System.exit(0));
        fileMenu.add(exitMenuItem);
        fileMenu.add(fileMenu);

        theMenuBar.add(fileMenu);

        //Registration Menu
        JMenu registrationMenu = new JMenu("Registration");

        JMenuItem addCategoryMenuItem = new JMenuItem("Add Category");
        addCategoryMenuItem.addActionListener(e -> new AddCategoryDialog(frame, "Add Category", em));
        registrationMenu.add(addCategoryMenuItem);

        JMenuItem addMembershipMenuItem = new JMenuItem("Add Membership");
        addMembershipMenuItem.addActionListener(e -> new AddMembershipDialog(frame, "Add Membership", em));
        registrationMenu.add(addMembershipMenuItem);

        JMenuItem addMemberMenuItem = new JMenuItem("Add Member");
        addMemberMenuItem.addActionListener(e -> new AddMemberDialog(frame, "Add Member"));
        registrationMenu.add(addMemberMenuItem);

        theMenuBar.add(registrationMenu);

        //Booking Menu
        JMenu bookingMenu = new JMenu("Booking");

        JMenuItem addFacilityMenuItem = new JMenuItem("Add Facility");
        addFacilityMenuItem.addActionListener(e -> new AddFacilityDialog(frame, "Add Facility", em));
        bookingMenu.add(addFacilityMenuItem);

        theMenuBar.add(bookingMenu);

        ///Window Menu
        JMenu windowMenu = new JMenu("Window");

        JMenuItem welcomeMenuItem = new JMenuItem("Welcome");
        welcomeMenuItem.addActionListener(e -> showWelcomeBox());
        windowMenu.add(welcomeMenuItem);

        theMenuBar.add(windowMenu);

        frame.setJMenuBar(theMenuBar);
    }

    public void show() {
        frame.setVisible(true);
    }

    @Override
    public void onRegistrationItemClicked(RegistrationPanel.Item item) {

        if (item.equals(RegistrationPanel.Item.Categories)) showCategoriesTable();
        if (item.equals(RegistrationPanel.Item.Memberships)) showMemberShipsTable();
        if (item.equals(RegistrationPanel.Item.Members)) showMembersTable();
        if (item.equals(RegistrationPanel.Item.Staff)) showStaffTable();
    }

    private void showStaffTable() {
        StaffRepository staffRepository = new StaffRepository(em);

        SwingWorker<List<Staff>, Void> getStaffWorker = new SwingWorker<>() {
            @Override
            protected List<Staff> doInBackground() throws Exception {

                return staffRepository.findAll();

            }

            @Override
            protected void done() {

                try {
                    List<Staff> staffList = get();

                    String[] tableHeaders = {"ID", "Name", "Title", "Part Time", "Temporary", "Trained"};
                    Object[][] tableData = new Object[staffList.size()][tableHeaders.length];

                    for (int i = 0; i < staffList.size(); i++) {

                        Staff staff = staffList.get(i);

                        tableData[i][0] = staff.getId();
                        tableData[i][1] = staff.getName();
                        tableData[i][2] = staff.getStaffTitle().toString();
                        tableData[i][3] = staff.getPartTime();
                        tableData[i][4] = staff.getTemporary();
                        tableData[i][5] = staff.getTrained();

                    }

                    theMainPanel.removeAll();
                    JTable table = new JTable(tableData, tableHeaders);

                    theMainPanel.add(new JScrollPane(table), BorderLayout.CENTER);

                    theMainPanel.updateUI();

                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }

            }
        };

        getStaffWorker.execute();
    }

    private void showMembersTable() {
        MemberRepository memberRepository = new MemberRepository(em);

        SwingWorker<List<Member>, Void> getMembersWorker = new SwingWorker<>() {
            @Override
            protected List<Member> doInBackground() throws Exception {
                return memberRepository.findAll();
            }

            @Override
            protected void done() {
                try {
                    List<Member> members = get();

                    String[] tableHeaders = {"Name", "Email", "Phone", "Bank Acc/No", "Membership", "Category"};

                    Object[][] tableData = new Object[members.size()][tableHeaders.length];

                    for (int i = 0; i < members.size(); i++) {
                        Member member = members.get(i);

                        tableData[i][0] = member.getName();
                        tableData[i][1] = member.getEmail();
                        tableData[i][2] = member.getPhone();
                        tableData[i][3] = member.getBankAccountNumber();
                        tableData[i][4] = member.getMembership().getName();
                        tableData[i][5] = (member.getCategory() != null) ? member.getCategory().getName() : "N/A";

                        theMainPanel.removeAll();

                        JTable table = new JTable(tableData, tableHeaders);

                        theMainPanel.add(new JScrollPane(table), BorderLayout.CENTER);

                        theMainPanel.updateUI();

                    }

                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        };

        getMembersWorker.execute();
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

    @Override
    public void onBookingItemClicked(BookingPanel.Item item) {


        if (item.equals(BookingPanel.Item.Facilities)) showFacilitiesTable();
        System.out.println(item.toString());
    }

    private void showFacilitiesTable() {

        FacilityRepository facilityRepository = new FacilityRepository(em);

        SwingWorker<List<Facility>, Void> getAllFacilitiesWorker = new SwingWorker<>() {
            @Override
            protected List<Facility> doInBackground() throws Exception {
                return facilityRepository.findAll();
            }

            @Override
            protected void done() {
                try {

                    List<Facility> facilities = get();

                    String[] tableHeaders = {"ID", "Name", "Activities"};

                    Object[][] tableData = new Object[facilities.size()][tableHeaders.length];

                    for (int i = 0; i < facilities.size(); i++) {
                        Facility facility = facilities.get(i);
                        tableData[i][0] = facility.getId();
                        tableData[i][1] = facility.getName();
                        tableData[i][2] = facility.getActivities();
                    }

                    theMainPanel.removeAll();

                    JTable table = new JTable(tableData, tableHeaders);

                    theMainPanel.add(new JScrollPane(table), BorderLayout.CENTER);

                    theMainPanel.updateUI();


                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        };

        getAllFacilitiesWorker.execute();
    }
}
