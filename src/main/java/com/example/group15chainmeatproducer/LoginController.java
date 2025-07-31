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

    private static DatabaseUtil.User currentUser;

    public static DatabaseUtil.User getCurrentUser() {
        return currentUser;
    }

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

        // Authenticate users against the database
        DatabaseUtil.User user = DatabaseUtil.authenticateUser(idNumber, userType, password);
        if (user != null) {
            currentUser = user;
            System.out.println("Login successful - ID: " + idNumber + ", User Type: " + userType);
            // After successful login, you can navigate to appropriate dashboard
        } else {
            System.out.println("Invalid credentials!");
        }
    }

    @FXML
    private void handleSignUp(ActionEvent event) {
        // Navigate to signup page using SceneManager
        SceneManager.switchToSignUp(event);
    }
}