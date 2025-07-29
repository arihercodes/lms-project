
# LMS Project (Java Swing + MySQL)

This is a full-featured **Learning Management System (LMS)** desktop application built with **Java Swing GUI** and **MySQL** backend.  
Designed to demonstrate practical application of **Object-Oriented Programming (OOP) principles**, clean **modular structure**, and robust **database integration**.

---

## Key Highlights

- **Secure login system** with role-based access: Admin, Teacher, Student
- **OOP Design**: Inheritance, Abstraction, Encapsulation, and Polymorphism used throughout
- **DAO Pattern**: Clean separation of concerns for database operations
- **Java Swing GUI**: User-friendly graphical interface for each role
- **MySQL Database**: Persistent storage for users, assignments, and submissions
- **Admin Dashboard**: Approve, edit, or remove users
- **Teacher Dashboard**: Post assignments, view submissions, give grades and feedback
- **Student Dashboard**: Submit assignments, view grades, manage profile
- **Session Timeout**: Auto logout after inactivity

---

## Project Structure

```
lms-project/
├── src/
│   ├── dashboard/                 # Dashboards for each role (Admin, Teacher, Student)
│   ├── dao/                       # Data Access Objects for DB operations
│   ├── gui/                       # Swing GUI windows
│   ├── model/                     # User, Assignment, Submission classes
│   ├── util/                      # Utilities like DB connection handler
│   └── Main.java                  # Launches the application
├── lms_schema.sql                # SQL script to set up MySQL DB schema
├── run-lms.bat                   # Batch file to run the project (Windows)
├── sample_config.properties      # Sample DB config file
├── .gitignore                    # Git exclusions (includes db_config.properties)
└── README.md                     # This file
```

---

## OOP Concepts Applied

| Concept           | Description                                                               |
|-------------------|---------------------------------------------------------------------------|
| **Encapsulation** | Each class (User, Assignment, Submission) wraps data + methods            |
| **Inheritance**   | Role-specific users (Admin, Teacher, Student) extend a base User class    |
| **Abstraction**   | Interfaces and abstract classes separate GUI, DB, and logic cleanly       |
| **Polymorphism**  | Method overriding allows role-specific behavior via shared interfaces     |

---

## Technologies Used

- **Java 21.0.8**
- **Java Swing** (Desktop GUI)
- **MySQL**
- **JDBC** (for DB connectivity)
- **Batch Scripting** (Windows launch file)
- **GitHub** for version control

---

## Setup Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/yourusername/lms-project.git
cd lms-project
```

### 2. Set Up MySQL Database

- Create a database (e.g., `lmsdb`)
- Import schema:
  ```bash
  mysql -u root -p < lms_schema.sql
  ```

### 3. Configure Database Credentials

- Create a file named `db_config.properties` in the root folder (not included in Git)
- Use this format:
  ```
  db.url=jdbc:mysql://localhost:3306/lmsdb
  db.username=your_mysql_username
  db.password=your_mysql_password
  ```

> 🔐 **Note**: `db_config.properties` is excluded using `.gitignore` for security.  
> Use `sample_config.properties` as a template.

### 4. Run the App

- Run using the batch file:
  ```bash
  run-lms.bat
  ```
- Or open `Main.java` in your IDE and run it manually.

---

## How to Use

- **Register** a new user (approval required).
- **Login** based on role:
  - Admin: Approve/edit/remove users
  - Teacher: Post assignments, grade students
  - Student: Submit assignments, view grades
- **Edit your profile** any time
- **Auto-logout** after inactivity to ensure session security


## License

This project is licensed under the [MIT License](LICENSE).

---

## Author

Created by **[Ariana]**  
For academic, interview, and learning purposes.  
Contributions & feedback welcome!
