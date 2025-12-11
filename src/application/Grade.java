package application;

public class Grade {
    private String studentId;
    private String courseId;
    private String courseName;
    private double score;
    private String letterGrade;

    public Grade(String studentId, String courseId, double score) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.score = score;
        this.letterGrade = calculateLetterGrade(score);
        this.courseName = "";
    }

    private String calculateLetterGrade(double score) {
        if (score >= 90) return "A";
        else if (score >= 80) return "B";
        else if (score >= 70) return "C";
        else if (score >= 60) return "D";
        else return "F";
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
        this.letterGrade = calculateLetterGrade(score);
    }

    public String getLetterGrade() {
        return letterGrade;
    }

    @Override
    public String toString() {
        return studentId + "," + courseId + "," + score + "," + letterGrade;
    }
}