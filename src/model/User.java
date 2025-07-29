package model;

public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private String role;
    private boolean approved;

    // Full Constructor
    public User(int id, String name, String email, String password, String role, boolean approved) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.approved = approved;
    }

    // Constructor without ID (for registration)
    public User(String name, String email, String password, String role, boolean approved) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.approved = approved;
    }

    // Minimal constructor
    public User(String name, String email, String password, String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.approved = false; // default
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Email: " + email + ", Role: " + role + ", Approved: " + approved;
    }

}
