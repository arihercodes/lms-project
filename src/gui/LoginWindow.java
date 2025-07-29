package gui;

import dao.UserDAO;
import model.User;
import dashboard.AdminDashboard;
import dashboard.TeacherDashboard;
import dashboard.StudentDashboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginWindow extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private UserDAO userDAO;

    public LoginWindow() {
        userDAO = new UserDAO();

        setTitle("Login");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 10, 10));

        add(new JLabel("Email:"));
        emailField = new JTextField();
        add(emailField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");

        add(loginButton);
        add(registerButton);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();
                User user = userDAO.login(email, password);

                if (user != null && user.isApproved()) {
                    JOptionPane.showMessageDialog(LoginWindow.this, "Login successful!");

                    switch (user.getRole().toLowerCase()) {
                        case "admin":
                            new AdminDashboard().setVisible(true);
                            break;
                        case "teacher":
                            new TeacherDashboard(user).setVisible(true);
                            break;
                        case "student":
                            new StudentDashboard(user).setVisible(true);
                            break;
                        default:
                            JOptionPane.showMessageDialog(LoginWindow.this, "Unknown role: " + user.getRole());
                    }

                    dispose(); // close login window
                } else if (user != null && !user.isApproved()) {
                    JOptionPane.showMessageDialog(LoginWindow.this, "Your account is not approved yet.");
                } else {
                    JOptionPane.showMessageDialog(LoginWindow.this, "Invalid credentials.");
                }
            }
        });

        registerButton.addActionListener(e -> {
            new RegisterWindow().setVisible(true);
            dispose();
        });

        setVisible(true);
    }
}
