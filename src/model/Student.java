package model;

public class Student extends User {
    public Student(int id, String name, String email, String password, String role, boolean approved) {
        super(id, name, email, password, role, approved);
    }
}
