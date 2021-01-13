package org.example.ui.registration;

import org.example.ui.Display;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.swing.FontIcon;

import javax.swing.*;
import java.awt.*;

public class RegistrationPanel extends JPanel {

    private RegistrationButtonListener listener;

    public enum Item {Categories, Memberships, Members, Staff}

    private final JButton browseCategoriesButton;
    private final JButton browseMembershipsButton;
    private final JButton browseMembersButton;
    private final JButton browseStaffsButton;

    public RegistrationPanel(RegistrationButtonListener listener) {
        this.listener = listener;

        browseCategoriesButton = new JButton("Browse Categories");
        FontIcon categoriesIcon = FontIcon.of(FontAwesomeSolid.LIST_ALT, 48, Color.BLACK);
        browseCategoriesButton.setIcon(categoriesIcon);
        browseCategoriesButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        browseCategoriesButton.setHorizontalTextPosition(SwingConstants.CENTER);

        browseMembershipsButton = new JButton("Browse Memberships");
        FontIcon membershipIcon = FontIcon.of(FontAwesomeSolid.USER_TAG, 48, Color.BLACK);
        browseMembershipsButton.setIcon(membershipIcon);
        browseMembershipsButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        browseMembershipsButton.setHorizontalTextPosition(SwingConstants.CENTER);

        browseMembersButton = new JButton("Browse Members");
        FontIcon membersIcon = FontIcon.of(FontAwesomeSolid.USERS, 48, Color.BLACK);
        browseMembersButton.setIcon(membersIcon);
        browseMembersButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        browseMembersButton.setHorizontalTextPosition(SwingConstants.CENTER);

        browseStaffsButton = new JButton("Browse Staff");
        FontIcon staffIcon = FontIcon.of(FontAwesomeSolid.USERS_COG, 48, Color.BLACK);
        browseStaffsButton.setIcon(staffIcon);
        browseStaffsButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        browseStaffsButton.setHorizontalTextPosition(SwingConstants.CENTER);

        initComponents();

        addComponents();
    }

    private void addComponents() {
        setLayout(new GridLayout(2, 2, 20, 20));

        add(browseCategoriesButton);
        add(browseMembershipsButton);
        add(browseMembersButton);
        add(browseStaffsButton);
    }

    private void initComponents() {
        browseCategoriesButton.addActionListener(e -> listener.onButtonItemClicked(Item.Categories));
        browseMembershipsButton.addActionListener(e -> listener.onButtonItemClicked(Item.Memberships));
        browseMembersButton.addActionListener(e -> listener.onButtonItemClicked(Item.Members));
        browseStaffsButton.addActionListener(e -> listener.onButtonItemClicked(Item.Staff));

    }

    public interface RegistrationButtonListener {

        void onButtonItemClicked(Item item);

    }
}
