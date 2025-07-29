package model;

import java.sql.Timestamp;

public class AssignmentSubmission {
    private int assignmentId;
    private String studentEmail;
    private String submittedFilePath;
    private Timestamp submittedAt;

    public AssignmentSubmission(int assignmentId, String studentEmail, String submittedFilePath) {
        this.assignmentId = assignmentId;
        this.studentEmail = studentEmail;
        this.submittedFilePath = submittedFilePath;
        this.submittedAt = new Timestamp(System.currentTimeMillis());
    }

    public int getAssignmentId() {
        return assignmentId;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public String getSubmittedFilePath() {
        return submittedFilePath;
    }

    public Timestamp getSubmittedAt() {
        return submittedAt;
    }

    @Override
    public String toString() {
        return "AssignmentSubmission{" +
                "assignmentId=" + assignmentId +
                ", studentEmail='" + studentEmail + '\'' +
                ", submittedFilePath='" + submittedFilePath + '\'' +
                ", submittedAt=" + submittedAt +
                '}';
    }
}
