package org.example.bmicalculator;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.application.Platform;
import javafx.scene.control.Alert.AlertType;

public class BMICalculatorController {

    // RadioButton for selecting metric units (kilograms/meters)
    @FXML
    private RadioButton kgmRadioButton;

    // RadioButton for selecting imperial units (pounds/inches)
    @FXML
    private RadioButton lbsInchesRadioButton;

    // TextField for user input of weight
    @FXML
    private TextField weightTextField;

    // TextField for user input of height
    @FXML
    private TextField heightTextField;

    // Label to display the calculated BMI result
    @FXML
    private Label resultLabel;

    // Label to display the BMI status (Underweight, Normal weight, etc.)
    @FXML
    private Label statusLabel;

    // Menu items for application actions
    @FXML
    private MenuItem exitMenuItem;

    @FXML
    private MenuItem clearMenuItem;

    @FXML
    private MenuItem helpMenuItem;

    // Button to trigger the BMI calculation
    @FXML
    private Button calculateButton;

    // Initialize method to set up the UI elements
    @FXML
    public void initialize() {
        // Initialize ToggleGroup for switching between unit measurement systems
        ToggleGroup unitToggleGroup = new ToggleGroup();
        kgmRadioButton.setToggleGroup(unitToggleGroup);
        lbsInchesRadioButton.setToggleGroup(unitToggleGroup);

        // Set up actions for menu items
        exitMenuItem.setOnAction(_ -> exitApplication());  // Close application
        clearMenuItem.setOnAction(_ -> clearFields());      // Clear input fields
        helpMenuItem.setOnAction(_ -> showHelp());          // Show help information
    }

    // Method to calculate BMI based on user input
    @FXML
    private void calculateBMI() {
        try {
            // Parse user input for weight and height
            double weight = Double.parseDouble(weightTextField.getText());
            double height = Double.parseDouble(heightTextField.getText());

            double bmi;
            // Check which unit system is selected and calculate BMI accordingly
            if (kgmRadioButton.isSelected()) {
                // Metric system (weight in kg, height in meters)
                bmi = weight / (height * height);
            } else if (lbsInchesRadioButton.isSelected()) {
                // Imperial system (weight in pounds, height in inches)
                bmi = (weight / (height * height)) * 703;
            } else {
                // No unit selected, prompt user to select one
                statusLabel.setText("Please select a unit.");
                return;
            }

            // Display the calculated BMI and its status
            resultLabel.setText(String.format("BMI: %.2f", bmi));
            statusLabel.setText(getBMIStatus(bmi));

        } catch (NumberFormatException e) {
            // Handle invalid input
            statusLabel.setText("Invalid input. Please enter numbers.");
        }
    }

    // Method to determine the user's BMI status based on the calculated BMI
    private String getBMIStatus(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";  // BMI less than 18.5
        } else if (bmi < 24.9) {
            return "Normal weight"; // BMI between 18.5 and 24.9
        } else if (bmi < 29.9) {
            return "Overweight";    // BMI between 25 and 29.9
        } else {
            return "Obesity";       // BMI 30 or greater
        }
    }

    // Method to clear the input fields and reset labels
    @FXML
    private void clearFields() {
        weightTextField.clear(); // Clear weight input
        heightTextField.clear(); // Clear height input
        resultLabel.setText("");  // Clear result display
        statusLabel.setText("");   // Clear status display
    }

    // Method to exit the application
    @FXML
    private void exitApplication() {
        Platform.exit(); // Close the application
    }

    // Method to display help information in an alert dialog
    @FXML
    private void showHelp() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Help");
        alert.setHeaderText("BMI Calculator Instructions");
        alert.setContentText("""
                1. Enter your weight and height.
                2. Select the unit system (Kg/M or Lbs/Inches).
                3. Ex:60kg/1.70m and 150lbs/65inches
                4. Click 'Calculate' to see your BMI.
                Use 'Clear' to reset fields, and 'Exit' to close the app.""");
        alert.showAndWait(); // Display the alert and wait for user action
    }

    // Getter for the calculate button
    public Button getCalculateButton() {
        return calculateButton;
    }

    // Setter for the calculate button
    public void setCalculateButton(Button calculateButton) {
        this.calculateButton = calculateButton;
    }
}

