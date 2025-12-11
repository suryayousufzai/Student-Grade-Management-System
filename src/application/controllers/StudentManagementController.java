package application.controllers;
import application.DataManager;
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
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;
public class StudentManagementController implements Initializable {

    @FXML private TextField txtStudentId;
    @FXML private TextField txtName;
    @FXML private TextField txtMajor;
    @FXML private TableView<Student> studentTable;
    @FXML private TableColumn<Student, String> colStudentId;
    @FXML private TableColumn<Student, String> colName;
    @FXML private TableColumn<Student, String> colMajor;
    private ObservableList<Student> studentList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colMajor.setCellValueFactory(new PropertyValueFactory<>("major"));
        loadStudents();
        studentTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                txtStudentId.setText(newSelection.getStudentId());
                txtName.setText(newSelection.getName());
                txtMajor.setText(newSelection.getMajor());
            }
        });
    }
    private void loadStudents() {
        studentList.clear();
        studentList.addAll(DataManager.loadStudents());
        studentTable.setItems(studentList);
    }
    // this part for adding new student
    @FXML
    public void addStudent(ActionEvent event) {
        if (validateInput()) {
            Student student = new Student(
                    txtStudentId.getText(),
                    txtName.getText(),
                    txtMajor.getText()
            );

            studentList.add(student);
            DataManager.saveStudents(studentList);
            clearFields(event);
            showAlert("Success", "Student added successfully!", Alert.AlertType.INFORMATION);
        }
    }
    // here is for update selected student
    @FXML
    public void updateStudent(ActionEvent event) {
        Student selected = studentTable.getSelectionModel().getSelectedItem();
        if (selected != null && validateInput()) {
            selected.setStudentId(txtStudentId.getText());
            selected.setName(txtName.getText());
            selected.setMajor(txtMajor.getText());

            DataManager.saveStudents(studentList);
            studentTable.refresh();
            clearFields(event);
            showAlert("Success", "Student updated successfully!", Alert.AlertType.INFORMATION);
        } else {
            showAlert("Error", "Please select a student to update!", Alert.AlertType.ERROR);
        }
    }
    // dear professor I add this part to delete selected student
    @FXML
    public void deleteStudent(ActionEvent event) {
        Student selected = studentTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            studentList.remove(selected);
            DataManager.saveStudents(studentList);
            clearFields(event);
            showAlert("Success", "Student deleted successfully!", Alert.AlertType.INFORMATION);
        } else {
            showAlert("Error", "Please select a student to delete!", Alert.AlertType.ERROR);
        }
    }
    //  this par show clear input fields
    @FXML
    public void clearFields(ActionEvent event) {
        txtStudentId.clear();
        txtName.clear();
        txtMajor.clear();
    }
    // this part show validate input
    private boolean validateInput() {
        if (txtStudentId.getText().isEmpty() || txtName.getText().isEmpty() || txtMajor.getText().isEmpty()) {
            showAlert("Error", "All fields are required!", Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }
    // here it show alert dialog
    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    // Gwe we use this part it go back to main menu
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