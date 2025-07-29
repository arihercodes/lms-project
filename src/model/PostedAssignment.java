package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PostedAssignment {
    private int id;
    private String title;
    private String description;
    private LocalDate dueDate;
    private String filePath;
    private String postedBy;
    private LocalDateTime postedAt;

    public PostedAssignment(int id, String title, String description, LocalDate dueDate, String filePath, String postedBy, LocalDateTime postedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.filePath = filePath;
        this.postedBy = postedBy;
        this.postedAt = postedAt;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public LocalDateTime getPostedAt() {
        return postedAt;
    }
}
