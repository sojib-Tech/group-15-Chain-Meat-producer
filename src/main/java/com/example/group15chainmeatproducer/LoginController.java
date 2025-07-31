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

        System.out.println("Login successful - ID: " + idNumber + ", User Type: " + userType);
        // After successful login, you can navigate to appropriate dashboard
    }

    @FXML
    private void handleSignUp(ActionEvent event) {
        // Navigate to signup page using SceneManager
        SceneManager.switchToSignUp(event);
    }
}