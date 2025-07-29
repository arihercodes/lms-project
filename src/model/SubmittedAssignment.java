package model;

import java.sql.Timestamp;

public class SubmittedAssignment {
    private int id;
    private int assignmentId;
    private String studentEmail;
    private String filePath;
    private Timestamp submittedAt;
    private String grade;
    private String feedback;

    public SubmittedAssignment(int assignmentId, String studentEmail, String filePath, Timestamp submittedAt) {
        this.assignmentId = assignmentId;
        this.studentEmail = studentEmail;
        this.filePath = filePath;
        this.submittedAt = submittedAt;
    }

    public SubmittedAssignment(int id, int assignmentId, String studentEmail, String filePath, Timestamp submittedAt, String grade, String feedback) {
        this.id = id;
        this.assignmentId = assignmentId;
        this.studentEmail = studentEmail;
        this.filePath = filePath;
        this.submittedAt = submittedAt;
        this.grade = grade;
        this.feedback = feedback;
    }

    public SubmittedAssignment(int id, int assignmentId, String studentEmail, String filePath, Timestamp submittedAt) {
        this.id = id;
        this.assignmentId = assignmentId;
        this.studentEmail = studentEmail;
        this.filePath = filePath;
        this.submittedAt = submittedAt;
    }

    public int getId() {
        return id;
    }

    public int getAssignmentId() {
        return assignmentId;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public String getFilePath() {
        return filePath;
    }

    public Timestamp getSubmittedAt() {
        return submittedAt;
    }

    public String getGrade() {
        return grade;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
