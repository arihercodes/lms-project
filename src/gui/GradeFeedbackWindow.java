package gui;

import dao.SubmittedAssignmentDAO;
import model.User;

import javax.swing.*;
import java.awt.*;

public class GradeFeedbackWindow extends JFrame {
    private JTextField submissionIdField, gradeField;
    private JTextArea feedbackArea;
    private JButton submitButton;
    private User teacher;

    public GradeFeedbackWindow(User teacher) {
        this.teacher = teacher;

        setTitle("Grade and Feedback");
        setSize(400, 300);
        setLayout(new GridLayout(5, 2, 10, 10));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        add(new JLabel("Submission ID:"));
        submissionIdField = new JTextField();
        add(submissionIdField);

        add(new JLabel("Grade:"));
        gradeField = new JTextField();
        add(gradeField);

        add(new JLabel("Feedback:"));
        feedbackArea = new JTextArea(3, 20);
        add(new JScrollPane(feedbackArea));

        submitButton = new JButton("Submit");
        add(submitButton);
        add(new JLabel());

        submitButton.addActionListener(e -> {
            try {
                int submissionId = Integer.parseInt(submissionIdField.getText().trim());
                String grade = gradeField.getText().trim();
                String feedback = feedbackArea.getText().trim();
                boolean success = new SubmittedAssignmentDAO().gradeSubmission(submissionId, grade, feedback);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Grading successful.");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Grading failed.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid submission ID.");
            }
        });

        setVisible(true);
    }
}
