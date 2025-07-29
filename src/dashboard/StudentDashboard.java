package dashboard;

import gui.AssignmentUploadWindow;
import gui.ViewSubmissionsWindow;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StudentDashboard extends JFrame {
    private final User student;
    private Timer sessionTimer;

    public StudentDashboard(User student) {
        this.student = student;

        setTitle("Student Dashboard - " + student.getName());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Welcome, " + student.getName(), SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(welcomeLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JButton uploadAssignmentBtn = new JButton("Upload Assignment");
        uploadAssignmentBtn.addActionListener(e -> {
            resetTimer();
            new AssignmentUploadWindow(student);
        });

        JButton viewSubmissionsBtn = new JButton("View Submissions");
        viewSubmissionsBtn.addActionListener(e -> {
            resetTimer();
            new ViewSubmissionsWindow(student);
        });

        JButton editProfileBtn = new JButton("Edit Profile");
        editProfileBtn.addActionListener(e -> {
            resetTimer();
            new gui.UserEditWindow(student.getId());
        });

        JButton logoutBtn = new JButton("Logout");
        logoutBtn.addActionListener(e -> {
            logout();
        });

        buttonPanel.add(uploadAssignmentBtn);
        buttonPanel.add(viewSubmissionsBtn);
        buttonPanel.add(editProfileBtn);
        buttonPanel.add(logoutBtn);

        add(buttonPanel, BorderLayout.CENTER);

        setupSessionTimer();
        addActivityListeners();

        setVisible(true);
    }

    private void logout() {
        sessionTimer.stop();
        dispose();
        new gui.LoginWindow();
    }

    private void setupSessionTimer() {
        sessionTimer = new Timer(5 * 60 * 1000, e -> {
            JOptionPane.showMessageDialog(this, "Session expired due to inactivity.");
            logout();
        });
        sessionTimer.setRepeats(false);
        sessionTimer.start();
    }

    private void resetTimer() {
        if (sessionTimer != null) {
            sessionTimer.restart();
        }
    }

    private void addActivityListeners() {
        Toolkit.getDefaultToolkit().addAWTEventListener(e -> resetTimer(), AWTEvent.MOUSE_EVENT_MASK | AWTEvent.KEY_EVENT_MASK);
    }
}
