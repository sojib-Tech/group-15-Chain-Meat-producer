package com.example.group15chainmeatproducer;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

public class LoginController {

    @FXML
    private TextField idNumberField;

    @FXML
    private ComboBox<String> userTypeComboBox;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Button signUpButton;

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
    private void handleLogin(ActionEvent event) {
        // Login logic here
        String idNumber = idNumberField.getText();
        String userType = userTypeComboBox.getValue();
        String password = passwordField.getText();

        if (idNumber.isEmpty() || userType == null || password.isEmpty()) {
            System.out.println("Please fill all fields!");
            return;
        }

        // Authenticate user using database
        DatabaseManager dbManager = DatabaseManager.getInstance();
        User authenticatedUser = dbManager.authenticateUser(idNumber, password, userType);

        if (authenticatedUser != null) {
            System.out.println("Login successful - Welcome, " + authenticatedUser.getFullName() + "!");
            // Store current user session (you can expand this later)
            CurrentUserSession.setCurrentUser(authenticatedUser);
            // After successful login, you can navigate to appropriate dashboard
            // SceneManager.switchToDashboard(event, userType);
        } else {
            System.out.println("Invalid credentials or user type. Please try again.");
        }
    }

    @FXML
    private void handleSignUp(ActionEvent event) {
        // Navigate to signup page using SceneManager
        SceneManager.switchToSignUp(event);
    }
}