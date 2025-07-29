package dao;

import model.PostedAssignment;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostedAssignmentDAO {

    public boolean postAssignment(PostedAssignment assignment) {
        String query = "INSERT INTO posted_assignments (title, description, due_date, file_path, posted_by, posted_at) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, assignment.getTitle());
            stmt.setString(2, assignment.getDescription());
            stmt.setDate(3, Date.valueOf(assignment.getDueDate()));
            stmt.setString(4, assignment.getFilePath());
            stmt.setString(5, assignment.getPostedBy());
            stmt.setTimestamp(6, Timestamp.valueOf(assignment.getPostedAt()));
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<PostedAssignment> getAllAssignments() {
        List<PostedAssignment> assignments = new ArrayList<>();
        String query = "SELECT * FROM posted_assignments ORDER BY posted_at DESC";
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                PostedAssignment assignment = new PostedAssignment(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getDate("due_date").toLocalDate(),
                        rs.getString("file_path"),
                        rs.getString("posted_by"),
                        rs.getTimestamp("posted_at").toLocalDateTime()
                );
                assignments.add(assignment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assignments;
    }

    public List<PostedAssignment> getAssignmentsByTeacher(String email) {
        List<PostedAssignment> assignments = new ArrayList<>();
        String query = "SELECT * FROM posted_assignments WHERE posted_by = ? ORDER BY posted_at DESC";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                PostedAssignment assignment = new PostedAssignment(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getDate("due_date").toLocalDate(),
                        rs.getString("file_path"),
                        rs.getString("posted_by"),
                        rs.getTimestamp("posted_at").toLocalDateTime()
                );
                assignments.add(assignment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assignments;
    }
}
