package application;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataManager {

    private static final String URL = "jdbc:mysql://localhost:3306/student_management";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

   public static void saveStudent(Student student) {
        String sql = "INSERT INTO students (student_id, name, major) VALUES (?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE name=?, major=?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, student.getStudentId());
            stmt.setString(2, student.getName());
            stmt.setString(3, student.getMajor());
            stmt.setString(4, student.getName());
            stmt.setString(5, student.getMajor());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void saveStudents(List<Student> students) {
        for (Student student : students) {
            saveStudent(student);
        }
    }

    public static List<Student> loadStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Student student = new Student(
                        rs.getString("student_id"),
                        rs.getString("name"),
                        rs.getString("major")
                );
                students.add(student);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }

    public static void saveGrade(Grade grade) {
        String sql = "INSERT INTO grades (student_id, course_id, score, letter_grade) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, grade.getStudentId());
            stmt.setString(2, grade.getCourseId());
            stmt.setDouble(3, grade.getScore());
            stmt.setString(4, grade.getLetterGrade());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void saveGrades(List<Grade> grades) {
        String deleteSql = "DELETE FROM grades";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(deleteSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (Grade grade : grades) {
            saveGrade(grade);
        }
    }

    public static List<Grade> loadGrades() {
        List<Grade> grades = new ArrayList<>();
        String sql = "SELECT g.*, c.course_name FROM grades g " +
                "LEFT JOIN courses c ON g.course_id = c.course_id";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Grade grade = new Grade(
                        rs.getString("student_id"),
                        rs.getString("course_id"),
                        rs.getDouble("score")
                );
                grade.setCourseName(rs.getString("course_name"));
                grades.add(grade);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return grades;
    }


    public static List<Course> loadCourses() {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM courses";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Course course = new Course(
                        rs.getString("course_id"),
                        rs.getString("course_name"),
                        rs.getInt("credits"),
                        rs.getString("instructor")
                );
                courses.add(course);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courses;
    }
}