package gui;

import dao.PostedAssignmentDAO;
import model.PostedAssignment;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AssignmentPostWindow extends JFrame {
    private JTextField titleField, descriptionField;
    private JTextField dueDateField;
    private JButton chooseFileButton, postButton;
    private JLabel fileLabel;
    private File selectedFile;
    private User teacher;

    public AssignmentPostWindow(User teacher) {
        this.teacher = teacher;

        setTitle("Post Assignment");
        setSize(400, 300);
        setLayout(new GridLayout(6, 2, 10, 10));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        add(new JLabel("Title:"));
        titleField = new JTextField();
        add(titleField);

        add(new JLabel("Description:"));
        descriptionField = new JTextField();
        add(descriptionField);

        add(new JLabel("Due Date (YYYY-MM-DD):"));
        dueDateField = new JTextField();
        add(dueDateField);

        chooseFileButton = new JButton("Choose File");
        fileLabel = new JLabel("No file selected");
        add(chooseFileButton);
        add(fileLabel);

        postButton = new JButton("Post Assignment");
        add(postButton);
        add(new JLabel());

        chooseFileButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(AssignmentPostWindow.this);
            if (result == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();
                fileLabel.setText(selectedFile.getName());
            }
        });

        postButton.addActionListener(e -> {
            String title = titleField.getText().trim();
            String desc = descriptionField.getText().trim();
            String dueDateStr = dueDateField.getText().trim();

            if (title.isEmpty() || desc.isEmpty() || dueDateStr.isEmpty() || selectedFile == null) {
                JOptionPane.showMessageDialog(this, "Please fill all fields.");
                return;
            }

            try {
                LocalDate dueDate = LocalDate.parse(dueDateStr);
                PostedAssignment assignment = new PostedAssignment(
                        0,
                        title,
                        desc,
                        dueDate,
                        selectedFile.getAbsolutePath(),
                        teacher.getEmail(),
                        LocalDateTime.now()
                );

                boolean success = new PostedAssignmentDAO().postAssignment(assignment);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Assignment posted.");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to post.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid date.");
            }
        });

        setVisible(true);
    }
}
