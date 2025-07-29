package model;

public class Teacher extends User {
    public Teacher(int id, String name, String email, String password, String role, boolean approved) {
        super(id, name, email, password, role, approved);
    }
}
