package com.example.group15chainmeatproducer;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

public class SignUpController {

    @FXML
    private TextField fullNameField;

    @FXML
    private TextField idNumberField;

    @FXML
    private ComboBox<String> userTypeComboBox;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Button signUpButton;

    @FXML
    private Button backButton;

    @FXML
    private void initialize() {
        userTypeComboBox.getItems().addAll(
                "Factory Floor Operator",
                "Export Coordinator",
                "Quality Assurance Officer",
                "Inventory Manager",
                "Customer",
                "Customer Service Representative"
        );
    }

    @FXML
    private void handleSignUp(ActionEvent event) {
        // Sign up logic here
        String fullName = fullNameField.getText();
        String idNumber = idNumberField.getText();
        String userType = userTypeComboBox.getValue();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (fullName.isEmpty() || idNumber.isEmpty() || userType == null || password.isEmpty() || confirmPassword.isEmpty()) {
            System.out.println("Please fill all fields!");
            return;
        }

        if (!password.equals(confirmPassword)) {
            System.out.println("Passwords do not match!");
            return;
        }

        // Check if user already exists
        DatabaseManager dbManager = DatabaseManager.getInstance();
        if (dbManager.userExists(idNumber)) {
            System.out.println("User with this ID number already exists!");
            return;
        }

        // Create user and register
        User newUser = new User(fullName, idNumber, userType, password);
        boolean success = dbManager.registerUser(newUser);

        if (success) {
            System.out.println("Registration successful! Please login with your credentials.");
            SceneManager.switchToLogin(event);
        } else {
            System.out.println("Registration failed. Please try again.");
        }
    }

    @FXML
    private void handleBack(ActionEvent event) {
        // Navigate back to login using SceneManager
        SceneManager.switchToLogin(event);
    }
}