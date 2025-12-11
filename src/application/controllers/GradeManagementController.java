package application.controllers;

import application.DataManager;
import application.Grade;
import application.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;
public class GradeManagementController implements Initializable {
    @FXML private ComboBox<String> comboStudents;
    @FXML private TextField txtCourseName;
    @FXML private TextField txtScore;
    @FXML private Label lblGPA;
    @FXML private TableView<Grade> gradeTable;
    @FXML private TableColumn<Grade, String> colStudentId;
    @FXML private TableColumn<Grade, String> colCourseName;
    @FXML private TableColumn<Grade, Double> colScore;
    @FXML private TableColumn<Grade, String> colLetterGrade;
    private ObservableList<Grade> gradeList = FXCollections.observableArrayList();
    private ObservableList<String> studentIds = FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colCourseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        colScore.setCellValueFactory(new PropertyValueFactory<>("score"));
        colLetterGrade.setCellValueFactory(new PropertyValueFactory<>("letterGrade"));
        loadStudentIds();
        loadGrades();
        comboStudents.setOnAction(e -> filterGradesByStudent());
    }
    private void loadStudentIds() {
        studentIds.clear();
        for (Student student : DataManager.loadStudents()) {
            studentIds.add(student.getStudentId() + " - " + student.getName());
        }
        comboStudents.setItems(studentIds);
    }
    private void loadGrades() {
        gradeList.clear();
        gradeList.addAll(DataManager.loadGrades());
        gradeTable.setItems(gradeList);
    }
    private void filterGradesByStudent() {
        String selected = comboStudents.getValue();
        if (selected != null) {
            String studentId = selected.split(" - ")[0];
            ObservableList<Grade> filtered = FXCollections.observableArrayList();

            for (Grade grade : gradeList) {
                if (grade.getStudentId().equals(studentId)) {
                    filtered.add(grade);
                }
            }
            gradeTable.setItems(filtered);
        }
    }
    @FXML
    public void addGrade(ActionEvent event) {
        if (validateInput()) {
            String selected = comboStudents.getValue();
            String studentId = selected.split(" - ")[0];

            Grade grade = new Grade(
                    studentId,
                    txtCourseName.getText(),
                    Double.parseDouble(txtScore.getText())
            );

            gradeList.add(grade);
            DataManager.saveGrades(gradeList);
            filterGradesByStudent();
            clearFields(event);
            showAlert("Success", "Grade added successfully!", Alert.AlertType.INFORMATION);
        }
    }
    @FXML
    public void deleteGrade(ActionEvent event) {
        Grade selected = gradeTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            gradeList.remove(selected);
            DataManager.saveGrades(gradeList);
            filterGradesByStudent();
            clearFields(event);
            showAlert("Success", "Grade deleted successfully!", Alert.AlertType.INFORMATION);
        } else {
            showAlert("Error", "Please select a grade to delete!", Alert.AlertType.ERROR);
        }
    }
    @FXML
    public void calculateGPA(ActionEvent event) {
        String selected = comboStudents.getValue();
        if (selected != null) {
            String studentId = selected.split(" - ")[0];
            double totalScore = 0;
            int count = 0;

            for (Grade grade : gradeList) {
                if (grade.getStudentId().equals(studentId)) {
                    totalScore += grade.getScore();
                    count++;
                }
            }

            if (count > 0) {
                double gpa = (totalScore / count) / 25.0; // Convert to 4.0 scale
                lblGPA.setText(String.format("GPA: %.2f", gpa));
            } else {
                lblGPA.setText("GPA: No grades found");
            }
        } else {
            showAlert("Error", "Please select a student!", Alert.AlertType.ERROR);
        }
    }
    @FXML
    public void clearFields(ActionEvent event) {
        txtCourseName.clear();
        txtScore.clear();
    }
    private boolean validateInput() {
        if (comboStudents.getValue() == null) {
            showAlert("Error", "Please select a student!", Alert.AlertType.ERROR);
            return false;
        }
        if (txtCourseName.getText().isEmpty()) {
            showAlert("Error", "Course name is required!", Alert.AlertType.ERROR);
            return false;
        }
        try {
            double score = Double.parseDouble(txtScore.getText());
            if (score < 0 || score > 100) {
                showAlert("Error", "Score must be between 0 and 100!", Alert.AlertType.ERROR);
                return false;
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "Score must be a valid number!", Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }
    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
    public void goBack(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("application/views/MainDashboard.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 800, 600);
            stage.setScene(scene);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}