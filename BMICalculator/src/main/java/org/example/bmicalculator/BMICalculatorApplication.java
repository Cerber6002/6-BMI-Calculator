package org.example.bmicalculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class BMICalculatorApplication extends Application {

    // The start method is the entry point for the JavaFX application
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file that defines the user interface layout
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("BMICalculator.fxml")));

        // Set the title of the primary stage (the main application window)
        primaryStage.setTitle("BMI Calculator");

        // Create a new scene with the loaded layout and set it to the primary stage
        primaryStage.setScene(new Scene(root));

        // Display the primary stage (show the application window)
        primaryStage.show();
    }

    // The main method serves as the entry point for the application
    public static void main(String[] args) {
        launch(args); // Launch the JavaFX application
    }
}

