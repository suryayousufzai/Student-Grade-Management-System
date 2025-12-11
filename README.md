# Student Grade Management System

A comprehensive JavaFX desktop application for managing students, courses, and grades with MySQL database integration.

## Features

- **Student Management**
  - Add, update, and delete student records
  - View all students in a table
  - Search and filter functionality
  
- **Grade Management**
  - Add grades for students in different courses
  - Automatic letter grade calculation (A, B, C, D, F)
  - GPA calculation
  
- **Course Management**
  - Pre-loaded course catalog
  - Course information (ID, name, credits, instructor)

- **Database Integration**
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
   git clone https://github.com/yourusername/Student-Grade-Management-System.git
```

2. **Set up the database**
   - Start MySQL server
   - Create database: `student_management`
   - Run the SQL script in `database/schema.sql`

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



## Learning Outcomes

This project demonstrates:
- Object-Oriented Programming (OOP) principles
- MVC (Model-View-Controller) architecture
- Database design and SQL queries
- GUI development with JavaFX
- Error handling and validation
- File organization and project structure

## Author

**[Surya Yousufzai]**
- GitHub: [suryayousufzai](https://github.com/suryayousufzai)
- LinkedIn: [Surya Yousufzai Software Engineer](https://www.linkedin.com/in/surya-y-113964238/)

## License

This project was created as part of Programming II course requirements.

## Acknowledgments

- Course: Programming II
- Institution: American University of Afghanistan 
- Year: 2025
