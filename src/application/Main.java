package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("application/views/MainDashboard.fxml"));
            Scene scene = new Scene(root, 800, 600);
            primaryStage.setTitle("Student Grade Management System");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            System.out.println("Error loading FXML:");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}