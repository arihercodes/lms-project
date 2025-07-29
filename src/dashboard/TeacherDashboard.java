package dashboard;

import gui.AssignmentPostWindow;
import gui.GradeFeedbackWindow;
import gui.ViewSubmissionsWindow;
import gui.UserEditWindow;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TeacherDashboard extends JFrame {
    private final User teacher;
    private Timer sessionTimer;

    public TeacherDashboard(User teacher) {
        this.teacher = teacher;

        setTitle("Teacher Dashboard - " + teacher.getName());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Welcome, " + teacher.getName(), SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(welcomeLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JButton postAssignmentBtn = new JButton("Post Assignment");
        postAssignmentBtn.addActionListener(e -> {
            resetTimer();
            new AssignmentPostWindow(teacher);
        });

        JButton gradeFeedbackBtn = new JButton("Grade & Feedback");
        gradeFeedbackBtn.addActionListener(e -> {
            resetTimer();
            new GradeFeedbackWindow(teacher);
        });

        JButton viewSubmissionsBtn = new JButton("View Submissions");
        viewSubmissionsBtn.addActionListener(e -> {
            resetTimer();
            new ViewSubmissionsWindow(teacher);
        });

        JButton editProfileBtn = new JButton("Edit Profile");
        editProfileBtn.addActionListener(e -> {
            resetTimer();
            new UserEditWindow(teacher.getId());
        });

        JButton logoutBtn = new JButton("Logout");
        logoutBtn.addActionListener(e -> {
            logout();
        });

        buttonPanel.add(postAssignmentBtn);
        buttonPanel.add(gradeFeedbackBtn);
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
