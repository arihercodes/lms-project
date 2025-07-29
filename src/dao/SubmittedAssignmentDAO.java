package dao;

import model.SubmittedAssignment;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubmittedAssignmentDAO {

    public boolean submitAssignment(SubmittedAssignment submission) {
        String query = "INSERT INTO submitted_assignments (assignment_id, student_email, file_path, submitted_at) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, submission.getAssignmentId());
            stmt.setString(2, submission.getStudentEmail());
            stmt.setString(3, submission.getFilePath());
            stmt.setTimestamp(4, submission.getSubmittedAt());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean gradeSubmission(int submissionId, String grade, String feedback) {
        String query = "UPDATE submitted_assignments SET grade = ?, feedback = ? WHERE id = ?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, grade);
            stmt.setString(2, feedback);
            stmt.setInt(3, submissionId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public SubmittedAssignment getSubmissionById(int id) {
        String query = "SELECT * FROM submitted_assignments WHERE id = ?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new SubmittedAssignment(
                    rs.getInt("id"),
                    rs.getInt("assignment_id"),
                    rs.getString("student_email"),
                    rs.getString("file_path"),
                    rs.getTimestamp("submitted_at"),
                    rs.getString("grade"),
                    rs.getString("feedback")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<SubmittedAssignment> getAllSubmissions() {
        List<SubmittedAssignment> submissions = new ArrayList<>();
        String query = "SELECT * FROM submitted_assignments";
        try (Connection conn = DBUtil.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                submissions.add(new SubmittedAssignment(
                    rs.getInt("id"),
                    rs.getInt("assignment_id"),
                    rs.getString("student_email"),
                    rs.getString("file_path"),
                    rs.getTimestamp("submitted_at"),
                    rs.getString("grade"),
                    rs.getString("feedback")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return submissions;
    }
}
