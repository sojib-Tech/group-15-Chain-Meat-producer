package com.example.group15chainmeatproducer;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

public class LoginController {

    @FXML
    private ComboBox<String> userTypeComboBox;

    @FXML
    private Button confirmButton;

    private static DatabaseUtil.User currentUser;

    public static DatabaseUtil.User getCurrentUser() {
        return currentUser;
    }

    @FXML
    private void initialize() {
        userTypeComboBox.getItems().addAll(
                "Factory Floor Worker",
                "Export Coordinator",
                "Quality Assurance Officer",
                "Inventory Manager",
                "Customer",
                "Customer Service Representative"
        );
    }

    @FXML
    private void handleConfirm(ActionEvent event) {
        String selectedUser = userTypeComboBox.getValue();

        if (selectedUser == null) {
            System.out.println("Please select a user type!");
            return;
        }

        switch (selectedUser) {
            case "Factory Floor Worker":
                SceneManager.switchToUser1Menu(event);
                break;
            case "Export Coordinator":
                SceneManager.switchToUser2Menu(event);
                break;
            case "Quality Assurance Officer":
                SceneManager.switchToUser3Menu(event);
                break;
            case "Inventory Manager":
                SceneManager.switchToUser4Menu(event);
                break;
            case "Customer":
                System.out.println("Customer dashboard - Not implemented yet");
                break;
            case "Customer Service Representative":
                System.out.println("Customer Service Representative dashboard - Not implemented yet");
                break;
            default:
                System.out.println("Invalid user type selected!");
        }
    }
}