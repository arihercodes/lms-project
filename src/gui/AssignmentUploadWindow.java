package gui;

import dao.SubmittedAssignmentDAO;
import model.SubmittedAssignment;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.sql.Timestamp;

public class AssignmentUploadWindow extends JFrame {
    private JTextField assignmentIdField;
    private JButton chooseFileButton, uploadButton;
    private JLabel fileLabel;
    private File selectedFile;
    private User student;

    public AssignmentUploadWindow(User student) {
        this.student = student;
        setTitle("Upload Assignment");
        setSize(400, 200);
        setLayout(new GridLayout(4, 2, 10, 10));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        add(new JLabel("Assignment ID:"));
        assignmentIdField = new JTextField();
        add(assignmentIdField);

        chooseFileButton = new JButton("Choose File");
        fileLabel = new JLabel("No file selected");
        add(chooseFileButton);
        add(fileLabel);

        uploadButton = new JButton("Upload");
        add(uploadButton);
        add(new JLabel());

        chooseFileButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(AssignmentUploadWindow.this);
            if (result == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();
                fileLabel.setText(selectedFile.getName());
            }
        });

        uploadButton.addActionListener(e -> {
            try {
                int assignmentId = Integer.parseInt(assignmentIdField.getText().trim());
                if (selectedFile == null) {
                    JOptionPane.showMessageDialog(this, "Please select a file.");
                    return;
                }
                SubmittedAssignment submission = new SubmittedAssignment(
                        assignmentId,
                        student.getEmail(),
                        selectedFile.getAbsolutePath(),
                        new Timestamp(System.currentTimeMillis())
                );
                boolean success = new SubmittedAssignmentDAO().submitAssignment(submission);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Assignment uploaded.");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Upload failed.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid assignment ID.");
            }
        });

        setVisible(true);
    }
}
