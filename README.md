# Student Grade Management System

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![JavaFX](https://img.shields.io/badge/JavaFX-007396?style=for-the-badge&logo=java&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)

A comprehensive JavaFX desktop application for managing students, courses, and grades with MySQL database integration.

## Features

### Student Management
- Add, update, and delete student records
- View all students in a table
- Search and filter functionality

### Grade Management
- Add grades for students in different courses
- Automatic letter grade calculation (A, B, C, D, F)
- GPA calculation

### Course Management
- Pre-loaded course catalog
- Course information (ID, name, credits, instructor)

### Database Integration
- MySQL database for persistent storage
- CRUD operations (Create, Read, Update, Delete)
- Foreign key relationships between tables

## Technologies Used

- **Java** - Core programming language
- **JavaFX** - GUI framework
- **FXML** - UI layout
- **MySQL** - Database management
- **JDBC** - Database connectivity
- **IntelliJ IDEA** - Development environment

## Database Schema

### Tables:
1. **students** - Student information (ID, name, major)
2. **courses** - Course catalog (ID, name, credits, instructor)
3. **grades** - Student grades (student_id, course_id, score, letter_grade)

## Getting Started

### Prerequisites
- Java JDK 11 or higher
- JavaFX SDK
- MySQL Server (or XAMPP)
- IntelliJ IDEA (or any Java IDE)

### Installation

1. **Clone the repository**
```bash
   git clone https://github.com/suryayousufzai/Student-Grade-Management-System.git
```

2. **Set up the database**
   - Start MySQL server
   - Create database: `student_management`
   - Run the SQL commands:
```sql
   CREATE DATABASE student_management;
   USE student_management;

   CREATE TABLE students (
       student_id VARCHAR(20) PRIMARY KEY,
       name VARCHAR(100) NOT NULL,
       major VARCHAR(100) NOT NULL
   );

   CREATE TABLE courses (
       course_id VARCHAR(20) PRIMARY KEY,
       course_name VARCHAR(100) NOT NULL,
       credits INT NOT NULL,
       instructor VARCHAR(100)
   );

   CREATE TABLE grades (
       grade_id INT AUTO_INCREMENT PRIMARY KEY,
       student_id VARCHAR(20) NOT NULL,
       course_id VARCHAR(20) NOT NULL,
       score DOUBLE NOT NULL,
       letter_grade VARCHAR(2) NOT NULL,
       FOREIGN KEY (student_id) REFERENCES students(student_id) ON DELETE CASCADE,
       FOREIGN KEY (course_id) REFERENCES courses(course_id) ON DELETE CASCADE
   );

   -- Sample data
   INSERT INTO courses VALUES
   ('CS101', 'Introduction to Programming', 3, 'Dr. Smith'),
   ('CS102', 'Data Structures', 4, 'Dr. Johnson'),
   ('CS201', 'Database Systems', 3, 'Dr. Williams'),
   ('MATH101', 'Calculus I', 4, 'Dr. Brown');
```

3. **Configure database connection**
   - Open `DataManager.java`
   - Update connection details if needed:
```java
   private static final String URL = "jdbc:mysql://localhost:3306/student_management";
   private static final String USER = "root";
   private static final String PASSWORD = "";
```

4. **Add JavaFX library to your IDE**

5. **Run the application**
   - Execute `Main.java`

## Screenshots

*In screenshot folder*

## Learning Outcomes

This project demonstrates:
- Object-Oriented Programming (OOP) principles
- MVC (Model-View-Controller) architecture
- Database design and SQL queries
- GUI development with JavaFX
- Error handling and validation
- File organization and project structure

## Author

**Surya Yousufzai**
- GitHub: [@suryayousufzai](https://github.com/suryayousufzai)
- LinkedIn: [Surya Yousufzai - Software Engineer](https://www.linkedin.com/in/surya-yousufzai)

## License

This project was created as part of Programming II course requirements.

## Acknowledgments

- **Course:** Programming II
- **Institution:** American University of Afghanistan
- **Year:** 2025

---

⭐ If you found this project helpful, please give it a star!
