package model;

public class Admin extends User {
    public Admin(int id, String name, String email, String password, String role, boolean approved) {
        super(id, name, email, password, role, approved);
    }
}
