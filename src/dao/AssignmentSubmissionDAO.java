package dao;

import model.AssignmentSubmission;

import java.sql.*;

public class AssignmentSubmissionDAO {
    private final String url = "jdbc:mysql://localhost:3306/lms";
    private final String username = "root";
    private final String password = "YOUR_SQL_PASSWORD";

    public boolean submitAssignment(AssignmentSubmission submission) {
        String query = "INSERT INTO submitted_assignments (assignment_id, student_email, submitted_file_path, submitted_at) " +
                       "VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, submission.getAssignmentId());
            stmt.setString(2, submission.getStudentEmail());
            stmt.setString(3, submission.getSubmittedFilePath());
            stmt.setTimestamp(4, submission.getSubmittedAt());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.out.println("Error submitting assignment: " + e.getMessage());
            return false;
        }
    }
}
