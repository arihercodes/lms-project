package gui;

import dao.UserDAO;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RegisterWindow extends JFrame {
    private JTextField nameField, emailField;
    private JPasswordField passwordField;
    private JComboBox<String> roleBox;

    public RegisterWindow() {
        setTitle("LMS Registration");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 270);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Registration Panel
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        formPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        formPanel.add(emailField);

        formPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        formPanel.add(passwordField);

        formPanel.add(new JLabel("Role:"));
        roleBox = new JComboBox<>(new String[]{"student", "teacher"});
        formPanel.add(roleBox);

        // Buttons
        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(this::handleRegister);
        formPanel.add(registerButton);

        JButton backButton = new JButton("Back to Login");
        backButton.addActionListener(e -> {
            dispose();
            new LoginWindow();
        });
        formPanel.add(backButton);

        JButton logoutButton = new JButton("Exit");
        logoutButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
        formPanel.add(logoutButton);

        add(formPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void handleRegister(ActionEvent e) {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());
        String role = (String) roleBox.getSelectedItem();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            return;
        }

        User newUser = new User(name, email, password, role, false); // Not approved yet
        UserDAO userDAO = new UserDAO();
        boolean success = userDAO.registerUser(newUser);

        if (success) {
            JOptionPane.showMessageDialog(this, "Registration successful! Awaiting admin approval.");
            dispose();
            new LoginWindow();
        } else {
            JOptionPane.showMessageDialog(this, "Registration failed. Email may already be in use.");
        }
    }
}
