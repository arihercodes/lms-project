package gui;

import dao.SubmittedAssignmentDAO;
import model.SubmittedAssignment;
import model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ViewSubmissionsWindow extends JFrame {
    private JTable submissionsTable;
    private User teacher;

    public ViewSubmissionsWindow(User teacher) {
        this.teacher = teacher;

        setTitle("View Submissions");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        submissionsTable = new JTable();
        add(new JScrollPane(submissionsTable), BorderLayout.CENTER);

        loadSubmissions();

        setVisible(true);
    }

    private void loadSubmissions() {
        List<SubmittedAssignment> submissions = new SubmittedAssignmentDAO().getAllSubmissions();
        String[] cols = { "ID", "Assignment ID", "Student", "File", "Submitted At", "Grade", "Feedback" };
        DefaultTableModel model = new DefaultTableModel(cols, 0);

        for (SubmittedAssignment sub : submissions) {
            model.addRow(new Object[] {
                    sub.getId(),
                    sub.getAssignmentId(),
                    sub.getStudentEmail(),
                    sub.getFilePath(),
                    sub.getSubmittedAt(),
                    sub.getGrade(),
                    sub.getFeedback()
            });
        }

        submissionsTable.setModel(model);
    }
}
