package gui;

import dao.UserDAO;
import model.User;

import javax.swing.*;
import java.awt.*;

public class UserEditWindow extends JFrame {
    private JTextField nameField, emailField;
    private JPasswordField passwordField;
    private JComboBox<String> roleBox, approvalBox;
    private int userId;
    private UserDAO userDAO;
    private JFrame parentDashboard;

    public UserEditWindow(int userId) {
        this(userId, null); // Calls the second constructor
    }

    public UserEditWindow(int userId, JFrame parentDashboard) {
        this.userId = userId;
        this.parentDashboard = parentDashboard;
        userDAO = new UserDAO();

        setTitle("Edit User");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2, 10, 10));

        User user = userDAO.getUserById(userId);
        if (user == null) {
            JOptionPane.showMessageDialog(this, "User not found.");
            dispose();
            return;
        }

        add(new JLabel("Name:"));
        nameField = new JTextField(user.getName());
        add(nameField);

        add(new JLabel("Email:"));
        emailField = new JTextField(user.getEmail());
        add(emailField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField(user.getPassword());
        add(passwordField);

        add(new JLabel("Role:"));
        roleBox = new JComboBox<>(new String[]{"student", "teacher", "admin"});
        roleBox.setSelectedItem(user.getRole());
        add(roleBox);

        add(new JLabel("Approved:"));
        approvalBox = new JComboBox<>(new String[]{"true", "false"});
        approvalBox.setSelectedItem(String.valueOf(user.isApproved()));
        add(approvalBox);

        JButton updateBtn = new JButton("Update");
        updateBtn.addActionListener(e -> handleUpdateUser());
        add(updateBtn);

        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.addActionListener(e -> dispose());
        add(cancelBtn);

        setVisible(true);
    }

    private void handleUpdateUser() {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());
        String role = (String) roleBox.getSelectedItem();
        boolean approved = Boolean.parseBoolean((String) approvalBox.getSelectedItem());

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required.");
            return;
        }

        User updatedUser = new User(userId, name, email, password, role, approved);
        boolean success = userDAO.updateUser(updatedUser);

        if (success) {
            JOptionPane.showMessageDialog(this, "User updated successfully.");
            if (parentDashboard instanceof dashboard.AdminDashboard) {
                ((dashboard.AdminDashboard) parentDashboard).refreshUserTable();
            }
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to update user.");
        }
    }
}
