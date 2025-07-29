CREATE DATABASE IF NOT EXISTS lms;
USE lms;

CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role ENUM('admin', 'teacher', 'student') NOT NULL,
    approved BOOLEAN DEFAULT FALSE
);

CREATE TABLE posted_assignments (
    id INT PRIMARY KEY AUTO_INCREMENT,
    teacher_id INT,
    title VARCHAR(255),
    description TEXT,
    due_date DATE,
    FOREIGN KEY (teacher_id) REFERENCES users(id)
);

CREATE TABLE submitted_assignments (
    id INT PRIMARY KEY AUTO_INCREMENT,
    assignment_id INT,
    student_id INT,
    file_path VARCHAR(255),
    submission_date DATE,
    grade VARCHAR(10),
    feedback TEXT,
    FOREIGN KEY (assignment_id) REFERENCES posted_assignments(id),
    FOREIGN KEY (student_id) REFERENCES users(id)
);

CREATE TABLE attendance (
    id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT,
    date DATE,
    status ENUM('present', 'absent'),
    FOREIGN KEY (student_id) REFERENCES users(id)
);

CREATE TABLE grades (
    id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT,
    assignment_id INT,
    grade VARCHAR(10),
    feedback TEXT,
    FOREIGN KEY (student_id) REFERENCES users(id),
    FOREIGN KEY (assignment_id) REFERENCES posted_assignments(id)
);

CREATE TABLE messages (
    id INT PRIMARY KEY AUTO_INCREMENT,
    sender_id INT,
    receiver_id INT,
    content TEXT,
    timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (sender_id) REFERENCES users(id),
    FOREIGN KEY (receiver_id) REFERENCES users(id)
);

CREATE TABLE activity_logs (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    action VARCHAR(255),
    timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE reset_tokens (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    token VARCHAR(255),
    expiration DATETIME,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Insert default admin user
INSERT INTO users (name, email, password, role, approved) 
VALUES ('ADMIN-1', 'admin1@lms.com', 'admin1', 'admin', 1);
