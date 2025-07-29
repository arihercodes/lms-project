package model;

import java.util.List;

public interface ClassManagement {
    void assignStudentToClass(Student student);
    List<Student> getClassStudents();
}
